package com.senac.assister.backend.domain.cron;

import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.service.ServicesService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class AssisterJobs {

    private final ServicesService servicesService;

    public AssisterJobs(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void invalidateServices() {
        List<Service> listOfServices = servicesService.getAllPendingServices();
        listOfServices.forEach(servicesService::cancelService);
        System.out.println(listOfServices.size() + " Services checked to invalidate in " + Instant.now());
    }

    @Scheduled(cron = "0 0/9 * * * ?")
    public void paidPendingServices() {
        List<Service> listOfServices = servicesService.getAllServicesByStatus(ServiceStatus.FINISHED);
        listOfServices.forEach(servicesService::payService);
        System.out.println(listOfServices.size() + " Services was paid in " + Instant.now());
    }
}

