package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.HtsNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HtsNewRepository extends JpaRepository<HtsNew, Long> {

    @Query(value = "select h from HtsNew h where (lower(h.surname) like lower(concat('%', :query, '%') ) or " +
        "lower(h.firstName) like lower(concat('%', :query, '%')) or lower(h.middleName) like lower(:query) or " +
        "lower(h.clientCode) like lower(:query)) and h.facility.id = :facility")
    Page<HtsNew> searchHtsClient(@Param("facility") Long facilityId, @Param("query") String query, Pageable pageable);

    @Query(
        value = "SELECT * FROM hts_new WHERE id = :id",
        nativeQuery = true
    )
    HtsNew getHTSDataById(@Param("id") Long id);


}
