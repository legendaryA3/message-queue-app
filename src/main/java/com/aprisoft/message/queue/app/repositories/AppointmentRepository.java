package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Appointment;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByPatientAndDateTracked(Patient patient, LocalDate date);

    Appointment findByFacilityAndPatientAndDateTracked(Facility facility, Patient patient, LocalDate date);

    List<Appointment> findByPatient(Patient patient);

    Optional<Appointment> findByUuid(String uuid);
}
