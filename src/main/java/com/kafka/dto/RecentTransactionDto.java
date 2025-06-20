package com.kafka.dto;

import java.time.LocalDateTime;

import com.kafka.entity.TransactStatus;

public class RecentTransactionDto {
    private LocalDateTime timestamp;
    private String type; // "Sent" or "Received"
    private String counterpartyName;
    private Float amount;
    private TransactStatus status;
	public RecentTransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecentTransactionDto(LocalDateTime timestamp, String type, String counterpartyName, Float amount,
			TransactStatus status) {
		super();
		this.timestamp = timestamp;
		this.type = type;
		this.counterpartyName = counterpartyName;
		this.amount = amount;
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCounterpartyName() {
		return counterpartyName;
	}
	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public TransactStatus getStatus() {
		return status;
	}
	public void setStatus(TransactStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RecentTransactionDto [timestamp=" + timestamp + ", type=" + type + ", counterpartyName="
				+ counterpartyName + ", amount=" + amount + ", status=" + status + "]";
	}
    
    
}
