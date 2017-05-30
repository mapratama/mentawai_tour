package mobile.android.mentawaitour.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.models.Content;
import mobile.android.mentawaitour.other.Utils;

/**
 * Created by angga on 30/04/16.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.mentawai_island_button)
    public void mentawaiIslandButtonOnClick() {
        Utils.openContent(getActivity(), "mentawai_island");
    }

    @OnClick(R.id.local_wisdom_button)
    public void localWisdomButtonOnClick() {
        Utils.openContent(getActivity(), "local_wisdom");
    }

    @OnClick(R.id.world_oldest_tattoo_button)
    public void worldOldestTattooButtonOnClick() {
        Utils.openContent(getActivity(), "world_oldest_tattoo");
    }

    @OnClick(R.id.attractions_button)
    public void attractionsButtonOnClick() {
        Utils.openContent(getActivity(), "attractions");
    }

    @OnClick(R.id.nature_button)
    public void natureButtonOnClick() {
        Utils.openContent(getActivity(), "nature");
    }
}
