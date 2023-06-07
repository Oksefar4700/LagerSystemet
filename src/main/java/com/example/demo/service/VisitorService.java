package com.example.demo.service;

import com.example.demo.model.Visit;
import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void approvePerson(Integer personId) {
        Visitor visitor = getPersonById(personId);
        if (visitor != null) {
            visitor.setAccountStatus("Approved");
            visitor.setApprovedBy(administratorService.getLoggedInAdministrator());
            update(visitor);
        }
    }

    @Override
    public void declinePerson(Integer personId) {
        Visitor visitor = getPersonById(personId);
        if (visitor != null) {
            visitor.setAccountStatus("Declined");
            visitor.setApprovedBy(administratorService.getLoggedInAdministrator());
            update(visitor);
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


}
