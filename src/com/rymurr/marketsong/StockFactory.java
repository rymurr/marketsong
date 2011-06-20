package com.rymurr.marketsong;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.google.gson.Gson;
/**
 * 
 */

/**
 * @author ryanmurray
 *
 */


public class StockFactory {
	
	public static SingleStock create(String name, int startTime, int endTime) {
		SingleStock retVal = new SingleStock(name, startTime, endTime);
		setStats(retVal, startTime, endTime);
		return retVal;
	}

	private static String[] makeStrings(String name, int startTime, int endTime){
		String FRONT = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22";
		String MID1 = "%22%20and%20startDate%20%3D%20%22";
		String MID2 = "%22%20and%20endDate%20%3D%20%22";
		String END = "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
		Integer sDay = startTime%100;
		Integer sMonth = ((startTime-sDay)%10000)/100;
		Integer sYear = (startTime-sDay-100*sMonth)/10000;
		Integer fDay = endTime%100;
		Integer fMonth = ((endTime-fDay)%10000)/100;
		Integer fYear = (endTime-fDay-100*fMonth)/10000;
		
		Integer numYears = fYear - sYear;
		String request = "";
		for(int i=0;i<numYears-1;++i){
			String sDate = String.format("%04d", sYear+i) + "-" + String.format("%02d", sMonth) + "-" + String.format("%02d", sDay);
			String fDate = String.format("%04d", sYear+i+1) + "-" + String.format("%02d", sMonth) + "-" + String.format("%02d", sDay-1);
			request = request + FRONT + name + MID1 + sDate + MID2 + fDate + END + "&&&";
		}
		String sDate = String.format("%04d", sYear+numYears-1) + "-" + String.format("%02d", sMonth) + "-" + String.format("%02d", sDay);
		String fDate = String.format("%04d", fYear) + "-" + String.format("%02d", fMonth) + "-" + String.format("%02d", fDay);
		request = request + FRONT + name + MID1 + sDate + MID2 + fDate + END;
		return request.split("&&&");
	}
	
	private static InputStream getURL(String url){
		CacheConfig cacheConfig = new CacheConfig();  
		cacheConfig.setMaxCacheEntries(10000);
		cacheConfig.setMaxObjectSizeBytes(16777216);
		HttpClient httpClient = new CachingHttpClient(new DefaultHttpClient(), cacheConfig);
        URI uri;
        InputStream data = null;
        try {
            uri = new URI(url);
            HttpGet method = new HttpGet(uri);
            HttpResponse response = httpClient.execute(method);
            data = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
	}
	
	private static JSONQuotes runJSONParser(InputStream input) throws IOException{
        Reader r = new InputStreamReader(input);
        return new Gson().fromJson(r, JSONQuotes.class);
    }
	
	private static List<Quote> getJSON(String name, int startTime, int endTime) throws IOException{

		String[] request = makeStrings(name,startTime,endTime);


		List<Quote> quotes = new ArrayList<Quote>();
		for (int i=0;i<request.length;++i){
			InputStream JSONRes = getURL(request[i]);
			//InputStream JSONRes = new FileInputStream("json.dat");
			JSONQuotes output = runJSONParser(JSONRes);
			quotes.addAll(output.getQuery().getResults().getQuote());
		}

		return quotes;
	}
	
	private static void setStats(SingleStock x, int startTime, int endTime){
		try {
			List<Quote> quotes = getJSON(x.getName(), startTime, endTime);
			x.setClose(getClose(quotes));
			x.setHigh(getHigh(quotes));
			x.setLow(getLow(quotes));
			x.setVolume(getVolume(quotes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Double> getHigh(List<Quote> quotes){
		ArrayList<Double> retVal = new ArrayList<Double>();
		for (int i=0;i<quotes.size();++i){
			retVal.add(quotes.get(i).getHigh());
		}
		return retVal;
	}
	
	private static ArrayList<Double> getLow(List<Quote> quotes){
		ArrayList<Double> retVal = new ArrayList<Double>();
		for (int i=0;i<quotes.size();++i){
			retVal.add(quotes.get(i).getLow());
		}
		return retVal;
	}
	
	private static ArrayList<Double> getClose(List<Quote> quotes){
		ArrayList<Double> retVal = new ArrayList<Double>();
		for (int i=0;i<quotes.size();++i){
			retVal.add(quotes.get(i).getAdj_Close());
		}
		return retVal;
	}
	
	private static ArrayList<Integer> getVolume(List<Quote> quotes){
		ArrayList<Integer> retVal = new ArrayList<Integer>();
		for (int i=0;i<quotes.size();++i){
			retVal.add(quotes.get(i).getVolume());
		}
		return retVal;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] out = makeStrings("UBS", 20090609, 20100310);

		for(int i=0;i<out.length;++i){
			System.out.println(out[i]);
		}
		try {
			System.out.println(getJSON("UBS", 20090609, 20100310));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}

