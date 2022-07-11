package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Covid19Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Covid19VaccinationRepository extends JpaRepository<Covid19Vaccination, UUID>, JpaSpecificationExecutor<Covid19Vaccination> {
}
