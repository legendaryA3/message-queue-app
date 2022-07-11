package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.DrugTherapy;
import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DrugTherapyRepository extends JpaRepository<DrugTherapy, Long> {
    DrugTherapy findByPatientAndDateVisit(Patient patient, LocalDate date);

    DrugTherapy findByFacilityAndPatientAndDateVisit(Facility facility, Patient patient, LocalDate date);

    List<DrugTherapy> findByPatient(Patient patient);
}
