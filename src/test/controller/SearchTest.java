package test.controller;

import static org.junit.Assert.*;
import java.util.ArrayList;

import main.engines.BingSearch;
import main.engines.GoogleSearch;
import main.engines.SearchEngine;
import main.engines.WikileaksSearch;
import main.engines.YahooSearch;
import main.engines.YandexSearch;
import main.model.SearchResult;
import org.junit.Test;
import main.util.*;

public class SearchTest {

	@Test
	public void GoogleTestSimpleSearch() throws Exception {
		SearchEngine engine = new GoogleSearch("Putin", 4);
		
		ArrayList<SearchResult> arr = engine.search();
		
		System.out.println(arr.get(0));
		assertFalse(arr.isEmpty());
		
	}
	
	@Test
	public void BingTestSimpleSearch() throws Exception {
		SearchEngine engine = new BingSearch("Siria domain:www.g1.com.br");
		
		ArrayList<SearchResult> arr = engine.search();
		
		System.out.println(arr.get(0));
		assertFalse(arr.isEmpty());
		
	}
	
	
	@Test
	public void YandexTestSimpleSearch() throws Exception {
		SearchEngine engine = new YandexSearch("Erdogan");
		
		ArrayList<SearchResult> arr = engine.search();
		
		System.out.println(arr.get(0));
		assertFalse(arr.isEmpty());
	}
	
	@Test
	public void YahooTestSimpleSearch() throws Exception {
		SearchEngine engine = new YahooSearch("Erdogan");
		
		ArrayList<SearchResult> arr = engine.search();
		
		System.out.println(arr.get(0));
		System.out.println(arr.get(1));
		assertFalse(arr.isEmpty());
	}
	
	@Test
	public void WikileaksTestSimpleSearch() throws Exception {
		SearchEngine engine = new WikileaksSearch("Siria");
		
		ArrayList<SearchResult> arr = engine.search();
		
		System.out.println(arr.get(0));
		assertFalse(arr.isEmpty());
	}


}
