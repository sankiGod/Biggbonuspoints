package com.bonuspoint.model;

import java.util.List;

public class QuerySummary {

	private long ticketID;
	private String customerOrMerchant;
	private String ID;
	private String subject;
	private String initialQuery;
	private String status;
	private String priority;
	private List<TicketThread> threads;
	
	public long getTicketID() {
		return ticketID;
	}
	public void setTicketID(long ticketID) {
		this.ticketID = ticketID;
	}
	public String getCustomerOrMerchant() {
		return customerOrMerchant;
	}
	public void setCustomerOrMerchant(String customerOrMerchant) {
		this.customerOrMerchant = customerOrMerchant;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getInitialQuery() {
		return initialQuery;
	}
	public void setInitialQuery(String initialQuery) {
		this.initialQuery = initialQuery;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public List<TicketThread> getThreads() {
		return threads;
	}
	public void setThreads(List<TicketThread> threads) {
		this.threads = threads;
	}
	
}
