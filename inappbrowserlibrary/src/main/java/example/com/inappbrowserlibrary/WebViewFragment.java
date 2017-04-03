package example.com.inappbrowserlibrary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebView Fragment.
 */
public class WebViewFragment extends Fragment {

    private static final String ARG_WEB_CONTENT_URL = "arg_web_content_url";

    private String webContentUrl;
    private WebView webView;
    private BrowserHistoryNode currentBrowserHistoryNode;

    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Create new Instance of this Fragment passing in the web content url to be displayed in the web view.
     *
     * @param webContentUrl web content url
     * @return instance of this Fragment
     */
    public static WebViewFragment newInstance(String webContentUrl) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WEB_CONTENT_URL, webContentUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            webContentUrl = getArguments().getString(ARG_WEB_CONTENT_URL);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        webView = (WebView) view.findViewById(R.id.web_view);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setEnableSmoothTransition(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(webContentUrl);

        currentBrowserHistoryNode = BrowserHistoryCache.addUrl(webContentUrl);
        return view;
    }

    /**
     * Load a new web content url.
     *
     * @param webContentUrl web content url
     */
    public void loadNewUrl(String webContentUrl) {
        this.webContentUrl = webContentUrl;
        currentBrowserHistoryNode = BrowserHistoryCache.addUrl(webContentUrl);
        webView.loadUrl(webContentUrl);
    }

    /**
     * Call when user initiates the "back" function.
     */
    public void onBackHistory() {
        if (currentBrowserHistoryNode.getPrevious() != null) {
            BrowserHistoryNode previous = currentBrowserHistoryNode.getPrevious();
            webContentUrl = previous.getWebContentUrl();
            currentBrowserHistoryNode = previous;
            webView.loadUrl(webContentUrl);
        }
    }

    /**
     * Call when user initiates the "forward" function.
     */
    public void onForwardHistory() {
        if (currentBrowserHistoryNode.getNext() != null) {
            BrowserHistoryNode next = currentBrowserHistoryNode.getNext();
            webContentUrl = next.getWebContentUrl();
            currentBrowserHistoryNode = next;
            webView.loadUrl(webContentUrl);
        }
    }
}
