package com.kafka.dto;

public class TopUserDTO {
    private Long userId;
    private Float totalAmount;

    public TopUserDTO(Long userId, Float totalAmount) {
        this.userId = userId;
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

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "TopUserDTO [userId=" + userId + ", totalAmount=" + totalAmount + "]";
	}

    
}
