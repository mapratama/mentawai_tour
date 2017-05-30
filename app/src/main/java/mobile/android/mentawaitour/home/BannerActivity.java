package mobile.android.mentawaitour.home;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import me.relex.circleindicator.CircleIndicator;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.models.Content;
import mobile.android.mentawaitour.models.Photo;
import mobile.android.mentawaitour.other.ImageAdapter;

public class BannerActivity extends AppCompatActivity {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.indicator) CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        if (getIntent().getBooleanExtra("imageTab", false)) {
            RealmResults<Photo> photos = Realm.getDefaultInstance().where(Photo.class).findAll();
            viewPager.setAdapter(new ImageAdapter(this, photos));
        }
        else
            viewPager.setAdapter(new ImageAdapter(this, Content.getByKey(
                    getIntent().getStringExtra("key")).getPhotos()));
        circleIndicator.setViewPager(viewPager);
    }
}
