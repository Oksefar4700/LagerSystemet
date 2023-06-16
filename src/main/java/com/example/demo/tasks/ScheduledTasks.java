package com.example.demo.tasks;

import com.example.demo.service.DriverService;
import com.example.demo.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {
    @Autowired
    private DriverService driverService;
    @Autowired
    private VisitorService visitorService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOldUsers() {
        driverService.deleteUsersWithOldVisits();
        visitorService.deleteUsersWithOldVisits();
    }
}
