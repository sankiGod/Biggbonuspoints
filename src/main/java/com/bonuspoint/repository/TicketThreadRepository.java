package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.TicketThread;

@Repository
public interface TicketThreadRepository extends JpaRepository<TicketThread, Long> {

	List<TicketThread> findByTicketIDOrderByCreatedAtAsc(long ticketID);
}
