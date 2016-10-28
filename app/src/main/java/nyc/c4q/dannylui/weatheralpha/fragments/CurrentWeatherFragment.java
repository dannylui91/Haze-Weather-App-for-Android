package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Currently;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;

/**
 * Created by dannylui on 10/25/16.
 */

public class CurrentWeatherFragment extends Fragment implements UpdateableFragment {
    private TextView locationView;
    private TextView descriptionView;
    private TextView tempView;
    private TextView weatherDataView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_weather_fragment, container, false);
        locationView = (TextView) view.findViewById(R.id.location_view);
        descriptionView = (TextView) view.findViewById(R.id.description_view);
        tempView = (TextView) view.findViewById(R.id.current_temp_view);
        weatherDataView = (TextView) view.findViewById(R.id.current_data_view);
        return view;
    }

    @Override
    public void update(Location locationData, Forecast forecastData) {
        String city = locationData.getCity();
        String region = locationData.getRegionName();
        Currently data = forecastData.getCurrently();

        locationView.setText(city + ", " + region);
        descriptionView.setText(data.getSummary());
        tempView.setText(String.valueOf(Math.round(data.getTemperature())) + "°");
        String result = "";
        result += "Humidity: " + data.getHumidity() + "\nRain Chance: " + data.getPrecipProbability();
        weatherDataView.setText(result);
    }

}
