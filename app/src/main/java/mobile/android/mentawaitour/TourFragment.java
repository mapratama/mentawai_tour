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
import mobile.android.mentawaitour.other.Utils;

/**
 * Created by angga on 30/04/16.
 */
public class TourFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tour_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.resorts_button)
    public void resortsButtonOnClick() {
        Utils.openContent(getActivity(), "resorts");
    }

    @OnClick(R.id.homestay_button)
    public void homestayButtonOnClick() {
        Utils.openContent(getActivity(), "homestay");
    }

    @OnClick(R.id.how_button)
    public void howButtonOnClick() {
        Utils.openContent(getActivity(), "how_to_mentawai");
    }

    @OnClick(R.id.guide_button)
    public void guideButtonOnClick() {
        Utils.openContent(getActivity(), "tour_guide");
    }

    @OnClick(R.id.island_life_button)
    public void islandLifeButtonOnClick() {
        Utils.openContent(getActivity(), "island_life");
    }
}
