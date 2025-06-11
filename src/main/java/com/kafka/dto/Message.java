package com.kafka.dto;

public class Message {

	private Long senderId ;
	private Long recieverId;
	private Float amount;
	public Message(Long senderId, Long recieverId, Float amount) {
		super();
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.amount = amount;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Message [senderId=" + senderId + ", recieverId=" + recieverId + ", amount=" + amount + "]";
	}
	
}
