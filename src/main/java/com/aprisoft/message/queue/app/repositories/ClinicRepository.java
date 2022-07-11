package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.controllers.vm.ClinicVM;
import com.aprisoft.message.queue.app.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    @Query(value = "SELECT * FROM clinic WHERE last_modified = :lastModified",
            nativeQuery = true)
    List<Clinic> findByLastModified (@Param("lastModified") LocalDateTime lastModified);

    @Query(value = "SELECT id FROM clinic WHERE id = :id",
            nativeQuery = true)
    Optional<ClinicVM> getClinicById (@Param("id") Long od);

}
