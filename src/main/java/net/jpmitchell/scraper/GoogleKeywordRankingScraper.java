package net.jpmitchell.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.net.URLCodec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class GoogleKeywordRankingScraper {
  
    public static List<String> getNaturalSearchRankings(String query) throws Exception{
        
        query = new URLCodec().encode(query);
        Document doc = Jsoup.connect("http://localhost:8081/search?q="+query).get();
    
        
        //natural search results:
        Elements searchResults = doc.select("#search cite");
        
        
        List<String> results = new ArrayList<String>();
        for(Element e: searchResults){
            results.add(getText(e));
        }
        return results;

        
    } 
    
    private static String getText(Element element){        
        StringBuffer text = new StringBuffer();
        getText(element.childNodes(),text);
        return text.toString().trim();
    } 
    private static void getText(List<Node> nodes, StringBuffer text){
        for(Node n: nodes){
            if(n instanceof TextNode){                
                text.append(" ").append(((TextNode) n).text());
            }
            else{
                getText(n.childNodes(), text);
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        System.out.println("Results:  "+Arrays.toString(getNaturalSearchRankings("Brakes Herndon VA").toArray()));
    }

}
