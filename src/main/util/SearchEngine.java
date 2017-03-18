package main.util;

import java.util.ArrayList;

import main.model.SearchResult;

public abstract class SearchEngine{
	 
	protected static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	protected static final Integer DEFAULT_TIMEOUT = 8000;
	protected String userAgent;
	protected String query;
	protected Integer page;
	protected Integer timeout;
	
	void setQuery(String query){
		this.query = query;
	}
	
	void setPage(int page){
		this.page = page;
	}
	
	void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}
	
	void setTimeout(int timeout){
		this.timeout = timeout;
	}
	
	/*
	 * Return a array that represent the Search results
	 * @see main.model.SearchResult#search()
	 */
	public abstract ArrayList<SearchResult> search() throws Exception;
}
