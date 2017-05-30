package mobile.android.mentawaitour;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import mobile.android.mentawaitour.models.Content;
import mobile.android.mentawaitour.other.API;
import mobile.android.mentawaitour.other.Preferences;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.loading_text) TextView loadingText;

    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);

        View view = findViewById(R.id.view);

        anim = new ScaleAnimation(
                1f, 1f, 1, 8,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f);
        anim.setDuration(1900);
        anim.setRepeatCount(100);
        view.startAnimation(anim);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Preferences preferences = new Preferences(this);
        if (new Date().before(new Date(preferences.getLong("lastSyncTime") + (1000 * 60)))
                && Content.hasData()) {
            showMainActivity();
            return;
        }

        loadingText.setText("Loading...");
        API.get(API.BASE_URL + "contents", API.getBaseParams(), false, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Content.fromJSONArray(response.getJSONArray("contents"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                preferences.set("lastSyncTime", new Date().getTime());
                showMainActivity();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                if (Content.hasData()) showMainActivity();
                else loadingText.setText("Please check your internet connection");
                anim.cancel();
            }
        });
    }

    private void showMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}
