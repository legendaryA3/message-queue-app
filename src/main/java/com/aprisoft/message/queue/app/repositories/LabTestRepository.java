package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.LabTest;
import com.aprisoft.message.queue.app.entities.LabTestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {

    List<LabTest> findByLabTestCategory(LabTestCategory category);
}
