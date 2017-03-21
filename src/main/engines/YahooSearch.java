package main.engines;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;

public final class YahooSearch extends SearchEngine {
	
	private static final String DOMAIN_NAME = "https://search.yahoo.com/";
	private ArrayList<SearchResult> array;
	private Connection connection;
	private String query;
	private String httpStatus;
	
	public YahooSearch(String query){
		this.query = query;
	}

	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		//Get Connect to the Server
		connection = Jsoup.connect(new StringBuilder(DOMAIN_NAME)
        				.append("?p=").append(query).toString()); 	
		
		//Get HTML Document
        Document doc = 	connection
        				.userAgent(DEFAULT_USER_AGENT)
        				.followRedirects(true)
        				.timeout(DEFAULT_TIMEOUT)
        				.get();
        
        Elements main = doc.select("#ce50");
        
        //Elements primary = main.select("li");

        //Get HTTP status
        httpStatus = getHttpStatus(connection);
        
        for (Element result : main){
        	
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
		return e.select("h3.title").text();
	}
	
	/*
	 * Return the search URL
	 * 
	 * @param e an HTML Element
	 * @return		String that represent URL
	 */
	private String getSearchURL(Element e){
		return e.select("span").text();
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

