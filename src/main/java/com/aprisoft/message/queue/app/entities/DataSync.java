package com.aprisoft.message.queue.app.entities;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "data_sync")
@Data
public class DataSync {

    @Id
    private UUID patientId;
    private Long facilityId;
    private LocalDateTime patientTableSyncDate;
    private LocalDateTime clinicTableSyncDate;
    private LocalDateTime laboratoryTableSyncDate;
    private LocalDateTime pharmacyTableSyncDate;

    @EntityView(DataSync.class)
    public interface MaxSyncTime {

        @Mapping("MAX(patientTableSyncDate)")
        LocalDateTime getMaxPatientTableSyncDate();
        @Mapping("MAX(laboratoryTableSyncDate)")
        LocalDateTime getMaxLaboratoryTableSyncDate();
        @Mapping("MAX(clinicTableSyncDate)")
        LocalDateTime getMaxClinicTableSyncDate();
        @Mapping("MAX(pharmacyTableSyncDate)")
        LocalDateTime getMaxPharmacyTableSyncDate();

    }
}
