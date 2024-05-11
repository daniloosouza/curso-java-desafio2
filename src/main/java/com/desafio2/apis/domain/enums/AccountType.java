package com.desafio2.apis.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    CONTA_CORRENTE(1),
    CONTA_POUPANCA(2),
    CONTA_SALARIO(3);
    private final int value;
    private static final Map<Object, Object> map = new HashMap<>();

    private AccountType(int value) {
        this.value = value;
    }

    static {
        for (AccountType type : AccountType.values()) {
            map.put(type.value, type);
        }
    }

    public static AccountType valueOf(int pageType) {
        return (AccountType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
