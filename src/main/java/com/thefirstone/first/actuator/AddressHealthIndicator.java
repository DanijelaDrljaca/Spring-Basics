package com.thefirstone.first.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AddressHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (true) {
        return Health.down().withDetail("address_api", "Not available!").build();
        }
        return Health.up().withDetail("address_api", "Available!").build();
    }
}
