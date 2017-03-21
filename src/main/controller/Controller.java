package main.controller;

import java.util.ArrayList;
import main.util.GoogleSearch;
import main.util.BingSearch;
import main.util.YandexSearch;
import main.util.WikileaksSearch;
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
