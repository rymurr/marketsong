/**
 * 
 */
package com.rymurr.marketsong;

import java.util.ArrayList;

/**
 * @author ryanmurray
 *
 */
public class SingleStock extends SingleSecurity {
	
	private ArrayList<Double> close, high, low;
	private ArrayList<Integer> volume;

	public SingleStock(String Name, int StartTime, int FinishTime) {
		super(Name, StartTime, FinishTime);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setClose(ArrayList<Double> close) {
		this.close = close;
	}

	public ArrayList<Double> getClose() {
		return close;
	}

	public void setHigh(ArrayList<Double> high) {
		this.high = high;
	}

	public ArrayList<Double> getHigh() {
		return high;
	}

	public void setLow(ArrayList<Double> low) {
		this.low = low;
	}

	public ArrayList<Double> getLow() {
		return low;
	}

	public void setVolume(ArrayList<Integer> volume) {
		this.volume = volume;
	}

	public ArrayList<Integer> getVolume() {
		return volume;
	}

}
