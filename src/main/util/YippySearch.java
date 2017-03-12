package main.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;


public class YippySearch extends SearchEngine {
	
	private static final String NAME_DOMAIN = "http://yippy.com";
	private ArrayList<SearchResult> array;
	private Connection connection;
	private String query;
	private String httpStatus;

	public YippySearch(String query){
		this.query = query;
	}
	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		//Get Connect to Server
		Connection connection = Jsoup.connect(new StringBuilder(NAME_DOMAIN)
				.append("/search?v%3Aproject=clusty-new&query=").append(query).toString());
		
		//Get HTML Document
		final Document doc = connection
				.userAgent(DEFAULT_USER_AGENT)
				.followRedirects(true)
				.timeout(DEFAULT_TIMEOUT)
				.get();
		
		httpStatus = getHttpStatus(connection);
		
		Elements news = doc.select("ul.results").select("a.title");
		
		for (Element result : news){
			
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
		return e.text();		
	}
	
	/*
	 * Return the search URL
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
