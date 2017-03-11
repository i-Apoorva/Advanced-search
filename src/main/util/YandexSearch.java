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
	private UserAgents agents;
	private ArrayList<SearchResult> array;
	private Connection connection;
	private String query;
	private String httpStatus;
	
	public YandexSearch(String query){
		this.query = query;
	}

	@Override
	public ArrayList<SearchResult> search() throws Exception {
		
		array = new ArrayList();
		
		agents = new UserAgents();	
		
		connection = Jsoup.connect(new StringBuilder(DOMAIN_NAME)
								.append("/yandsearch?text=").append(query).toString());
		
        Document doc = 	connection
        				.userAgent(DEFAULT_USER_AGENT)
        				.followRedirects(true)
        				.timeout(DEFAULT_TIMEOUT)
        				.get();
        
        Elements main = doc.select("ul.serp-list");
        
        Elements primary = main.select("li.serp-item");
        
        httpStatus = new StringBuilder(Integer.toString(connection.response().statusCode()))
				.append(" ")
				.append(connection.response().statusMessage())
				.toString();

        
        for (Element result : primary){
        	
        	String title = getNewsTitle(result);
        	String url = getNewsURL(result);
        	
        	
        	array.add(new SearchResult(title, url, httpStatus));
       }
        
       
       return array;
		
	}
	
	private String getNewsTitle(Element e){
		return e.select("a.link").text();
	}
	
	private String getNewsURL(Element e){
		return e.select("a.link").attr("href");
	}

	//@Override
	/*public ArrayList<SearchResult> search(String query, int max) throws Exception {
		// TODO Auto-generated method stub
		
	}*/

}

