package mobile.android.mentawaitour;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.android.mentawaitour.home.BannerActivity;
import mobile.android.mentawaitour.home.HomeFragment;
import mobile.android.mentawaitour.other.CustomViewPager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) CustomViewPager viewPager;

    private int lastTabSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new SurfFragment(), "Surf");
        adapter.addFragment(new TourFragment(), "Tour");
        adapter.addFragment(new SearchFragment(), "Image");
        adapter.addFragment(new ProfileFragment(), "Video");
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);

        final Activity activity = this;
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) tabLayout.setBackgroundColor(ContextCompat.getColor(activity, R.color.orange));
                else if (position == 1) tabLayout.setBackgroundColor(ContextCompat.getColor(activity, R.color.blue));
                else if (position == 2) tabLayout.setBackgroundColor(ContextCompat.getColor(activity, R.color.magenta));
                else if (position == 3) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    openBannerPage();
                    return;
                }
                else if (position == 4) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    Intent intent = new Intent(MainActivity.this, VideoViewActivity.class);
                    startActivityForResult(intent, 1);
                    return;
                }

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewPager.setCurrentItem(0, true);
    }

    private void openBannerPage() {
        Intent intent = new Intent(MainActivity.this, BannerActivity.class);
        intent.putExtra("imageTab", true);
        startActivityForResult(intent, 1);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
}
