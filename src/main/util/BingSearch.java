package main.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;

public final class BingSearch extends SearchEngine {
	
	private static final String DOMAIN_NAME = "https://bing.com/";
	private ArrayList<SearchResult> array;
	private Connection connection;
	private String query;
	private String httpStatus;
	
	public BingSearch(String query){
		setQuery(query);
		setUserAgent(DEFAULT_USER_AGENT);
		setPage(1);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public BingSearch(String query, String userAgent){
		setUserAgent(userAgent);
		setPage(1);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public BingSearch(String query, int page){
		setQuery(query);
		setPage(page);
		setUserAgent(DEFAULT_USER_AGENT);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public BingSearch(String query, int page, int timeout){
		setQuery(query);
		setPage(page);
		setUserAgent(DEFAULT_USER_AGENT);
		setTimeout(timeout);
	}
	
	public BingSearch(String query, String userAgent, int page){
		setQuery(query);
		setUserAgent(userAgent);
		setPage(page);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public BingSearch(String query, String userAgent, int page, int timeout){
		setQuery(query);
		setUserAgent(userAgent);
		setPage(page);
		setTimeout(timeout);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see main.util.SearchEngine#search()
	 */
	
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		// Connect to Server
		connection = Jsoup.connect(new StringBuilder(DOMAIN_NAME)
								.append("?q=")
								.append(query)
								.append("&&filt=rf")
								.append("&first=")
								.append(page)
								.toString());
		
		// Get HTML document
        Document doc = 	connection
        				.userAgent(userAgent)
        				.followRedirects(true)
        				.timeout(timeout)
        				.get();
        
        Elements main = doc.select("ol#b_results");
        
        Elements primary = main.select("li.b_algo");
        
        //Get HttpStatus
        httpStatus = getHttpStatus(connection);

        //Get title and url to all results
        for (Element result : primary){
        	
        	String title = getSearchTitle(result);
        	String url = getSearchURL(result);
        	
        	
        	//add to ArrayList
        	array.add(new SearchResult(title, url, httpStatus));
       }
        
       
       return array;
		
	}
	
	/*
	 * Return the news title
	 * 
	 * @param e an HTML Element 
	 * @return		String that represent the Title
	 */
	private String getSearchTitle(Element e){
		return e.text();
	}
	/*
	 * Return the news URL
	 * 
	 * @param e an HTML Element
	 * @return		String that represent URL
	 */
	private String getSearchURL(Element e){
		return e.attr("href");
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
}

