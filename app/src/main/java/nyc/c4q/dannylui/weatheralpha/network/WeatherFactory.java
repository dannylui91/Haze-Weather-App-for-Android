package nyc.c4q.dannylui.weatheralpha.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.dannylui.weatheralpha.models.RainModel;
import nyc.c4q.dannylui.weatheralpha.models.SunModel;
import nyc.c4q.dannylui.weatheralpha.models.TempModel;
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
                SunModel sunModel = parseToSunModel(forecast);
                TempModel tempModel = parseToTempModel(forecast);
                RainModel rainModel = parseToRainModel(forecast);
                weatherCallback.getForecastData(forecast, sunModel, tempModel, rainModel);
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

    public SunModel parseToSunModel(Forecast forecast) {
        int iconId = 0;
        String uvIndex = "0";
        String sunriseTime = ConvertUnixTs.toHourMinute(forecast.getDaily().getData().get(0).getSunriseTime());
        String sunsetTime = ConvertUnixTs.toHourMinute(forecast.getDaily().getData().get(0).getSunsetTime());
        String cloudCoverage = (int) (forecast.getCurrently().getCloudCover() * 100) + "%";
        String sunTime = getSunTime(forecast);
        List<String> sunshineList = getSunshineList(forecast);

        return new SunModel(iconId, uvIndex, sunriseTime, sunsetTime, cloudCoverage, sunTime, sunshineList);
    }

    public TempModel parseToTempModel(Forecast forecast) {
        String highTemp = (int) Math.round(forecast.getDaily().getData().get(0).getTemperatureMax()) + "°";
        String lowTemp = (int) Math.round(forecast.getDaily().getData().get(0).getTemperatureMin()) + "°";
        String feelTemp = (int) Math.round(forecast.getCurrently().getApparentTemperature()) + "°";
        String windSpeed = (int) Math.round(forecast.getCurrently().getWindSpeed()) + "";
        String windDirection = forecast.getCurrently().getWindBearing() + "";
        String currentTemp = (int) Math.round(forecast.getCurrently().getTemperature()) + "°";
        List<String> highTempList = getHighTempList(forecast);

        return new TempModel(highTemp, lowTemp, feelTemp, windSpeed, windDirection, currentTemp, highTempList);
    }

    public RainModel parseToRainModel(Forecast forecast) {
        int iconId = 0;
        String rainDropped = (int) Math.round(forecast.getCurrently().getPrecipIntensity()) + "";
        String pressure = (int) Math.round(forecast.getCurrently().getPressure()) + "";
        String humidity = (int) (forecast.getCurrently().getHumidity() * 100) + "";
        String rainChance = (int) (forecast.getCurrently().getPrecipProbability() * 100) + "%";
        List<String> rainChanceList = getRainChanceList(forecast);

        return new RainModel(iconId, rainDropped, pressure, humidity, rainChance, rainChanceList);
    }

    private List<String> getHighTempList(Forecast forecast) {
        List<String> highTempList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            highTempList.add((int) Math.round(forecast.getDaily().getData().get(i).getTemperatureMax()) + "");
        }
        return highTempList;
    }

    private List<String> getRainChanceList(Forecast forecast) {
        List<String> rainChanceList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            rainChanceList.add((int)(forecast.getDaily().getData().get(i).getPrecipProbability() * 100) + "");
        }
        return rainChanceList;
    }

    private List<String> getSunshineList(Forecast forecast) {
        List<String> sunshineList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sunshineList.add(Math.round((forecast.getDaily().getData().get(i).getSunsetTime() -
                    forecast.getDaily().getData().get(i).getSunriseTime()) / 3600 *
                    forecast.getDaily().getData().get(i).getCloudCover()) + "");
        }
        return sunshineList;
    }

    private String getSunTime(Forecast forecastData) {
        int sunriseTime = forecastData.getDaily().getData().get(0).getSunriseTime();
        int sunsetTime = forecastData.getDaily().getData().get(0).getSunsetTime();
        int currentTime = forecastData.getCurrently().getTime();

        String sunRemaining = "0h";
        if (currentTime > sunriseTime && currentTime < sunsetTime) {
            sunRemaining = Math.round((sunsetTime - currentTime) / 3600) + "h";
        }
        return sunRemaining;
    }
}

