package mobile.android.mentawaitour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.android.mentawaitour.home.ContentDetailsActivity;

/**
 * Created by angga on 30/04/16.
 */
public class TourFragment extends Fragment {

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tour_fragment, container, false);
        ButterKnife.bind(this, view);
        intent = new Intent(getActivity(), ContentDetailsActivity.class);

        return view;
    }

    @OnClick(R.id.resorts_button)
    public void resortsButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.resorts_content));
        intent.putExtra("title", getResources().getString(R.string.resorts));
        intent.putExtra("banner", new int[]{R.mipmap.resort1, R.mipmap.resort2});
        startActivity(intent);
    }

    @OnClick(R.id.homestay_button)
    public void homestayButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.homestay_content));
        intent.putExtra("title", getResources().getString(R.string.homestay));
        intent.putExtra("banner", new int[]{R.mipmap.homestay1, R.mipmap.homestay2, R.mipmap.homestay3});
        startActivity(intent);
    }

    @OnClick(R.id.how_button)
    public void howButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.how_to_mentawai_content));
        intent.putExtra("title", getResources().getString(R.string.how_to_mentawai));
        intent.putExtra("banner", new int[]{R.mipmap.transportation1, R.mipmap.transportation2});
        startActivity(intent);
    }
}
