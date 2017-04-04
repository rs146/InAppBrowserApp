package uk.co.ostmodern.webview;

import java.util.LinkedList;

/**
 * Class that manages a cache of browsing history for as long as the app is alive.
 */
public class BrowserHistoryCache {

    private static LinkedList<BrowserHistoryNode> BROWSER_HISTORY_LIST = new LinkedList<>();

    /**
     * Adds a web url to the browser history list and returns the node for that Browser History.
     *
     * @param webUrl web url to be added
     * @return the browser history node representing that url in the browser history
     */
    public static BrowserHistoryNode addUrl(String webUrl) {
        if (BROWSER_HISTORY_LIST.isEmpty()) {
            BrowserHistoryNode firstItem = new BrowserHistoryNode(null, null, webUrl, 0);
            BROWSER_HISTORY_LIST.add(firstItem);
            return firstItem;
        } else {
            BrowserHistoryNode last = BROWSER_HISTORY_LIST.getLast();
            BrowserHistoryNode newNode = new BrowserHistoryNode(null, last, webUrl, BROWSER_HISTORY_LIST.size());
            BROWSER_HISTORY_LIST.addLast(newNode);
            last.setNext(newNode);
            BROWSER_HISTORY_LIST.set(last.getId(), last);
            return newNode;
        }
    }

    public static void resetCache() {
        BROWSER_HISTORY_LIST = new LinkedList<>();
    }
}
