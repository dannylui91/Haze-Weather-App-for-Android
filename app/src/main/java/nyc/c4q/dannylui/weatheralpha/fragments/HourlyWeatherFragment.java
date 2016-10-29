package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.adapters.HourlyAdapter;
import nyc.c4q.dannylui.weatheralpha.models.Datum_;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;

/**
 * Created by dannylui on 10/25/16.
 */

public class HourlyWeatherFragment extends Fragment implements UpdateableFragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hourly_weather_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.hourly_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new HourlyAdapter());
        return view;
    }

    @Override
    public void update(Location locationData, Forecast forecastData) {
        List<Datum_> dataList = forecastData.getHourly().getData();
        HourlyAdapter hourlyAdapter = (HourlyAdapter) recyclerView.getAdapter();
        hourlyAdapter.setData(dataList);
    }
}
