package com.kafka.dto;

public class StatusRatioDTO {
    private String status;
    private long count;


	public StatusRatioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatusRatioDTO(String status, long count) {
		super();
		this.status = status;
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StatusRatioDTO [status=" + status + ", count=" + count + "]";
	}

    
}
