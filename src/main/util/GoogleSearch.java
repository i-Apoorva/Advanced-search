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
	private UserAgents agents;
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
		
		connection = Jsoup.connect(new StringBuilder(NAME_DOMAIN)
				.append("/search?q=").append(query).toString());

		final Document doc = connection
				.userAgent(DEFAULT_USER_AGENT)
				.followRedirects(true)
				.timeout(DEFAULT_TIMEOUT)
				.get();
		
		httpStatus = new StringBuilder(Integer.toString(connection.response().statusCode()))
				.append(" ")
				.append(connection.response().statusMessage())
				.toString();

		for (Element result : doc.select("h3.r a")){

			String title = getNewsTitle(result);
			String url = getNewsURL(result);

			array.add(new SearchResult(title, url, httpStatus));
		}
		
		return array;
	}
	
	private String getNewsTitle(Element e){
		return e.text();
	}
	
	private String getNewsURL(Element e){
		return e.attr("href");
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
