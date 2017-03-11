
import java.util.*;
import java.awt.*;
import main.controller.Controller;
import main.model.SearchResult;
public class SearchEngine {
	private static Controller controller;
	public static void main(String[] args) throws Exception{
   ArrayList<SearchResult >result= controller.googleSearch("test");
    for(SearchResult i: result) {
    	System.out.println(i);
    }
	}

}