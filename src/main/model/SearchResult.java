package main.model;

import java.time.LocalTime;

public class SearchResult {
	
	private String title;
	
	private String URL;
	
	private String httpStatus;
	
	public SearchResult(String title, String URL, String httpStatus){
		setTitle(title);
		setURL(URL);
		setHttpStatus(httpStatus);
	}

	
	public SearchResult(String title, String String){
		setTitle(title);
		setURL(String);
	}
	
	/*
	 * Set Title
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/*
	 * Set URL
	 * @param URL
	 */
	public void setURL(String URL){
		this.URL = URL;
	}
	
	/*
	 * Set Http Status
	 * @param httpStatus
	 */
	public void setHttpStatus(String httpStatus){
		this.httpStatus = httpStatus;
	}
	
	/*
	 * Get Title
	 * @return		String represent Title
	 */
	public String getTitle(){
		return title;
	}
	
	/*
	 * Get URL
	 * @return		String represent URL
	 */
	public String getURL(){
		return URL;
	}
	
	/*
	 * Get Http Status
	 * @return		String represent Http Status
	 */
	public String getHttpStatus(){
		return httpStatus;
	}
	
	@Override
	public String toString(){
		return new StringBuilder( )
				.append(LocalTime.now())
				.append(" URL: ")
				.append(URL)
				.append("\n ")
				.append(title)
				.append("\n")
				.append(httpStatus)
				.toString();
	}
	
	
	
	
	

}
