package nyc.c4q.dannylui.weatheralpha;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import nyc.c4q.dannylui.weatheralpha.adapters.ViewPagerAdapter;
import nyc.c4q.dannylui.weatheralpha.fragments.CurrentWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.EmptyFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.HeaderDailyFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.PrecipFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.SunFragment;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;
import nyc.c4q.dannylui.weatheralpha.network.DarkSkyClient;
import nyc.c4q.dannylui.weatheralpha.network.GeolocationClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {
    private FrameLayout movingCircle;

    private static final String TAG = MainActivity.class.getName();
    private GeolocationClient geolocationClient;
    private DarkSkyClient darkSkyClient;
    private String currentLocation;

    private ViewPager pager;

    private TextView header;
    private FragmentManager fragmentManager;


    private ImageView firstDotView;
    private ImageView secondDotView;
    private ImageView thirdDotView;

    private CurrentWeatherFragment cwFrag;
    private PrecipFragment pFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBackgroundImage();
        movingCircle = (FrameLayout) findViewById(R.id.moving_circle);

        header = (TextView) findViewById(R.id.header_view);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_top, new HeaderDailyFragment())
                .commit();

        initViewPager();
        initViewPagerDots();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            System.out.println("WIDTH IS " + width);
            System.out.println("HEIGHT IS " + height);

            pager.getLayoutParams().width = width;
            pager.getLayoutParams().height = width;
        }



        //Get Weather Data
        findGeoLocation();
    }

    private void setBackgroundImage() {
        ImageView backgroundGifView = (ImageView) findViewById(R.id.background_gif_view);
        Glide.with(this).load(R.drawable.animation_bg_candy).into(backgroundGifView);
    }

    public void initViewPagerDots() {
        firstDotView = (ImageView) findViewById(R.id.first_dot_view);
        secondDotView = (ImageView) findViewById(R.id.second_dot_view);
        thirdDotView = (ImageView) findViewById(R.id.third_dot_view);
    }

    public void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmptyFragment());
        adapter.addFragment(new EmptyFragment());
        adapter.addFragment(new EmptyFragment());

        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);
        pager.addOnPageChangeListener(onPageChangeListener());
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setAlpha(normalizedposition);
            }
        });
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
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.moving_circle, cwFrag)
                                .commit();
                        movingCircle.animate().translationY(0f);
                        header.setText(currentLocation);
                        firstDotView.setBackgroundResource(R.drawable.dot_selected);
                        secondDotView.setBackgroundResource(R.drawable.dot);
                        thirdDotView.setBackgroundResource(R.drawable.dot);
                        break;
                    case 1:
                        System.out.println("Page 2");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.moving_circle, new PrecipFragment())
                                .commit();
                        movingCircle.animate().translationY(400f);
                        header.setText("Next 24 Hours");
                        firstDotView.setBackgroundResource(R.drawable.dot);
                        secondDotView.setBackgroundResource(R.drawable.dot_selected);
                        thirdDotView.setBackgroundResource(R.drawable.dot);
                        break;
                    case 2:
                        System.out.println("Page 3");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.moving_circle, new SunFragment())
                                .commit();
                        movingCircle.animate().translationY(-600f);
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
        geolocationClient = geolocationClient.getInstance();
        Call<Location> call = geolocationClient.getLocation();
        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                Location location = response.body();
                header.setText(currentLocation = location.getCity() + ", " + location.getRegionName());
                findForecast(location);
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    public void findForecast(Location location) {
        darkSkyClient = darkSkyClient.getInstance();
        Call<Forecast> call = darkSkyClient.getForecast(location.getLat() + "," + location.getLon());
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                ViewPagerAdapter viewPagerAdapter = (ViewPagerAdapter) pager.getAdapter();
                viewPagerAdapter.update(forecast);
                cwFrag = new CurrentWeatherFragment();
                cwFrag.update(forecast);

                HeaderDailyFragment fragment = (HeaderDailyFragment) fragmentManager.findFragmentById(R.id.fragment_top);
                fragment.setData(forecast);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
