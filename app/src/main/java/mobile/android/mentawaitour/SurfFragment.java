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
public class SurfFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surf_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.retribution_button)
    public void retributionButtonOnClick() {
        Utils.openContent(getActivity(), "surfing_retribution");
    }

    @OnClick(R.id.spot_button)
    public void spotButtonOnClick() {
        Utils.openContent(getActivity(), "surfing_spot");
    }

    @OnClick(R.id.tides_button)
    public void tidesButtonOnClick() {
        Utils.openContent(getActivity(), "tides");
    }

    @OnClick(R.id.information_button)
    public void informationButtonOnClick() {
        Utils.openContent(getActivity(), "tourism_information_center");
    }

    @OnClick(R.id.living_button)
    public void livingButtonOnClick() {
        Utils.openContent(getActivity(), "living_on_board");
    }
}
