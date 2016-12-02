package nyc.c4q.dannylui.weatheralpha.network.ipapi;

import nyc.c4q.dannylui.weatheralpha.models.ipapi.Location;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dannylui on 11/1/16.
 */
public class GeolocationClient {
    public static final String API_URL = "http://ip-api.com/";

    private static GeolocationClient instance;

    private final GeolocationApi api;

    public static GeolocationClient getInstance() {
        if (instance == null) {
            instance = new GeolocationClient();
        }
        return instance;
    }

    private GeolocationClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(GeolocationApi.class);
    }

    public Call<Location> getLocation() {
        return api.getLocation();
    }
}
