package main.model;

import java.awt.Color;
import java.time.LocalTime;

public class SearchResult {
	
	private String title;
	
	private String URL;
	
	private String httpStatus;
	
	private static final String HEADER = Color.GREEN + "_[ - ]::--------------------------------------------------------";
	
	private static final String LINE = "_[ + ] ";
	
	
	
	public SearchResult(String title, String URL, String httpStatus){
		setTitle(title);
		setURL(URL);
		setHttpStatus(httpStatus);
	}

	
	public SearchResult(String title, String String){
		setTitle(title);
		setURL(String);
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public void setURL(String URL){
		this.URL = URL;
	}
	
	public void setHttpStatus(String httpStatus){
		this.httpStatus = httpStatus;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getURL(){
		return URL;
	}
	
	public String getHttpStatus(){
		return httpStatus;
	}
	
	@Override
	public String toString(){
		return new StringBuilder(HEADER)
				.append("\n")
				.append(LINE  + LocalTime.now())
				.append(" URL: ")
				.append(URL)
				.append("\n ")
				.append(LINE)
				.append(title)
				.append("\n")
				.append(httpStatus)
				.toString();
	}
	
	
	
	
	

}
