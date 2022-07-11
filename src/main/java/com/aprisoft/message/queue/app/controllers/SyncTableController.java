package com.aprisoft.message.queue.app.controllers;

import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;
import com.aprisoft.message.queue.app.controllers.vm.SyncTableDetails;
import com.aprisoft.message.queue.app.services.SyncTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sync")
@CrossOrigin
public class SyncTableController {

    private final SyncTableService syncTableService;
    private static final Logger logger = LoggerFactory.getLogger(SyncTableController.class.getSimpleName());

    public SyncTableController(
            SyncTableService syncTableService
    ) {
        this.syncTableService = syncTableService;
    }

    @GetMapping("/{facilityId}")
    public List<SyncTableDetails> getTablesForSync(@PathVariable("facilityId") Long facilityId) {
        return syncTableService.getSyncTablesDetails(facilityId);
    }

    @PutMapping("/tables")
    public String syncTables(@RequestBody List<SyncRecordParameters> syncRecordParameters){
        logger.info("About to sync table record == {}");
        syncTableService.getProducersData(syncRecordParameters);
        return "Records synced";
    }
}
