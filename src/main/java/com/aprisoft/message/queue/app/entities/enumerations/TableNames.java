package com.aprisoft.message.queue.app.entities.enumerations;

public enum TableNames {
    PATIENT("PATIENT"), CLINIC("CLINIC"), PHARMACY("PHARMACY"), LABORATORY("LABORATORY");
    private final String status;

    TableNames(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
