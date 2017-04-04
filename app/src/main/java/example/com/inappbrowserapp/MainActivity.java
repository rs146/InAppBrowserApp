package example.com.inappbrowserapp;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import uk.co.ostmodern.customtabs.CustomTabActivityHelper;
import uk.co.ostmodern.webview.WebViewFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_URL = "extra_url";

    private static final String WEB_URL = "http://summergames.olympicchannel.com/olympic/2016-rio-de-janeiro/";
    private static final String BBC_URL = "https://bbc.co.uk";
    private static final String BBC_SPORTS_URL = "https://bbc.co.uk/sports";

    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button backButton = (Button) findViewById(R.id.btn_back);
        Button forwardButton = (Button) findViewById(R.id.btn_forward);

        backButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);

        if (getIntent().getStringExtra(EXTRA_URL) != null && !getIntent().getStringExtra(EXTRA_URL).isEmpty()) {
            webViewFragment = WebViewFragment.newInstance(getIntent().getStringExtra(EXTRA_URL));
        } else {
            webViewFragment = WebViewFragment.newInstance(WEB_URL);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container, webViewFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            webViewFragment.onBackHistory();
        } else if (v.getId() == R.id.btn_forward) {
            webViewFragment.onForwardHistory();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add_bbc_url:
                webViewFragment.loadNewUrl(BBC_URL);
                break;
            case R.id.add_bbc_sports_url:
                webViewFragment.loadNewUrl(BBC_SPORTS_URL);
                break;
            case R.id.open_chrome_custom_tab:
                openChromeCustomTab();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void openChromeCustomTab() {
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        CustomTabActivityHelper.openCustomTab(this, customTabsIntent, Uri.parse(WEB_URL), new WebViewFallback());
    }
}
