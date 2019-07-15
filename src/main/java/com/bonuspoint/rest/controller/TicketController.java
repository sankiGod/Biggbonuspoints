package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.QuerySummary;
import com.bonuspoint.model.TicketThread;
import com.bonuspoint.model.Tickets;
import com.bonuspoint.rest.service.TicketsService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	TicketsService service;
	
	@GetMapping("/getAllTickets")
	public List<Tickets> getAllTickets(){
		return service.getAllTickets();
	}
	
	@GetMapping("/getAllThreads")
	public List<TicketThread> getAllThreads(){
		return service.getAllThreads();
	}
	
	@PostMapping("/createTicket")
	public ResponseEntity<Tickets> createTicket(@Valid @RequestBody Tickets ticket){
		Tickets tic = service.createTicket(ticket);
		return new ResponseEntity<Tickets>(tic, HttpStatus.CREATED);
	}
	
	@PostMapping("/createThread")
	public ResponseEntity<TicketThread> createThread(@Valid @RequestBody TicketThread thread){
		TicketThread thd = service.createThread(thread);
		return new ResponseEntity<TicketThread>(thd, HttpStatus.CREATED);
	}
	
	@GetMapping("/getTicketsByCustomerId/{customerID}")
	public List<Tickets> getByCustomerId(@PathVariable String customerID){
		return service.getByCustomerId(customerID);
	}
	
	@GetMapping("/getTicketsByMerchantId/{merchantID}")
	public List<Tickets> getByMerchantId(@PathVariable String merchantID){
		return service.getByMerchantId(merchantID);
	}
	
	@GetMapping("/getQueryByTicketId/{ticketID}")
	public QuerySummary getQueryByTicketId(@PathVariable long ticketID){
		return service.getQueryByTicketId(ticketID);
	}
	
	@PutMapping("/changeStatus/{ticketID}")
	public ResponseEntity<Tickets> changeStatus(@PathVariable long ticketID, @PathParam("status") String status){
		Tickets tic = service.changeStatus(ticketID, status);
		return new ResponseEntity<Tickets>(tic, HttpStatus.OK);
	}

}
