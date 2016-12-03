package nyc.c4q.dannylui.weatheralpha;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import nyc.c4q.dannylui.weatheralpha.adapters.ViewPagerAdapter;
import nyc.c4q.dannylui.weatheralpha.fragments.CircleFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.EmptyFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.FooterFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.HeaderFragment;
import nyc.c4q.dannylui.weatheralpha.models.RainModel;
import nyc.c4q.dannylui.weatheralpha.models.SunModel;
import nyc.c4q.dannylui.weatheralpha.models.TempModel;
import nyc.c4q.dannylui.weatheralpha.models.ipapi.Location;
import nyc.c4q.dannylui.weatheralpha.network.WeatherCallback;
import nyc.c4q.dannylui.weatheralpha.network.WeatherFactory;

public class MainActivity extends FragmentActivity implements WeatherCallback {
    private ViewPager pager;

    private CircleFragment circleFragment;
    private HeaderFragment headerFragment;
    private FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveWeatherData();
        initializeCircle();
        inflateFiveDayHeader();
        inflateFooter();
        setupViewPager();
    }

    private void retrieveWeatherData() {
        WeatherFactory weatherFactory = new WeatherFactory(this);
        weatherFactory.retrieveWeatherData();
    }

    private void initializeCircle() {
        FrameLayout movingCircle = (FrameLayout) findViewById(R.id.moving_circle);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            movingCircle.getLayoutParams().width = width;
            movingCircle.getLayoutParams().height = width;
        }

        circleFragment = new CircleFragment();
        System.out.println("Inflating circleFrag");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.moving_circle, circleFragment)
                .commit();
        pager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void inflateFiveDayHeader() {
        headerFragment = new HeaderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_top, headerFragment)
                .commit();
    }

    private void inflateFooter() {
        footerFragment = new FooterFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.footer, footerFragment)
                .commit();
    }

    public void setupViewPager() {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmptyFragment());
        adapter.addFragment(new EmptyFragment());
        adapter.addFragment(new EmptyFragment());

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

        pager.post(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(adapter.CENTER_PAGE);
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
                ((EmptyFragment)(((ViewPagerAdapter)pager.getAdapter()).getFragment(position))).changeBackground(position);
                circleFragment.replaceContainer(position);
                headerFragment.changeData(position);
                footerFragment.changeDotPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void getForecastData(SunModel sunModel, TempModel tempModel, RainModel rainModel) {
        System.out.println("Got forecast data");
        headerFragment.updateAll(sunModel, tempModel, rainModel);
        circleFragment.updateAll(sunModel, tempModel, rainModel);
    }

    @Override
    public void getLocationData(Location data) {
        System.out.println("Got location data");
    }
}
