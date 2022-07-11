package com.aprisoft.message.queue.app.services;

import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;
import com.aprisoft.message.queue.app.controllers.vm.SyncTableDetails;
import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.services.producers.impl.ClinicProducer;
import com.aprisoft.message.queue.app.services.producers.impl.LaboratoryProducer;
import com.aprisoft.message.queue.app.services.producers.impl.PatientProducer;
import com.aprisoft.message.queue.app.services.producers.impl.PharmacyProducer;
import com.aprisoft.message.queue.app.entities.entity.views.ClinicView;
import com.aprisoft.message.queue.app.entities.entity.views.LaboratoryView;
import com.aprisoft.message.queue.app.entities.entity.views.PatientView;
import com.aprisoft.message.queue.app.entities.entity.views.PharmacyView;
import com.aprisoft.message.queue.app.entities.enumerations.SyncStatus;
import com.aprisoft.message.queue.app.entities.enumerations.TableNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.aprisoft.message.queue.app.entities.enumerations.TableNames.*;

@Service
public class SyncTableService {
    private final DataSyncService dataSyncService;
    private final PatientService patientService;
    private final PatientProducer patientProducer;
    private final ClinicProducer clinicProducer;
    private final PharmacyProducer pharmacyProducer;
    private final LaboratoryProducer laboratoryProducer;
    private final ClinicService clinicService;
    private final PharmacyService pharmacyService;
    private final LaboratoryService laboratoryService;
    private static final Logger logger = LoggerFactory.getLogger(SyncTableService.class.getSimpleName());

    public SyncTableService(
            DataSyncService dataSyncService,
            PatientService patientService,
            PatientProducer patientProducer,
            ClinicProducer clinicProducer,
            PharmacyProducer pharmacyProducer,
            LaboratoryProducer laboratoryProducer,
            ClinicService clinicService, PharmacyService pharmacyService, LaboratoryService laboratoryService) {
        this.dataSyncService = dataSyncService;
        this.patientService = patientService;
        this.patientProducer = patientProducer;
        this.clinicProducer = clinicProducer;
        this.pharmacyProducer = pharmacyProducer;
        this.laboratoryProducer = laboratoryProducer;
        this.clinicService = clinicService;
        this.pharmacyService = pharmacyService;
        this.laboratoryService = laboratoryService;
    }

    public List<SyncTableDetails> getSyncTablesDetails(Long facilityId) {

        DataSync.MaxSyncTime maxSyncTime = dataSyncService.getTablesSyncMaxDateTime(facilityId);
        List<DataSync> dataSyncList = dataSyncService.getAllDataSync();

        List<SyncTableDetails> syncTableDetailsList = new ArrayList<>();

        EnumSet.allOf(TableNames.class).forEach(tableName -> {
            SyncTableDetails tableDetails = new SyncTableDetails();

            switch (tableName) {
                case PATIENT:
                    List<PatientView> patients =
                            patientService.getPatientByMaxTime(maxSyncTime.getMaxPatientTableSyncDate());

                    tableDetails.setTableDescription("Patient Table");
                    tableDetails.setTableName(String.valueOf(PATIENT));
                    tableDetails.setDateAndTimeLastSynced(maxSyncTime.getMaxPatientTableSyncDate());
                    tableDetails.setNumberOfRecords(patients.size());
                    tableDetails.setNumberOfNewlyCreatedRecords((int) patients.stream()
                            .filter(i -> !i.getSyncStatus().equals(SyncStatus.NOT_SYNC)).count());
                    syncTableDetailsList.add(tableDetails);
                    break;
                case CLINIC:

                    List<ClinicView> clinicViews =
                            clinicService.getClinicDataByMaxTime(maxSyncTime.getMaxClinicTableSyncDate());

                    tableDetails.setDateAndTimeLastSynced(maxSyncTime.getMaxClinicTableSyncDate());
                    tableDetails.setTableDescription("Clinic Table");
                    tableDetails.setTableName(String.valueOf(CLINIC));
                    tableDetails.setNumberOfRecords(clinicViews.size());
                    tableDetails.setNumberOfNewlyCreatedRecords(Math.toIntExact(clinicViews.stream()
                            .filter(i -> !i.getSyncStatus().equals(SyncStatus.NOT_SYNC)).count()));
                    syncTableDetailsList.add(tableDetails);
                    break;
                case PHARMACY:
                    List<PharmacyView> pharmacyViews =
                            pharmacyService.getPharmacyDataByMaxTime(maxSyncTime.getMaxPharmacyTableSyncDate());
                    tableDetails.setTableDescription("Pharmacy Table");
                    tableDetails.setTableName(String.valueOf(PHARMACY));
                    tableDetails.setDateAndTimeLastSynced(maxSyncTime.getMaxPharmacyTableSyncDate());
                    tableDetails.setNumberOfRecords(pharmacyViews.size());
                    tableDetails.setNumberOfNewlyCreatedRecords(Math.toIntExact(pharmacyViews.stream()
                            .filter(i -> !i.getSyncStatus().equals(SyncStatus.NOT_SYNC)).count()));
                    syncTableDetailsList.add(tableDetails);
                    break;
                case LABORATORY:
                    List<LaboratoryView> laboratoryViews =
                            laboratoryService.getLaboratoryDataByMaxTime(maxSyncTime.getMaxLaboratoryTableSyncDate());
                    tableDetails.setTableDescription("Laboratory Table");
                    tableDetails.setTableName(String.valueOf(LABORATORY));
                    tableDetails.setDateAndTimeLastSynced(maxSyncTime.getMaxLaboratoryTableSyncDate());
                    tableDetails.setNumberOfRecords(laboratoryViews.size());
                    tableDetails.setNumberOfNewlyCreatedRecords(Math.toIntExact(laboratoryViews.stream()
                            .filter(i -> !i.getSyncStatus().equals(SyncStatus.NOT_SYNC)).count()));
                    syncTableDetailsList.add(tableDetails);
                    break;
            }
        });

        return syncTableDetailsList;
    }

    public void getProducersData(List<SyncRecordParameters> syncRecordParameters) {
        logger.info("Sync Pharmacy == {}", syncRecordParameters);
        // Looping through the selected tables to be sync
        syncRecordParameters.forEach(table -> {
            String currentTable = table.getTableName();
            switch (currentTable) {
                case "PATIENT":
                    patientProducer.produceAndSyncData(table);
                    break;
                case "CLINIC":
                    clinicProducer.produceAndSyncData(table);
                    break;
                case "PHARMACY":
                    logger.info("Sync Pharmacy == {}", table);
                    pharmacyProducer.produceAndSyncData(table);
                    break;
                case "LABORATORY":
                    laboratoryProducer.produceAndSyncData(table);
                    break;
            }
        });
    }

}
