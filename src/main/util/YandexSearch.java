package main.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;

public final class YandexSearch extends SearchEngine {
	
	private static final String DOMAIN_NAME = "https://www.yandex.com";
	private ArrayList<SearchResult> array;
	private Connection connection;
	private String query;
	private String httpStatus;
	
	public YandexSearch(String query){
		setQuery(query);
		setUserAgent(DEFAULT_USER_AGENT);
		setPage(1);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public YandexSearch(String query, String userAgent){
		setUserAgent(userAgent);
		setPage(1);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public YandexSearch(String query, int page){
		setQuery(query);
		setPage(page);
		setUserAgent(DEFAULT_USER_AGENT);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public YandexSearch(String query, int page, int timeout){
		setQuery(query);
		setPage(page);
		setUserAgent(DEFAULT_USER_AGENT);
		setTimeout(timeout);
	}
	
	public YandexSearch(String query, String userAgent, int page){
		setQuery(query);
		setUserAgent(userAgent);
		setPage(page);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public YandexSearch(String query, String userAgent, int page, int timeout){
		setQuery(query);
		setUserAgent(userAgent);
		setPage(page);
		setTimeout(timeout);
	}


	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		//Get Connect to the Server
		connection = Jsoup.connect(new StringBuilder(DOMAIN_NAME)
								.append("/yandsearch?text=")
								.append(query)
								.append("&p=")
								.append(page)
								.append("lr=10136")
								.toString());
		
		//Get HTML Document
		Document doc = 	connection
        				.userAgent(userAgent)
        				.followRedirects(true)
        				.timeout(timeout)
        				.get();
        
        Elements main = doc.select("ul.serp-list");
        
        Elements primary = main.select("li.serp-item");
        
        //Get HTTP Status
        httpStatus = getHttpStatus(connection);

        
        for (Element result : primary){
        	
        	String title = getSearchTitle(result);
        	String url = getSearchURL(result);
        	
        	
        	array.add(new SearchResult(title, url, httpStatus));
       }
        
       
       return array;
		
	}
	
	/*
	 * Return the search title
	 * 
	 * @param e an HTML Element 
	 * @return		String that represent the Title
	 */
	private String getSearchTitle(Element e){
		return e.select("a.link").text();
	}
	
	/*
	 * Return the search URL
	 * 
	 * @param e an HTML Element
	 * @return		String that represent URL
	 */
	private String getSearchURL(Element e){
		return e.select("a.link").attr("href");
	}
	/*
	 * Return the Http status
	 * @param connection an Connection Object
	 * @return		String that represent the http status 
	 */
	private String getHttpStatus(Connection connection){
		
        return new StringBuilder(Integer.toString(connection.response().statusCode()))
				.append(" ")
				.append(connection.response().statusMessage())
				.toString();
	}

	//@Override
	/*public ArrayList<SearchResult> search(String query, int max) throws Exception {
		// TODO Auto-generated method stub
		
	}*/

}

