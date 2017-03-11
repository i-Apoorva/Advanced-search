package main.controller;

import java.util.ArrayList;
import main.util.GoogleSearch;
import main.util.BingSearch;
import main.util.YippySearch;
import main.util.SearchEngine;
import main.model.SearchResult;

public class Controller {

	private GoogleSearch google;
	private BingSearch bing;
	private YippySearch yippy; 
	
	public ArrayList<SearchResult> googleSearch(String query) throws Exception{
		google = new GoogleSearch(query);
		return google.search();
	}
	
	public ArrayList<SearchResult> bingSearch(String query) throws Exception{
		bing = new BingSearch(query);
		return bing.search();
	}
	
	public ArrayList<SearchResult> yippySearch(String query) throws Exception{
		yippy = new YippySearch(query);
		return yippy.search();
	}
	
}
