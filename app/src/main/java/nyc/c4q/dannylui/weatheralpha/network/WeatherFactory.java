package nyc.c4q.dannylui.weatheralpha.network;

import android.util.Log;

import java.util.List;

import nyc.c4q.dannylui.weatheralpha.models.darksky.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.ipapi.Location;
import nyc.c4q.dannylui.weatheralpha.network.darksky.DarkSkyClient;
import nyc.c4q.dannylui.weatheralpha.network.ipapi.GeolocationClient;
import nyc.c4q.dannylui.weatheralpha.utility.ConvertUnixTs;
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
//                SunModel sunModel = parseToSunModel(forecast);
//                TempModel tempModel = parseToTempModel(forecast);
//                RainModel rainModel = parseToRainModel(forecast);
                weatherCallback.getForecastData(forecast);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    public void retrieveWeatherData() {
        findGeoLocation();
    }

    public void sendCall() {

    }

    public void parseToSunModel(Forecast forecast) {
        int iconId = 0;
        String uvIndex = "0";
        String sunriseTime = getSunriseTime(forecast);
        String sunsetTime = getSunsetTime(forecast);
        String sunTime;
        String cloudCoverage;
        List<String> sunshineList;


    }

    public void parseToTempModel(Forecast forecast) {

    }

    public void parseToRainModel(Forecast forecast) {

    }

    public String getSunriseTime(Forecast forecast) {
        long sunriseTime = forecast.getDaily().getData().get(0).getSunriseTime();
        long sunsetTime = forecast.getDaily().getData().get(0).getSunsetTime();
        long currentTime = forecast.getCurrently().getTime();

        if (currentTime > sunsetTime) {
            sunriseTime = forecast.getDaily().getData().get(1).getSunriseTime();
        }
        return ConvertUnixTs.toHourMinute(sunriseTime);
    }

    public String getSunsetTime(Forecast forecast) {
        long sunsetTime = forecast.getDaily().getData().get(0).getSunsetTime();
        long currentTime = forecast.getCurrently().getTime();

        if (currentTime > sunsetTime) {
            sunsetTime = forecast.getDaily().getData().get(1).getSunsetTime();
        }
        return ConvertUnixTs.toHourMinute(sunsetTime);
    }

//    private void getSunTime(Forecast forecastData) {
//        int sunriseTime = forecastData.getDaily().getData().get(0).getSunriseTime();
//        int sunsetTime = forecastData.getDaily().getData().get(0).getSunsetTime();
//        int currentTime = forecastData.getCurrently().getTime();
//
//        System.out.println("Sunrise " + sunriseTime);
//        System.out.println("Sunset " + sunsetTime);
//        System.out.println("CurrentTime " + currentTime);
//
//        double hoursBeforeSunrise = -1;
//        double hoursBeforeSunset = -1;
//
//        if (currentTime < sunriseTime) {
//            hoursBeforeSunrise = (sunriseTime - currentTime) / 3600;
//        }
//        else if (currentTime > sunriseTime && currentTime < sunsetTime) {
//            hoursBeforeSunset = (sunsetTime - currentTime) / 3600;
//        } else if (currentTime > sunsetTime) {
//            int sunriseTime2 = forecastData.getDaily().getData().get(1).getSunriseTime();
//            hoursBeforeSunrise = (sunriseTime2 - currentTime) / 3600;
//        }
//
//        if (hoursBeforeSunrise != -1) {
//            System.out.println(hoursBeforeSunrise);
//            sunLeft.setText(String.valueOf(Math.round(hoursBeforeSunrise)) + "h");
//        }
//        if (hoursBeforeSunset != -1) {
//            System.out.println(hoursBeforeSunset);
//            sunLeft.setText(String.valueOf(Math.round(hoursBeforeSunset)) + "h");
//        }
//    }
}

