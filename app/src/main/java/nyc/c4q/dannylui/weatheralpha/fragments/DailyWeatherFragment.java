package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.adapters.DailyAdapter;
import nyc.c4q.dannylui.weatheralpha.models.Datum__;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;

/**
 * Created by dannylui on 10/25/16.
 */

public class DailyWeatherFragment extends Fragment implements UpdateableFragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_weather_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.daily_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new DailyAdapter());
        return view;
    }

    @Override
    public void update(Forecast newData) {
        List<Datum__> dataList = newData.getDaily().getData();
        DailyAdapter dailyAdapter = (DailyAdapter) recyclerView.getAdapter();
        dailyAdapter.setData(dataList);
    }
}
