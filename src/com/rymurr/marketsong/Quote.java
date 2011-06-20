package com.rymurr.marketsong;

public class Quote {
	private String date;
	private Double High, Low, Adj_Close;
	private Integer Volume;
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setAdj_Close(Double adj_Close) {
		Adj_Close = adj_Close;
	}
	public Double getAdj_Close() {
		return Adj_Close;
	}
	public void setHigh(Double high) {
		High = high;
	}
	public Double getHigh() {
		return High;
	}
	public void setLow(Double low) {
		Low = low;
	}
	public Double getLow() {
		return Low;
	}
	public void setVolume(Integer volume) {
		Volume = volume;
	}
	public Integer getVolume() {
		return Volume;
	}
	
}