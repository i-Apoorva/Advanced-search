package main.util;

import java.util.ArrayList;

import main.model.SearchResult;

public abstract class SearchEngine{
	
	protected static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	
	protected static final Integer DEFAULT_TIMEOUT = 8000;
	
	protected Integer max;
	
	public void setMax(Integer max){
		this.max = max;
	}
	
	public Integer getMax(){
		return this.max;
	}
	
	public abstract ArrayList<SearchResult> search() throws Exception;
	
	//public abstract ArrayList<SearchResult> search(String query, int max) throws Exception;


}
