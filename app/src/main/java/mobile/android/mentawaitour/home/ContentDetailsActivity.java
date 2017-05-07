package mobile.android.mentawaitour.home;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.other.ImageAdapter;
import mobile.android.mentawaitour.other.Utils;

public class ContentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.content_text) TextView contentTextView;
    @BindView(R.id.title_text) TextView titleTextView;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.indicator) CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);
        ButterKnife.bind(this);

        viewPager.setAdapter(new ImageAdapter(this, getIntent().getIntArrayExtra("banner")));
        circleIndicator.setViewPager(viewPager);
        contentTextView.setText(Utils.fromHtml(getIntent().getStringExtra("content")));
        titleTextView.setText(getIntent().getStringExtra("title"));
    }

    @OnClick(R.id.content_text)
    public void a() {
        viewPager.setCurrentItem(1, true);
    }

    @OnClick(R.id.title_text)
    public void viewPagerOnClick() {
        Intent intent = new Intent(this, BannerActivity.class);
        intent.putExtra("banner", getIntent().getIntArrayExtra("banner"));
        startActivity(intent);
    }
}
