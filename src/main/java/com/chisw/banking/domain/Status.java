package com.chisw.banking.domain;

public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");


    String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
