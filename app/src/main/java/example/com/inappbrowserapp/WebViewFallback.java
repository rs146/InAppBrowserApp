package example.com.inappbrowserapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import uk.co.ostmodern.customtabs.CustomTabFallback;

/**
 * Implementation of CustomTabFallback.
 */
class WebViewFallback implements CustomTabFallback {

    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_URL, uri.toString());
        activity.startActivity(intent);
    }
}
