package com.example.question1.Entity;

class Rating {

	private int count;
	private double rate;	


	public Rating() {
	}
	public Rating(int count, double rate) {
		this.count = count;
		this.rate = rate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}		
}