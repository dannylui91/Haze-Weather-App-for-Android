package nyc.c4q.dannylui.weatheralpha;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.io.IOException;

import nyc.c4q.dannylui.weatheralpha.adapters.PagerAdapter;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;
import nyc.c4q.dannylui.weatheralpha.network.LocationService;
import nyc.c4q.dannylui.weatheralpha.network.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();

    public final static String API_KEY = BuildConfig.API_KEY;
    public final static String IP_API_BASE_URL = "http://ip-api.com/";
    public final static String DARK_SKY_BASE_URL = "https://api.darksky.net/";

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        findGeoLocation();
    }

    public void findGeoLocation() {
        Retrofit retrofit = buildRetrofit(IP_API_BASE_URL);
        LocationService service = retrofit.create(LocationService.class);
        Call<Location> call = service.getLocation();
        call.enqueue(getCallback(call, null));
    }

    public void findForecast(Location location) {
        Retrofit retrofit = buildRetrofit(DARK_SKY_BASE_URL);
        WeatherService service = retrofit.create(WeatherService.class);
        Call<Forecast> call = service.getForecast(API_KEY, location.getLat() + "," + location.getLon());
        System.out.println(call.toString());
        call.enqueue(getCallback(call, location));
    }

    public Retrofit buildRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public Callback getCallback(Call call, final Object optionalLocation) {
        Callback callback = new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    if(response.isSuccessful()) {
                        handleResponses(response, optionalLocation);
                    } else {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    }
                } catch(IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        };
        return callback;
    }

    public  void handleResponses(Response response, Object optionalLocation) {

        if (response.body() instanceof Location) {
            Location location = (Location) response.body();
            findForecast(location);
        }
        if (response.body() instanceof Forecast) {
            Forecast forecastData = (Forecast) response.body();
            PagerAdapter pagerAdapter = (PagerAdapter) viewPager.getAdapter();
            pagerAdapter.update((Location) optionalLocation, forecastData);
        }
    }
}
