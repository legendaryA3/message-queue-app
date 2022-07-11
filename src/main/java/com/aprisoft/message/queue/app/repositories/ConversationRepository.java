package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("select c from Conversation c where c.originatorId = 0 and c.phone = ?1 " +
            "order by c.timeStamp desc")
    Conversation findRecentMessage(String phone);
}
