package nyc.c4q.dannylui.weatheralpha.network;

import nyc.c4q.dannylui.weatheralpha.BuildConfig;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dannylui on 11/1/16.
 */

public class DarkSkyClient {
    public static final String API_URL = "https://api.darksky.net/";
    private static final String API_KEY = BuildConfig.API_KEY;

    private static DarkSkyClient instance;

    private final DarkSkyApi api;

    public static DarkSkyClient getInstance() {
        if (instance == null) {
            instance = new DarkSkyClient();
        }
        return instance;
    }

    private DarkSkyClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(DarkSkyApi.class);
    }

    public Call<Forecast> getForecast(String coordinates) {
        return api.getForecast(API_KEY, coordinates);
    }
}
