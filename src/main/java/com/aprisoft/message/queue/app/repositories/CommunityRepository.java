package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Province;
import com.aprisoft.message.queue.app.entities.State;
import com.aprisoft.message.queue.app.entities.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<Community, UUID> {

    List<Community> findByState(State state);

    List<Community> findByLgaAndActiveTrue(Province lga);

    @Query(
        value = "SELECT * FROM communities WHERE lga_id = :lgaId",
        nativeQuery = true
            )
    List<Community> getAllCommunityByLga(@Param("lgaId") Long lgaId);
}
