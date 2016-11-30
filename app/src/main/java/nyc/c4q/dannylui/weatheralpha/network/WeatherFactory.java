package nyc.c4q.dannylui.weatheralpha.network;

import android.util.Log;

import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by dannylui on 11/29/16.
 */

public class WeatherFactory {
    private final WeatherCallback weatherCallback;

    public WeatherFactory(WeatherCallback weatherCallback) {
        this.weatherCallback = weatherCallback;
        findGeoLocation();
    }

    private void findGeoLocation() {
        GeolocationClient geolocationClient = GeolocationClient.getInstance();
        Call<Location> call = geolocationClient.getLocation();
        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                Location location = response.body();
                weatherCallback.getLocationData(location);
                findForecast(location);
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void findForecast(Location location) {
        DarkSkyClient darkSkyClient = DarkSkyClient.getInstance();
        Call<Forecast> call = darkSkyClient.getForecast(location.getLat() + "," + location.getLon());
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                weatherCallback.getForecastData(forecast);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}

