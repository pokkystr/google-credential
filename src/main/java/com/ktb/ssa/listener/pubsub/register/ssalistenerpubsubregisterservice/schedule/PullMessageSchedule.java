package com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class PullMessageSchedule {

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    void pullMessages() {
        Calendar calendar = Calendar.getInstance();

    }
}
