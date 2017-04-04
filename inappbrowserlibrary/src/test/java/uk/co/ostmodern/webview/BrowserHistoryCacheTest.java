package uk.co.ostmodern.webview;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for {@link BrowserHistoryCache}.
 */
public class BrowserHistoryCacheTest {

    private static final String FIRST_WEB_URL = "https://bbc.co.uk";
    private static final String SECOND_WEB_URL = "https://bbc.co.uk/sports";
    private static final String THIRD_WEB_URL = "https://bbc.co.uk/news";

    @Test
    public void addFirstWebUrlToBrowserHistory() {
        final BrowserHistoryNode firstHistoryNode = BrowserHistoryCache.addUrl(FIRST_WEB_URL);

        assertEquals(FIRST_WEB_URL, firstHistoryNode.getWebContentUrl());
        assertEquals(Integer.valueOf(0), firstHistoryNode.getId());

        clearCacheList();
    }

    @Test
    public void addSecondWebUrlToBrowserHistory() {
        final BrowserHistoryNode firstHistoryNode = BrowserHistoryCache.addUrl(FIRST_WEB_URL);
        final BrowserHistoryNode secondHistoryNode = BrowserHistoryCache.addUrl(SECOND_WEB_URL);

        assertEquals(FIRST_WEB_URL, firstHistoryNode.getWebContentUrl());
        assertEquals(Integer.valueOf(0), firstHistoryNode.getId());
        assertEquals(SECOND_WEB_URL, firstHistoryNode.getNext().getWebContentUrl());
        assertEquals(SECOND_WEB_URL, secondHistoryNode.getWebContentUrl());
        assertEquals(Integer.valueOf(1), secondHistoryNode.getId());
        assertEquals(FIRST_WEB_URL, secondHistoryNode.getPrevious().getWebContentUrl());

        clearCacheList();
    }

    @Test
    public void addThreeWebUrlsToBrowserHistory() {
        final BrowserHistoryNode firstHistoryNode = BrowserHistoryCache.addUrl(FIRST_WEB_URL);
        final BrowserHistoryNode secondHistoryNode = BrowserHistoryCache.addUrl(SECOND_WEB_URL);
        final BrowserHistoryNode thirdHistoryNode = BrowserHistoryCache.addUrl(THIRD_WEB_URL);

        assertEquals(FIRST_WEB_URL, firstHistoryNode.getWebContentUrl());
        assertEquals(Integer.valueOf(0), firstHistoryNode.getId());
        assertEquals(SECOND_WEB_URL, firstHistoryNode.getNext().getWebContentUrl());
        assertEquals(SECOND_WEB_URL, secondHistoryNode.getWebContentUrl());
        assertEquals(Integer.valueOf(1), secondHistoryNode.getId());
        assertEquals(FIRST_WEB_URL, secondHistoryNode.getPrevious().getWebContentUrl());
        assertEquals(THIRD_WEB_URL, secondHistoryNode.getNext().getWebContentUrl());
        assertEquals(THIRD_WEB_URL, thirdHistoryNode.getWebContentUrl());
        assertEquals(Integer.valueOf(2), thirdHistoryNode.getId());

        clearCacheList();
    }

    @After
    public void clearCacheList() {
        BrowserHistoryCache.resetCache();
    }
}