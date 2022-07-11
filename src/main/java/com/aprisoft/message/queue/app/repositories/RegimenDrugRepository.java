package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Regimen;
import com.aprisoft.message.queue.app.entities.RegimenDrug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegimenDrugRepository extends JpaRepository<RegimenDrug, Long> {
    List<RegimenDrug> findByRegimen(Regimen regimen);
}
