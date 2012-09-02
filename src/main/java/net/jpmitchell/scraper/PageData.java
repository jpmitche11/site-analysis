package net.jpmitchell.scraper;

import java.util.List;

public class PageData {
    private String url;
    private String title;
    private String description;
    private List<String> keywords;
    private String docType;
    private String bodyContent;
    private String headerContent;
    private String boldContent;
    private String anchorContent;
    
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDocType() {
        return docType;
    }
    public void setDocType(String docType) {
        this.docType = docType;
    }
    public String getBodyContent() {
        return bodyContent;
    }
    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }
    public String getHeaderContent() {
        return headerContent;
    }
    public void setHeaderContent(String headerContent) {
        this.headerContent = headerContent;
    }
    public String getBoldContent() {
        return boldContent;
    }
    public void setBoldContent(String boldContent) {
        this.boldContent = boldContent;
    }
    public List<String> getKeywords() {
        return keywords;
    }
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    public String getAnchorContent() {
        return anchorContent;
    }
    public void setAnchorContent(String anchorContent) {
        this.anchorContent = anchorContent;
    }
}
