package uk.co.ostmodern.webview;

/**
 * Data structure used to store the web url for a Browser History doubly linked list.
 */
public class BrowserHistoryNode {

    private BrowserHistoryNode next;
    private BrowserHistoryNode previous;
    private String webContentUrl;
    private Integer id;

    public BrowserHistoryNode(BrowserHistoryNode next, BrowserHistoryNode previous, String webContentUrl, Integer id) {
        this.next = next;
        this.previous = previous;
        this.webContentUrl = webContentUrl;
        this.id = id;
    }

    public BrowserHistoryNode getNext() {
        return next;
    }

    public void setNext(BrowserHistoryNode next) {
        this.next = next;
    }

    public BrowserHistoryNode getPrevious() {
        return previous;
    }

    public void setPrevious(BrowserHistoryNode previous) {
        this.previous = previous;
    }

    public String getWebContentUrl() {
        return webContentUrl;
    }

    public void setWebContentUrl(String webContentUrl) {
        this.webContentUrl = webContentUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
