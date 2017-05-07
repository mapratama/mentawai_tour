package mobile.android.mentawaitour.home;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.other.ImageAdapter;

public class BannerActivity extends AppCompatActivity {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.indicator) CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        viewPager.setAdapter(new ImageAdapter(this, getIntent().getIntArrayExtra("banner")));
        circleIndicator.setViewPager(viewPager);
    }
}
