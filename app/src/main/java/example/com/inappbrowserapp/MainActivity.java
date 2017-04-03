package example.com.inappbrowserapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import example.com.inappbrowserlibrary.WebViewFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        webViewFragment = WebViewFragment.newInstance(WEB_URL);

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
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
