package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.PartnerInformation;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartnerInformationRepository extends JpaRepository<PartnerInformation, Long> {
    List<PartnerInformation> findByPatient(Patient patient);

    List<PartnerInformation> findByFacility(Facility facility);

    Optional<PartnerInformation> findByUuid(String uuid);
}
