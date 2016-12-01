package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;

/**
 * Created by dannylui on 11/29/16.
 */

public class SunFragment extends Fragment {
    private View rootView;
    private Forecast forecastData;

    private TextView cloudIcon;
    private TextView uvIndex;
    private TextView sunLeft;
    private TextView sunrise;
    private TextView sunset;
    private TextView cloudCoverage;

    private static int origWidth;
    private static int origHeight;

    private static boolean animationEnd = false;
    private static boolean isDisabled = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sun, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cloudIcon = (TextView) view.findViewById(R.id.current_cloud);
        uvIndex = (TextView) view.findViewById(R.id.current_uv);
        sunLeft = (TextView) view.findViewById(R.id.current_sunleft);
        sunrise = (TextView) view.findViewById(R.id.current_sunrise);
        sunset = (TextView) view.findViewById(R.id.current_sunset);
        cloudCoverage = (TextView) view.findViewById(R.id.current_cloudcoverage);

        if (forecastData != null) {
            setForecastData();
        }

        origWidth = sunLeft.getLayoutParams().width;
        origHeight = sunLeft.getLayoutParams().height;

        disableViews();

        sunLeft.setClickable(true);
        sunLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDisabled) {
                    enableViews();
                    ViewGroup.LayoutParams params = sunLeft.getLayoutParams();
                    params.height = sunLeft.getHeight() - 100;
                    params.width = sunLeft.getWidth() - 100;
                    sunLeft.requestLayout();
                    isDisabled = false;
                }
                else {
                    if (animationEnd) {
                        animationEnd = false;
                        disableViews();
                        ViewGroup.LayoutParams params = sunLeft.getLayoutParams();
                        params.height = sunLeft.getHeight() + 100;
                        params.width = sunLeft.getWidth() + 100;

                        sunLeft.requestLayout();
                        isDisabled = true;
                    }
                }
            }
        });
    }

    public void update(Forecast data) {
        forecastData = data;
        if (rootView != null) {
            setForecastData();
        }
    }

    public void setForecastData() {
        String uv = String.valueOf("0");
        getSunTime();
        uvIndex.setText(uv);
    }

    private void getSunTime() {
        int sunriseTime = forecastData.getDaily().getData().get(0).getSunriseTime();
        int sunsetTime = forecastData.getDaily().getData().get(0).getSunsetTime();
        int currentTime = forecastData.getCurrently().getTime();


        System.out.println("Sunrise " + sunriseTime);
        System.out.println("Sunset " + sunsetTime);
        System.out.println("CurrentTime " + currentTime);

        double hoursBeforeSunrise = -1;
        double hoursBeforeSunset = -1;
        if (currentTime < sunriseTime) {
            hoursBeforeSunrise = (sunriseTime - currentTime) / 3600;
        }
        else if (currentTime > sunriseTime && currentTime < sunsetTime) {
            hoursBeforeSunset = (sunsetTime - currentTime) / 3600;
        } else if (currentTime > sunsetTime) {
            int sunriseTime2 = forecastData.getDaily().getData().get(1).getSunriseTime();
            hoursBeforeSunrise = (sunriseTime2 - currentTime) / 3600;
        }

        if (hoursBeforeSunrise != -1) {
            System.out.println(hoursBeforeSunrise);
            sunLeft.setText(String.valueOf(Math.round(hoursBeforeSunrise)) + "h");
        }
        if (hoursBeforeSunset != -1) {
            System.out.println(hoursBeforeSunset);
            sunLeft.setText(String.valueOf(Math.round(hoursBeforeSunset)) + "h");
        }
    }

    public void disableViews() {
        cloudIcon.setVisibility(View.INVISIBLE);
        uvIndex.setVisibility(View.INVISIBLE);
        sunrise.setVisibility(View.INVISIBLE);
        sunset.setVisibility(View.INVISIBLE);
        cloudCoverage.setVisibility(View.INVISIBLE);
    }

    public void enableViews() {
        cloudIcon.setVisibility(View.VISIBLE);
        uvIndex.setVisibility(View.VISIBLE);
        sunrise.setVisibility(View.VISIBLE);
        sunset.setVisibility(View.VISIBLE);
        cloudCoverage.setVisibility(View.VISIBLE);

        setAnimationStuff(cloudIcon);
        setAnimationStuff(uvIndex);
        setAnimationStuff(sunrise);
        setAnimationStuff(sunset);
        setAnimationStuff(cloudCoverage);
    }

    public void setAnimationStuff(TextView t) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(500);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationEnd = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        t.setAnimation(fadeIn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        animationEnd = false;
        isDisabled = true;
        System.out.println("Destroyed View");
    }
}
