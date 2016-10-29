package nyc.c4q.dannylui.weatheralpha.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import nyc.c4q.dannylui.weatheralpha.fragments.CurrentWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.DailyWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.fragments.HourlyWeatherFragment;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Hourly;
import nyc.c4q.dannylui.weatheralpha.models.Location;

/**
 * Created by dannylui on 10/25/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final static int NUMBER_OF_FRAGMENTS = 3;

    private CurrentWeatherFragment currentWeatherFragment;
    private HourlyWeatherFragment hourlyWeatherFragment;
    private DailyWeatherFragment dailyWeatherFragment;

    private Forecast forecastData;
    private Location locationData;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return this.currentWeatherFragment = new CurrentWeatherFragment();
            case 1:
                return this.hourlyWeatherFragment = new HourlyWeatherFragment();
            case 2:
                return this.dailyWeatherFragment = new DailyWeatherFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof CurrentWeatherFragment) {
            ((CurrentWeatherFragment) object).update(locationData, forecastData);
        }
        if (object instanceof HourlyWeatherFragment) {
            ((HourlyWeatherFragment) object).update(locationData, forecastData);
        }
        if (object instanceof DailyWeatherFragment) {
            ((DailyWeatherFragment) object).update(locationData, forecastData);
        }

        return super.getItemPosition(object);
    }

    public void update(Location newLocation, Forecast newForecast) {
        this.locationData = newLocation;
        this.forecastData = newForecast;
        notifyDataSetChanged();
    }

    public DailyWeatherFragment getDailyWeatherFragment() {
        return dailyWeatherFragment;
    }

    public HourlyWeatherFragment getHourlyWeatherFragment() {
        return hourlyWeatherFragment;
    }

    public CurrentWeatherFragment getCurrentWeatherFragment() {
        return currentWeatherFragment;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }
}
