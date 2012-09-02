package net.jpmitchell.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class HtmlDocumentHelper {
    private Document doc;
    
    public HtmlDocumentHelper(String url) throws IOException{
        HttpConnection conn = (HttpConnection)Jsoup.connect(url);
        doc = conn.get();
    } 
    
    public PageData getPageData(){
        PageData pd = new PageData();
        
        
        
        pd.setTitle(doc.select("title").html());
        Elements meta = doc.select("meta[name=description]");        
        pd.setDescription(meta.attr("content"));
        String keywords = doc.select("meta[name=keywords]").attr("content");
        
        List<String> kwList = new ArrayList<String>();        
        for(String kw: keywords.split(",")){
            kwList.add(kw.trim());
        }
        pd.setKeywords(kwList);
        
        Elements headers = doc.select("h1,h2,h3,h4,h5,h6");
        pd.setHeaderContent(getText(headers));
        
        Elements bold = doc.select("b,em,strong");        
        pd.setBoldContent(getText(bold));
        
        Elements anchor = doc.select("a[href]");        
        pd.setAnchorContent(getText(anchor));
        
        Elements body = doc.select("body");
        pd.setBodyContent(getText(body));
        
        
        //Scan top-level nodes for the doctype node
        for (Node node : doc.childNodes()) {
           if (node instanceof DocumentType) {
               DocumentType documentType = (DocumentType)node;
                 pd.setDocType(documentType.toString());
                 break;
           }
        }
        
        
        
        
        
        System.out.println("Title:  "+pd.getTitle());
        System.out.println("description:  "+pd.getDescription());
        System.out.println("Keywords:  "+Arrays.toString(pd.getKeywords().toArray()));
        System.out.println("Header Content:  "+pd.getHeaderContent());
        System.out.println("Anchor Content:  "+pd.getAnchorContent());
        System.out.println("Bold Content:  "+pd.getBoldContent());
        System.out.println("All Body Content:  "+pd.getBodyContent());
        
        return null;
    }
    
    /*
     * Gets text content of a list of nodes, with all html tags removed
     */
    private String getText(Elements elements){        
        StringBuffer text = new StringBuffer();
        for(Element e: elements){
            getText(e.childNodes(),text);
        }
        return text.toString().trim();
    } 
    private void getText(List<Node> nodes, StringBuffer text){
        for(Node n: nodes){
            if(n instanceof TextNode){                
                text.append(" ").append(((TextNode) n).text());
            }
            else{
                getText(n.childNodes(), text);
            }
        }
    }
   
    public final static void main(String[] args) throws Exception {

        try {
            String url = "http://www.networksolutions.com";
            new HtmlDocumentHelper(url).getPageData();

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
}
