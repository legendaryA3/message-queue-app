package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Patient;
import com.aprisoft.message.queue.app.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatient(Patient patient);
}
