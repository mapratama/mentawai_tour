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
public class SurfFragment extends Fragment {

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surf_fragment, container, false);
        ButterKnife.bind(this, view);
        intent = new Intent(getActivity(), ContentDetailsActivity.class);

        return view;
    }

    @OnClick(R.id.retribution_button)
    public void retributionButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.surfing_retribution_content));
        intent.putExtra("title", getResources().getString(R.string.surfing_retribution));
        intent.putExtra("isRetribution", true);
        intent.putExtra("banner", new int[]{R.mipmap.retribusi1, R.mipmap.retribusi2,
                R.mipmap.retribusi3, R.mipmap.retribusi4});
        startActivity(intent);
    }

    @OnClick(R.id.spot_button)
    public void spotButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.surfing_spot_content));
        intent.putExtra("title", getResources().getString(R.string.surfing_spot));
        intent.putExtra("banner", new int[]{R.mipmap.spot1, R.mipmap.spot2, R.mipmap.spot3, R.mipmap.spot4});
        startActivity(intent);
    }
}
