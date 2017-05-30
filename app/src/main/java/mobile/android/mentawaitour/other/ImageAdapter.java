package mobile.android.mentawaitour.other;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import io.realm.RealmList;
import io.realm.RealmResults;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.models.Photo;

/**
 * Created by angga on 25/04/17.
 */

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private RealmList<Photo> photos;

    public ImageAdapter(Context context, RealmResults<Photo> photoList) {
        this.mContext = context;
        photos = new RealmList<>();
        for (Photo photo : photoList) photos.add(photo);
    }

    public ImageAdapter(Context context, RealmList<Photo> photoList) {
        this.mContext = context;
        this.photos = photoList;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((SimpleDraweeView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        SimpleDraweeView photo = new SimpleDraweeView(mContext);
        photo.setImageURI(Uri.parse(photos.get(i).getPhotoUrl()));
        photo.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        ((ViewPager) container).addView(photo, 0);
        return photo;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((SimpleDraweeView) obj);
    }
}

