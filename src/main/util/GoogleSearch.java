package main.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;


public final class GoogleSearch extends SearchEngine {
	
	private static final String NAME_DOMAIN = "https://google.com";
	private Connection connection;
	private ArrayList<SearchResult> array;
	private String query;
	private String httpStatus;
	
	public GoogleSearch(String query){
		this.query = query;
	}

	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		//Connect to Server
		connection = Jsoup.connect(new StringBuilder(NAME_DOMAIN)
				.append("/search?q=").append(query).toString());
		
		//Get HTML Document
		final Document doc = connection
				.userAgent(DEFAULT_USER_AGENT)
				.followRedirects(true)
				.timeout(DEFAULT_TIMEOUT)
				.get();
		
		httpStatus = getHttpStatus(connection);
		
		//Get title and url to all results
		for (Element result : doc.select("h3.r a")){

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
	//@Override
	public ArrayList<SearchResult> search(String query, int max) throws Exception {
		
		array = new ArrayList();

		final Document doc = Jsoup.connect(new StringBuilder(NAME_DOMAIN)
				.append("/search?q=").append(query).append("&num").append(max).toString())
				.userAgent(DEFAULT_USER_AGENT)
				.followRedirects(true)
				.timeout(DEFAULT_TIMEOUT)
				.get();

		for (Element result : doc.select("h3.r a")){

			final String title = result.text();
			final String url = result.attr("href");

			array.add(new SearchResult(title, url));
		}
		
		return array;
	}
}
