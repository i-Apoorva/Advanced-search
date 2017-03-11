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
	private UserAgents agents;
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
		
		Connection connection = Jsoup.connect(new StringBuilder(NAME_DOMAIN)
				.append("/search?v%3Aproject=clusty-new&query=").append(query).toString());

		final Document doc = connection
				.userAgent(DEFAULT_USER_AGENT)
				.followRedirects(true)
				.timeout(DEFAULT_TIMEOUT)
				.get();
		
		Elements news = doc.select("ul.results").select("a.title");
		
		httpStatus = new StringBuilder(Integer.toString(connection.response().statusCode()))
				.append(" ")
				.append(connection.response().statusMessage())
				.toString();

		
		for (Element result : news){
			
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
}
