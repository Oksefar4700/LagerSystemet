package com.example.demo.service;

import com.example.demo.model.Visit;
import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorService extends PersonService<Visitor, VisitorRepository> {

    private final AdministratorService administratorService;
    private final VisitService visitService;


    @Autowired
    public VisitorService(VisitorRepository visitorRepository, AdministratorService administratorService, VisitService visitService) {
        super(visitorRepository);
        this.administratorService = administratorService;
        this.visitService = visitService;
    }

    public Visitor getPersonById(Integer id) {
        Optional<Visitor> visitor = repository.findById(id);
        if (visitor.isPresent()) {
            return visitor.get();
        } else {
            return null;
        }
    }

    public void createVisitorAndVisit(Visitor visitor) {
        Optional<Visitor> existingVisitor = findByLicencePassportNr(visitor.getLicencePassportNr());
        if (existingVisitor.isPresent()) {
            // Visitor exists, create a new visit
            Visitor foundVisitor = existingVisitor.get();
            Visit visit = new Visit();
            visit.setVisitor(foundVisitor);
            visitService.save(visit);
        } else {
            // Visitor does not exist, save the visitor
            save(visitor);

            // Create a new visit for the newly created visitor
            Visit visit = new Visit();
            visit.setVisitor(visitor);
            visitService.save(visit);
        }
    }

    public List<Visitor> searchVisitorsByName(String keyword) {
        List<Visitor> visitors = new ArrayList<>();

        // Search for visitors by name using repository methods
        List<Visitor> byFirstName = repository.findByFirstNameContainingIgnoreCase(keyword);
        List<Visitor> byLastName = repository.findByLastNameContainingIgnoreCase(keyword);

        // Add the matching visitors to the final list
        visitors.addAll(byFirstName);
        visitors.addAll(byLastName);

        return visitors;
    }

    public void deleteVisitor(Integer id) {
        visitService.deleteAllVisitsForVisitor(id);
        repository.deleteById(id);
    }

    @Transactional
    public void deleteUsersWithOldVisits() {
        ZonedDateTime threeYearsAgo = ZonedDateTime.now().minusYears(3);
        List<Visitor> visitors = repository.findAll();

        for (Visitor visitor : visitors) {
            if (!visitor.getVisitsList().isEmpty()) {
                Visit lastVisit = visitor.getVisitsList().get(visitor.getVisitsList().size() - 1);
                if (lastVisit.getCheck_in_time().isBefore(threeYearsAgo.toLocalDate().atStartOfDay(ZoneId.systemDefault()))) {
                    deletePerson(visitor.getPersonId());
                }
            }
        }
    }



}
