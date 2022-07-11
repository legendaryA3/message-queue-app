package com.aprisoft.message.queue.app.controllers.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SyncTableDetails {
    public String tableName;
    public String tableDescription;
    public int numberOfRecords;
    public int numberOfNewlyCreatedRecords;
    public LocalDateTime dateAndTimeLastSynced;
}
