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

/**
 * Created by angga on 30/04/16.
 */
public class HomeFragment extends Fragment {

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        intent = new Intent(getActivity(), ContentDetailsActivity.class);

        return view;
    }

    @OnClick(R.id.mentawai_island_button)
    public void mentawaiIslandButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.mentawai_island_content));
        intent.putExtra("title", getResources().getString(R.string.mentawai_islands));
        intent.putExtra("banner", new int[]{R.mipmap.island1, R.mipmap.island2, R.mipmap.island3, R.mipmap.island4});
        startActivity(intent);
    }

    @OnClick(R.id.local_wisdom_button)
    public void localWisdomButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.local_wisdom_content));
        intent.putExtra("title", getResources().getString(R.string.local_wisdom_details));
        intent.putExtra("banner", new int[]{R.mipmap.wisdom1, R.mipmap.wisdom2, R.mipmap.wisdom3, R.mipmap.wisdom4});
        startActivity(intent);
    }

    @OnClick(R.id.world_oldest_tattoo_button)
    public void worldOldestTattooButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.world_oldest_content));
        intent.putExtra("title", getResources().getString(R.string.world_oldest_tattoo_details));
        intent.putExtra("banner", new int[]{R.mipmap.tattoo2, R.mipmap.tattoo1, R.mipmap.tattoo3});
        startActivity(intent);
    }

    @OnClick(R.id.attractions_button)
    public void attractionsButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.attractions_content));
        intent.putExtra("title", getResources().getString(R.string.attractions_details));
        intent.putExtra("banner", new int[]{R.mipmap.attraction1, R.mipmap.attraction2, R.mipmap.attraction3, R.mipmap.attraction4});
        startActivity(intent);
    }

    @OnClick(R.id.nature_button)
    public void natureButtonOnClick() {
        intent.putExtra("content", getResources().getString(R.string.nature_content));
        intent.putExtra("title", getResources().getString(R.string.nature_details));
        intent.putExtra("banner", new int[]{R.mipmap.nature1, R.mipmap.nature2, R.mipmap.nature3, R.mipmap.nature4});
        startActivity(intent);
    }
}
