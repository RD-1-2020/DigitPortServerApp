package com.nstu.spdb.enums;

public enum OrderStatus {
    IN_WORK("В работе"),
    READY("Готов к выдаче"),
    CLOSED("Закрыт");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
