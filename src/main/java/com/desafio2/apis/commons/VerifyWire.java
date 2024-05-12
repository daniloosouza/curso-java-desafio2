package com.desafio2.apis.commons;

import com.desafio2.apis.commons.interfaces.WireTimeRule;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class VerifyWire implements WireTimeRule {

    private final LocalTime initLimit = LocalTime.parse("06:00:00");
    private final LocalTime endLimit = LocalTime.parse("20:00:00");

    @Override
    public LocalTime getInitLimit() {
        return initLimit;
    }

    @Override
    public LocalTime getEndLimit() {
        return endLimit;
    }

    @Override
    public boolean validatedWire(Double value) {
        boolean wireLimited = this.wireLimited();
        double valueOvernight = 500.00;

        if (!wireLimited && value > valueOvernight){
            return false;
        }
        return true;

    }

    private boolean wireLimited(){
        LocalTime timeNow = LocalTime.now();
        if (timeNow.isBefore(this.initLimit) || timeNow.isAfter(this.endLimit)){
            return false;
        }else{
            return true;
        }
    }
}
