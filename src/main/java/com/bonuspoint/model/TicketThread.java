package com.bonuspoint.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bp_ticket_thread")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class TicketThread {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="th_Id")
	private long thId;
	
	@Column(name="ticket_ID")
	private long ticketID;
	
	@Column(name="thread_message")
	@Lob
	private String threadMessage;
	
	@Column(name="sender")
	private String sender;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public long getThId() {
		return thId;
	}

	public void setThId(long thId) {
		this.thId = thId;
	}

	public long getTicketID() {
		return ticketID;
	}

	public void setTicketID(long ticketID) {
		this.ticketID = ticketID;
	}

	public String getThreadMessage() {
		return threadMessage;
	}

	public void setThreadMessage(String threadMessage) {
		this.threadMessage = threadMessage;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
