package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Delivery;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByPatientAndDateDelivery(Patient patient, LocalDate date);

    List<Delivery> findByPatient(Patient patient);

    Optional<Delivery> findByUuid(String uuid);
}
