package com.aprisoft.message.queue.app.controllers.parameters;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SyncRecordParameters {
    public Long facilityId;
    public String tableName;
    public LocalDateTime lastSyncDateTime;
}
