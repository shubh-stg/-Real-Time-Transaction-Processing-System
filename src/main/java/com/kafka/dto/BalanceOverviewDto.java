package com.kafka.dto;

public class BalanceOverviewDto {
	
	 private double currentBalance;
	    private double totalSent;
	    private double totalReceived;
	    private int totalTransactions;
	    private double averageTransaction;
	    private double largestTransaction;
	    
		public double getCurrentBalance() {
			return currentBalance;
		}
		public void setCurrentBalance(double currentBalance) {
			this.currentBalance = currentBalance;
		}
		public double getTotalSent() {
			return totalSent;
		}
		public void setTotalSent(double totalSent) {
			this.totalSent = totalSent;
		}
		public double getTotalReceived() {
			return totalReceived;
		}
		public void setTotalReceived(double totalReceived) {
			this.totalReceived = totalReceived;
		}
		public int getTotalTransactions() {
			return totalTransactions;
		}
		public void setTotalTransactions(int totalTransactions) {
			this.totalTransactions = totalTransactions;
		}
		public double getAverageTransaction() {
			return averageTransaction;
		}
		public void setAverageTransaction(double averageTransaction) {
			this.averageTransaction = averageTransaction;
		}
		public double getLargestTransaction() {
			return largestTransaction;
		}
		public void setLargestTransaction(double largestTransaction) {
			this.largestTransaction = largestTransaction;
		}
		@Override
		public String toString() {
			return "BalanceOverviewDto [currentBalance=" + currentBalance + ", totalSent=" + totalSent
					+ ", totalReceived=" + totalReceived + ", totalTransactions=" + totalTransactions
					+ ", averageTransaction=" + averageTransaction + ", largestTransaction=" + largestTransaction + "]";
		}
		public BalanceOverviewDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public BalanceOverviewDto(double currentBalance, double totalSent, double totalReceived, int totalTransactions,
				double averageTransaction, double largestTransaction) {
			super();
			this.currentBalance = currentBalance;
			this.totalSent = totalSent;
			this.totalReceived = totalReceived;
			this.totalTransactions = totalTransactions;
			this.averageTransaction = averageTransaction;
			this.largestTransaction = largestTransaction;
		}
	    
		
	    
    
}
