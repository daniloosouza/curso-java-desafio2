package com.desafio2.apis.commons.interfaces;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public interface WireTimeRule {

    LocalTime getInitLimit();
    LocalTime getEndLimit();
    boolean validatedWire(Double value);
}
