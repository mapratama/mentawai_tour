package mobile.android.mentawaitour.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.models.Content;
import mobile.android.mentawaitour.other.ImageAdapter;

public class ContentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.content_text) TextView contentTextView;
    @BindView(R.id.title_text) TextView titleTextView;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.pay_button) Button payButton;
    @BindView(R.id.indicator) CircleIndicator circleIndicator;

    private Content content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);
        ButterKnife.bind(this);

        content = Content.getByKey(getIntent().getStringExtra("key"));
        contentTextView.setText(content.getDescription());
        titleTextView.setText(content.getName());


        viewPager.setAdapter(new ImageAdapter(this, content.getPhotos()));
        circleIndicator.setViewPager(viewPager);

        if (content.getKey().equals("surfing_retribution")) {
            payButton.setVisibility(View.VISIBLE);
            return;
        }
    }

    @OnClick(R.id.pay_button)
    public void payButtonOnClick() {

    }

    @OnClick(R.id.title_text)
    public void viewPagerOnClick() {
        Intent intent = new Intent(this, BannerActivity.class);
        intent.putExtra("key", content.getKey());
        startActivity(intent);
    }
}
