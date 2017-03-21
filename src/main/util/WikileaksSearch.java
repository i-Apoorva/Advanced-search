package main.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import main.model.SearchResult;

public class WikileaksSearch extends SearchEngine{
	
	private static final String NAME_DOMAIN = "https://search.wikileaks.org";
	private Connection connection;
	private ArrayList<SearchResult> array;
	private String query;
	private String httpStatus;
	
	public WikileaksSearch(String query){
		setQuery(query);
		setUserAgent(DEFAULT_USER_AGENT);
		setPage(DEFAULT_PAGE);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public WikileaksSearch(String query, String userAgent){
		setUserAgent(userAgent);
		setPage(DEFAULT_PAGE);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public WikileaksSearch(String query, int page){
		setQuery(query);
		setPage(page);
		setUserAgent(DEFAULT_USER_AGENT);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public WikileaksSearch(String query, int page, int timeout){
		setQuery(query);
		setPage(page);
		setUserAgent(DEFAULT_USER_AGENT);
		setTimeout(timeout);
	}
	
	public WikileaksSearch(String query, String userAgent, int page){
		setQuery(query);
		setUserAgent(userAgent);
		setPage(page);
		setTimeout(DEFAULT_TIMEOUT);
	}
	
	public WikileaksSearch(String query, String userAgent, int page, int timeout){
		setQuery(query);
		setUserAgent(userAgent);
		setPage(page);
		setTimeout(timeout);
	}


	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		//Connect to Server
		connection = Jsoup.connect(new StringBuilder(NAME_DOMAIN)
					.append("/?qpage=")
					.append(page)
					.append("&q=")
					.append(query)
					.append("&sort=0#results")
					.toString());
		//Get HTML Document
		final Document doc = connection
					.userAgent(userAgent)
					.followRedirects(true)
					.timeout(timeout)
					.get();

		httpStatus = getHttpStatus(connection);
		
		for (Element result: doc.select("div.result")){
			
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
		return e.select("div.info").text();
	}

	/*
	 * Return the news URL
	 * 
	 * @param e an HTML Element
	 * @return		String that represent URL
	 */
	private String getSearchURL(Element e){
		//return e.attr("href");
		return e.select("a").attr("href");
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
