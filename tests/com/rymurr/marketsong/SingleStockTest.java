/**
 * 
 */
package com.rymurr.marketsong;

/**
 * @author ryanmurray
 *
 */
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.rymurr.marketsong.SingleStock;
import com.rymurr.marketsong.StockFactory;

public class SingleStockTest {

	@Test
	public void testName() {
        SingleStock stock = StockFactory.create("UBS",20080608,20110608);
        assertEquals("UBS", stock.getName());
	}
	
	@Test
	public void testStart() {
        SingleStock stock = StockFactory.create("UBS",20080608,20110608);
		Calendar sTime = Calendar.getInstance();
		sTime.set(2008, 5, 8);
        assertEquals(sTime.getTime(),stock.getStartTime());
	}
	
	@Test
	public void testFinish() {
        SingleStock stock = StockFactory.create("UBS",20080608,20110608);
		Calendar fTime = Calendar.getInstance();
		fTime.set(2011, 5, 8);
        assertEquals(fTime.getTime(),stock.getFinishTime());
	}
	
	@Test
	public void testVol(){
		SingleStock stock = StockFactory.create("UBS",20080608,20110608);
		assertEquals(stock.getVolume().get(0).intValue(),5476100);
	}

}
