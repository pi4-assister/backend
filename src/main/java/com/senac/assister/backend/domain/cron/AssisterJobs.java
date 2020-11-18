package com.senac.assister.backend.domain.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AssisterJobs {
    @Scheduled(cron = "0 0/5 * * * ?")
    public void invalidateServices() {
        System.out.println("Rodando teste no horário: " + Instant.now());
    }
}

