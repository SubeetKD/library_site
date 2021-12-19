package com.example.Local.Library.enums;

public enum BookStatusEnum {
    RENTED("RENTED"),
    RETURNED("RETURNED"),
    ;
    private String value;

    BookStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
