package main.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;

public final class UnbubbleSearch extends SearchEngine {
	
	private static final String DOMAIN_NAME = "https://www.unbubble.eu";
	private ArrayList<SearchResult> array;
	private Connection connection;
	private String query;
	private String httpStatus;
	
	public UnbubbleSearch(String query){
		this.query = query;
	}

	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		//Get Connect to the Server
		connection = Jsoup.connect(new StringBuilder(DOMAIN_NAME)
								.append("/?focus=web&q=").append(query).toString());
		
		//Get HTML Document
        Document doc = 	connection
        				.userAgent(DEFAULT_USER_AGENT)
        				.followRedirects(true)
        				.timeout(DEFAULT_TIMEOUT)
        				.get();
        
        Elements main = doc.select("ol.result-list");
        
        Elements primary = main.select("li");
        
        httpStatus = getHttpStatus(connection);

      //Get title and url to all results
        for (Element result : primary){
        	
        	String title = getSearchTitle(result);
        	String url = getSearchURL(result);
        	
        	
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
		return e.select("h3.title").text();
	}
	
	/*
	 * Return the news URL
	 * 
	 * @param e an HTML Element
	 * @return		String that represent URL
	 */
	private String getSearchURL(Element e){
		return e.select("div.detailsUrl").text();
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

