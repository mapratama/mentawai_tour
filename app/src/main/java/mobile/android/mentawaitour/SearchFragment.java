package mobile.android.mentawaitour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import mobile.android.mentawaitour.home.BannerActivity;

/**
 * Created by angga on 30/04/16.
 */
public class SearchFragment extends Fragment {

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Intent intent = new Intent(context, BannerActivity.class);
            intent.putExtra("banner", new int[]{R.mipmap.island1, R.mipmap.island2, R.mipmap.island3, R.mipmap.island4,
                    R.mipmap.wisdom1, R.mipmap.wisdom2, R.mipmap.wisdom3, R.mipmap.wisdom4, R.mipmap.tattoo2,
                    R.mipmap.tattoo1, R.mipmap.tattoo3, R.mipmap.attraction1, R.mipmap.attraction2,
                    R.mipmap.attraction3, R.mipmap.attraction4, R.mipmap.nature1, R.mipmap.nature2,
                    R.mipmap.nature3, R.mipmap.nature4});
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((MainActivity) getActivity()).viewPager.setCurrentItem(0, true);
    }
}
