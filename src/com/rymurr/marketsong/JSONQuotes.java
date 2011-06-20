/**
 * 
 */
package com.rymurr.marketsong;

import java.util.List;

/**
 * @author ryanmurray
 *
 */
public class JSONQuotes {
	private Query query;

	public void setQuery(Query query) {
		this.query = query;
	}

	public Query getQuery() {
		return query;
	}
	
	public static class Query {
		private Quotes results;
		private String count;
		private String created;
		private String lang;

		public void setCount(String count) {
			this.count = count;
		}

		public String getCount() {
			return count;
		}
		
		public void setResults(Quotes results) {
			this.results = results;
		}

		public Quotes getResults() {
			return results;
		}

		public void setCreated(String created) {
			this.created = created;
		}

		public String getCreated() {
			return created;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

		public String getLang() {
			return lang;
		}
	}
	
	public class Quotes {
		private List<Quote> quote;

		public void setQuote(List<Quote> quote) {
			this.quote = quote;
		}

		public List<Quote> getQuote() {
			return quote;
		}
	}
}