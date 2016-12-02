package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.darksky.Forecast;

/**
 * Created by dannylui on 11/30/16.
 */

public class CircleFragment extends Fragment {
    private View rootView;
    private CurrentWeatherFragment currentWeatherFragment;
    private PrecipFragment precipFragment;
    private SunFragment sunFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_centercircle, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("Init 3 Frags");
        currentWeatherFragment = new CurrentWeatherFragment();
        precipFragment = new PrecipFragment();
        sunFragment = new SunFragment();
    }

    public void replaceContainer(int position) {
        switch (position) {
            case 0:
                getFragmentManager().beginTransaction()
                        .replace(R.id.general_circle_container, sunFragment)
                        .commit();
                rootView.animate().translationY(-50f);
                break;
            case 1:
                System.out.println("Called replace Container 1");
                getFragmentManager().beginTransaction()
                        .replace(R.id.general_circle_container, currentWeatherFragment)
                        .commit();
                rootView.animate().translationY(250f);
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .replace(R.id.general_circle_container, precipFragment)
                        .commit();
                rootView.animate().translationY(450f);
                break;
        }
    }

    public void updateAll(Forecast data) {
        sunFragment.update(data);
        currentWeatherFragment.update(data);
        precipFragment.update(data);
    }

}
