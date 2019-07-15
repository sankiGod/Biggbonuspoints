package com.bonuspoint.rest.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.QuerySummary;
import com.bonuspoint.model.TicketThread;
import com.bonuspoint.model.Tickets;
import com.bonuspoint.repository.TicketThreadRepository;
import com.bonuspoint.repository.TicketsRepository;
import com.bonuspoint.util.ErrorCodes;

@Service
public class TicketsService {

	@Autowired
	TicketsRepository ticketRepository;

	@Autowired
	TicketThreadRepository threadRepository;

	public List<Tickets> getAllTickets() {
		return ticketRepository.findAll();
	}

	public List<TicketThread> getAllThreads() {
		return threadRepository.findAll();
	}

	public Tickets createTicket(@Valid Tickets ticket) {
		return ticketRepository.save(ticket);
	}

	public TicketThread createThread(@Valid TicketThread thread) {
		try {
			long ticketId = thread.getTicketID();
			if (ticketRepository.findById(ticketId) != null) {
				return threadRepository.save(thread);
			} else
				throw new ResourceNotFoundException("Ticket", "TicketId", ticketId);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Ticket", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Ticket", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public List<Tickets> getByCustomerId(String customerID) {
		if (!ticketRepository.findByCustomerIDOrderByCreatedAtDesc(customerID).isEmpty()) {
			return ticketRepository.findByCustomerIDOrderByCreatedAtDesc(customerID);
		} else
			throw new ResourceNotFoundException("Ticket", "customerID", customerID);
	}

	public List<Tickets> getByMerchantId(String merchantID) {
		if (!ticketRepository.findByMerchantIDOrderByCreatedAtDesc(merchantID).isEmpty()) {
			return ticketRepository.findByMerchantIDOrderByCreatedAtDesc(merchantID);
		} else
			throw new ResourceNotFoundException("Ticket", "merchantID", merchantID);
	}

	public QuerySummary getQueryByTicketId(long ticketID) {
		if (!threadRepository.findByTicketIDOrderByCreatedAtAsc(ticketID).isEmpty()) {
			Tickets ticket = ticketRepository.findById(ticketID)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket", "tID", ticketID));
			List<TicketThread> threads = threadRepository.findByTicketIDOrderByCreatedAtAsc(ticketID);

			QuerySummary query = new QuerySummary();
			query.setTicketID(ticketID);
			if (ticket.getCustomerID() == null && ticket.getMerchantID() != null) {
				query.setCustomerOrMerchant("Merchant");
				query.setID(ticket.getMerchantID());
			} else {
				query.setCustomerOrMerchant("Customer");
				query.setID(ticket.getCustomerID());
			}

			query.setInitialQuery(ticket.getMessage());
			query.setSubject(ticket.getSubject());
			query.setStatus(ticket.getStatus());
			query.setPriority(ticket.getPriority());
			query.setThreads(threads);
			return query;
		} else
			throw new ResourceNotFoundException("Thread", "ticketID", ticketID);
	}

	public Tickets changeStatus(long ticketID, String status) {
		try {
			Tickets ticket = ticketRepository.findById(ticketID)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket", "tID", ticketID));

			ticket.setStatus(status);
			return ticketRepository.save(ticket);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Ticket", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Ticket", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

}
