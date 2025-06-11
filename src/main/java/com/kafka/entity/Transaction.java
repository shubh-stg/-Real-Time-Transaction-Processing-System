package com.kafka.entity;

import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long senderId;
	
	private Long recieverId;
	
	private Float amount ;
	
	private LocalDateTime timestamp;
	
	@Enumerated(EnumType.STRING)
	private TransactStatus status;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long id, Long senderId, Long recieverId, Float amount, LocalDateTime timestamp,
			TransactStatus status) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.amount = amount;
		this.timestamp = timestamp;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public TransactStatus getStatus() {
		return status;
	}

	public void setStatus(TransactStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", senderId=" + senderId + ", recieverId=" + recieverId + ", amount=" + amount
				+ ", timestamp=" + timestamp + ", status=" + status + "]";
	}

}
	

