package nyc.c4q.dannylui.weatheralpha;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import nyc.c4q.dannylui.weatheralpha.adapters.PagerAdapter;
import nyc.c4q.dannylui.weatheralpha.fragments.CurrentWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.HourlyWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;
import nyc.c4q.dannylui.weatheralpha.network.LocationService;
import nyc.c4q.dannylui.weatheralpha.network.WeatherService;
import pl.droidsonroids.gif.GifDrawable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();
    private String currentLocation;

    public final static String API_KEY = BuildConfig.API_KEY;
    public final static String IP_API_BASE_URL = "http://ip-api.com/";
    public final static String DARK_SKY_BASE_URL = "https://api.darksky.net/";

    GifDrawable gifFromResource;

    private ViewPager viewPager;

    private TextView header;
    private ImageView firstDotView;
    private ImageView secondDotView;
    private ImageView thirdDotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = (TextView) findViewById(R.id.header_view) ;
        firstDotView = (ImageView) findViewById(R.id.first_dot_view);
        secondDotView = (ImageView) findViewById(R.id.second_dot_view);
        thirdDotView = (ImageView) findViewById(R.id.third_dot_view);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(onPageChangeListener());

        findGeoLocation();
    }

    public ViewPager.OnPageChangeListener onPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:
                        System.out.println("Page 1");
                        header.setText(currentLocation);
                        firstDotView.setBackgroundResource(R.drawable.dot_selected);
                        secondDotView.setBackgroundResource(R.drawable.dot);
                        thirdDotView.setBackgroundResource(R.drawable.dot);
                        break;
                    case 1:
                        System.out.println("Page 2");
                        header.setText("Next 24 Hours");
                        firstDotView.setBackgroundResource(R.drawable.dot);
                        secondDotView.setBackgroundResource(R.drawable.dot_selected);
                        thirdDotView.setBackgroundResource(R.drawable.dot);
                        break;
                    case 2:
                        System.out.println("Page 3");
                        header.setText("Next 7 Days");
                        firstDotView.setBackgroundResource(R.drawable.dot);
                        secondDotView.setBackgroundResource(R.drawable.dot);
                        thirdDotView.setBackgroundResource(R.drawable.dot_selected);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
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
            currentLocation = location.getCity() + ", " + location.getRegionName();
            header.setText(currentLocation);
            findForecast(location);
        }
        if (response.body() instanceof Forecast) {
            Forecast forecastData = (Forecast) response.body();
            PagerAdapter pagerAdapter = (PagerAdapter) viewPager.getAdapter();
            pagerAdapter.update((Location) optionalLocation, forecastData);
        }
    }
}
