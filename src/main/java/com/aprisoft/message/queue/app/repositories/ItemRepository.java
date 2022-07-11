package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
