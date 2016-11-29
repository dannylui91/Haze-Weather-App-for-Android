package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;

/**
 * Created by dannylui on 11/29/16.
 */

public class PrecipFragment extends Fragment {
    private Forecast forecastData;

    private TextView currentHiView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_precip, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentHiView = (TextView) view.findViewById(R.id.current_hi_view);

        if (forecastData != null) {
            setForecastData();
        }


    }

    public void update(Forecast data) {
        forecastData = data;
    }

    public void setForecastData() {

    }

    public void disableViews() {
    }

    public void enableViews() {
    }

    public void setAnimationStuff(TextView t) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
