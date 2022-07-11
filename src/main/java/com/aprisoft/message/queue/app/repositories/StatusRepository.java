package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
