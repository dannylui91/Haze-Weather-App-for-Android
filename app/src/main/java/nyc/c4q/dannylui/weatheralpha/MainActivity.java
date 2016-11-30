package nyc.c4q.dannylui.weatheralpha;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import nyc.c4q.dannylui.weatheralpha.adapters.ViewPagerAdapter;
import nyc.c4q.dannylui.weatheralpha.fragments.CircleContainerFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.CurrentWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.EmptyFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.FooterFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.HeaderFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.PrecipFragment;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;
import nyc.c4q.dannylui.weatheralpha.network.WeatherCallback;
import nyc.c4q.dannylui.weatheralpha.network.WeatherFactory;

public class MainActivity extends FragmentActivity implements WeatherCallback {
    private FrameLayout movingCircle;
    private ViewPager pager;

    private CircleContainerFragment circleFragment;
    private HeaderFragment fragmentTop;
    private FooterFragment fragmentBottom;
    private CurrentWeatherFragment cwFrag;
    private PrecipFragment pFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeatherInformation();
        setBackgroundImage();
        initialize();
        inflateFiveDayHeader();
        setupViewPager();
        inflateFooter();
    }

    private void getWeatherInformation() {
        WeatherFactory weatherFactory = new WeatherFactory(this);
    }

    private void setBackgroundImage() {
        ImageView backgroundGifView = (ImageView) findViewById(R.id.background_gif_view);
        Glide.with(this).load(R.drawable.animation_bg_candy).into(backgroundGifView);
    }

    private void initialize() {
        circleFragment = new CircleContainerFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.moving_circle, circleFragment)
                .commit();
        pager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void inflateFiveDayHeader() {
        fragmentTop = new HeaderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_top, fragmentTop)
                .commit();
    }

    private void inflateFooter() {
        fragmentBottom = new FooterFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.footer, fragmentBottom)
                .commit();
    }

    public void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmptyFragment());
        adapter.addFragment(new EmptyFragment());
        adapter.addFragment(new EmptyFragment());

        pager.setAdapter(adapter);
        //pager.setOffscreenPageLimit(2);
        pager.addOnPageChangeListener(onPageChangeListener());
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setAlpha(normalizedposition);
            }
        });

        setViewPagerWithSameWidthHeight();
    }

    private void setViewPagerWithSameWidthHeight() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            pager.getLayoutParams().width = width;
            pager.getLayoutParams().height = width;
        }
    }

    public ViewPager.OnPageChangeListener onPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        circleFragment.replaceContainer(position);
                        fragmentBottom.changeDotPosition(position);
                        break;
                    case 1:
                        circleFragment.replaceContainer(position);
                        fragmentBottom.changeDotPosition(position);
                        break;
                    case 2:
                        circleFragment.replaceContainer(position);
                        fragmentBottom.changeDotPosition(position);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void getForecastData(Forecast data) {
        System.out.println("Got forecast data");
        fragmentTop.update(data);
        cwFrag = new CurrentWeatherFragment();
        cwFrag.update(data);
    }

    @Override
    public void getLocationData(Location data) {
        System.out.println("Got location data");
    }
}
