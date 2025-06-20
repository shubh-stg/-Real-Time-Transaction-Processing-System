package com.kafka.dto;

public class TopUserDTO {
    private Long userId;
    private String name;
    private Double totalAmount;
	public TopUserDTO(Long userId, String name, Double totalAmount) {
		super();
		this.userId = userId;
		this.name = name;
		this.totalAmount = totalAmount;
	}
	public TopUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "TopUserDTO [userId=" + userId + ", name=" + name + ", totalAmount=" + totalAmount + "]";
	}

   
    
}
