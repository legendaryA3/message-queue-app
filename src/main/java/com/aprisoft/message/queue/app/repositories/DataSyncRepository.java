package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.DataSync;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DataSyncRepository extends JpaRepository<DataSync, UUID> {

    @Query(
            value = "SELECT * FROM data_sync WHERE patient_id = :patientId",
            nativeQuery = true)
    DataSync getDataSyncByPatientId (@Param("patientId") UUID patientId);

    @Query(value = "select " +
                        "(SELECT uuid_generate_v4()) as patient_id, " +
                        "max(patient_table_sync_date) as patient_table_sync_date, " +
                        "max(clinic_table_sync_date) as clinic_table_sync_date, " +
                        "max(pharmacy_table_sync_date) as pharmacy_table_sync_date, " +
                        "max(laboratory_table_sync_date) as laboratory_table_sync_date, " +
                        "(select id from facility where id = :facilityId) as facility_id " +
                    "from data_sync where facility_id = :facilityId",
            nativeQuery = true)
    DataSync maxSynDate(@Param("facilityId") Long facilityId);
}
