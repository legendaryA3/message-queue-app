package com.aprisoft.message.queue.app.entities.enumerations;

public enum SyncStatus {
    NOT_SYNC("NOT_SYNC"), SYNCED("SYNCED"), PROCESSING("PROCESSING"), FAILED("FAILED");
    private final String status;

    SyncStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
