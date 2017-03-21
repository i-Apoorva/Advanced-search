package main.controller;

import java.util.ArrayList;

import main.engines.BingSearch;
import main.engines.GoogleSearch;
import main.engines.WikileaksSearch;
import main.engines.YandexSearch;
import main.model.SearchResult;

public class Controller {

	private GoogleSearch google;
	private BingSearch bing;
	private YandexSearch yandex;
	private WikileaksSearch wikileaks;
	
	public ArrayList<SearchResult> googleSearch(String query) throws Exception{
		google = new GoogleSearch(query);
		return google.search();
	}
	
	public ArrayList<SearchResult> bingSearch(String query) throws Exception{
		bing = new BingSearch(query);
		return bing.search();
	}
	
	public ArrayList<SearchResult> yandexSearch(String query) throws Exception{
		yandex = new YandexSearch(query);
		return yandex.search();
	}
	
	public ArrayList<SearchResult> wikileaksSearch(String query) throws Exception{
		wikileaks = new WikileaksSearch(query);
		return wikileaks.search();
	}
	
}
