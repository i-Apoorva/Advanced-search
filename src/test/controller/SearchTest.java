package test.controller;

import static org.junit.Assert.*;
import java.util.ArrayList;
import main.model.SearchResult;
import org.junit.Test;
import main.util.*;

public class SearchTest {

	@Test
	public void GoogleTestSimpleSearch() throws Exception {
		SearchEngine engine = new GoogleSearch("Putin");
		
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
	public void YippyTestSimpleSearch() throws Exception {
		SearchEngine engine = new YippySearch("Erdogan");
		
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
	public void UnbubbleTestSimpleSearch() throws Exception {
		SearchEngine engine = new UnbubbleSearch("Erdogan");
		
		ArrayList<SearchResult> arr = engine.search();
		
		System.out.println(arr.get(0));
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
