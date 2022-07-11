package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.SelfTesting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SelfTestingRepository extends JpaRepository<SelfTesting, UUID> {

    @Query(value = "select h from SelfTesting h where (lower(h.surname) like lower(concat('%', :query, '%') ) or " +
        "lower(h.firstName) like lower(concat('%', :query, '%')) or lower(h.middleName) like lower(:query) or " +
        "lower(h.clientCode) like lower(:query)) and h.facility.id = :facility")
    Page<SelfTesting> searchSelfTestClient(@Param("facility") Long facilityId, @Param("query") String query, Pageable pageable);

}
