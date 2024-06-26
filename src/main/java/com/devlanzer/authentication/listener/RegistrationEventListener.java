package com.devlanzer.authentication.listener;

import java.util.UUID;

import org.springframework.context.ApplicationListener;

import com.devlanzer.authentication.events.RegistrationEvent;
import com.devlanzer.authentication.model.User;

public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {

    @Override
    public void onApplicationEvent(RegistrationEvent event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        System.out.println(user+token);
    }

}
