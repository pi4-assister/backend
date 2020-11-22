package com.senac.assister.backend.domain.cron;

import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.service.ServicesService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void paidPendingServices() {
        List<Service> listOfServices = servicesService.getAllServicesByStatus(ServiceStatus.FINISHED);
        listOfServices.forEach(servicesService::payService);
    }
}

