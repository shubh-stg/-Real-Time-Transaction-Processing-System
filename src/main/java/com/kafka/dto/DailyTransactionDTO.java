package com.kafka.dto;

public class DailyTransactionDTO {
    private String date;
    private float amount; 



	public DailyTransactionDTO(String date, float amount) {
		super();
		this.date = date;
		this.amount = amount;
	}

	public DailyTransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DailyTransactionDTO [date=" + date + ", amount=" + amount + "]";
	}

}
