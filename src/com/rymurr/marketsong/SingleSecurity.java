/**
 * Abstract class representing a single security of arbitrary type.
 */
package com.rymurr.marketsong;

import java.util.Date;
import java.util.Calendar;

/**
 * @author ryanmurray
 *
 */
public abstract class SingleSecurity {

	private String name;
	private Date startTime, finishTime;
	
	public SingleSecurity(String Name, int StartTime, int FinishTime){
		setName(Name);
		int sDay = StartTime%100;
		int sMonth = ((StartTime-sDay)%10000)/100;
		int sYear = (StartTime-sDay-100*sMonth)/10000;
		int fDay = FinishTime%100;
		int fMonth = ((FinishTime-fDay)%10000)/100;
		int fYear = (FinishTime-fDay-100*fMonth)/10000;
		setStartTime(sDay,sMonth-1,sYear);
		setFinishTime(fDay,fMonth-1,fYear);
		
		
	}

	public String getName() {
		return name;
	}
	
	private void setName(String Name) {
		this.name = Name;
	}

	private void setStartTime(int day, int month, int year) {
		Calendar time = Calendar.getInstance();
		time.set(year, month, day);
		this.startTime = time.getTime();
	}

	public Date getStartTime() {
		return startTime;
	}

	private void setFinishTime(int day, int month, int year) {
		Calendar time = Calendar.getInstance();
		time.set(year, month, day);
		this.finishTime = time.getTime();
	}

	public Date getFinishTime() {
		return finishTime;
	}

}
