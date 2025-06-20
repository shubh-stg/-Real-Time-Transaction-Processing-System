package com.kafka.dto;

public class BasicAdminAnalytics {
	
	long usercount;
	long transactioncount;
	double transactionvolume;
	public long getUsercount() {
		return usercount;
	}
	public void setUsercount(long usercount) {
		this.usercount = usercount;
	}
	public long getTransactioncount() {
		return transactioncount;
	}
	public void setTransactioncount(long transactioncount) {
		this.transactioncount = transactioncount;
	}
	public double getTransactionvolume() {
		return transactionvolume;
	}
	public void setTransactionvolume(double transactionvolume) {
		this.transactionvolume = transactionvolume;
	}
	public BasicAdminAnalytics(long usercount, long transactioncount, double transactionvolume) {
		super();
		this.usercount = usercount;
		this.transactioncount = transactioncount;
		this.transactionvolume = transactionvolume;
	}
	public BasicAdminAnalytics() {
		super();
		
	}
	@Override
	public String toString() {
		return "BasicAdminAnalytics [usercount=" + usercount + ", transactioncount=" + transactioncount
				+ ", transactionvolume=" + transactionvolume + "]";
	}
	
}
