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
import nyc.c4q.dannylui.weatheralpha.models.darksky.Forecast;
import nyc.c4q.dannylui.weatheralpha.utility.SpannableUtil;

/**
 * Created by dannylui on 10/25/16.
 */

public class CurrentWeatherFragment extends Fragment {
    private View rootView;
    private Forecast forecastData;

    private TextView currentHiView;
    private TextView currentLoView;
    private TextView currentTempView;
    private TextView currentFeelView;
    private TextView currentWindSpeedView;
    private TextView currentRainChanceView;

    private static int origWidth;
    private static int origHeight;

    private static boolean animationEnd = false;
    private static boolean isDisabled = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_current, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentHiView = (TextView) view.findViewById(R.id.current_hi_view);
        currentLoView = (TextView) view.findViewById(R.id.current_lo_view);
        currentTempView = (TextView) view.findViewById(R.id.current_temp_view);
        currentFeelView = (TextView) view.findViewById(R.id.current_feel_view);
        currentWindSpeedView = (TextView) view.findViewById(R.id.current_wind_view);
        currentRainChanceView = (TextView) view.findViewById(R.id.current_rain_chance_view);

        if (forecastData != null) {
            setForecastData();
        }

        origWidth = currentTempView.getLayoutParams().width;
        origHeight = currentTempView.getLayoutParams().height;

        disableViews();

        currentTempView.setClickable(true);
        currentTempView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDisabled) {
                    enableViews();
                    ViewGroup.LayoutParams params = currentTempView.getLayoutParams();
                    params.height = currentTempView.getHeight() - 100;
                    params.width = currentTempView.getWidth() - 100;
                    currentTempView.requestLayout();
                    isDisabled = false;
                }
                else {
                    if (animationEnd) {
                        animationEnd = false;
                        disableViews();
                        ViewGroup.LayoutParams params = currentTempView.getLayoutParams();
                        params.height = currentTempView.getHeight() + 100;
                        params.width = currentTempView.getWidth() + 100;

                        currentTempView.requestLayout();
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
        CharSequence currentHigh = SpannableUtil.changeTextSize(String.valueOf(Math.round(forecastData.getDaily().getData().get(0).getTemperatureMax()) + "°"), "\nHigh");
        CharSequence currentLow = SpannableUtil.changeTextSize(String.valueOf(Math.round(forecastData.getDaily().getData().get(0).getTemperatureMin()) + "°"), "\nLow");;
        CharSequence currentTemp = String.valueOf(Math.round(forecastData.getCurrently().getTemperature()) + "°");
        CharSequence currentFeel = SpannableUtil.changeTextSize(String.valueOf(Math.round(forecastData.getCurrently().getApparentTemperature()) + "°"), "\nFeels");
        CharSequence currentWindSpeed = SpannableUtil.changeTextSize(String.valueOf(Math.round(forecastData.getCurrently().getWindSpeed())), "\nm/h");
        CharSequence currentRainChance = SpannableUtil.changeTextSize(String.valueOf((int)(forecastData.getCurrently().getPrecipProbability() * 100)), "\n% Rain");


        currentHiView.setText(currentHigh);
        currentLoView.setText(currentLow);
        currentTempView.setText(currentTemp);
        currentFeelView.setText(currentFeel);
        currentWindSpeedView.setText(currentWindSpeed);
        currentRainChanceView.setText(currentRainChance);
    }

    public void disableViews() {
        currentHiView.setVisibility(View.INVISIBLE);
        currentLoView.setVisibility(View.INVISIBLE);
        currentFeelView.setVisibility(View.INVISIBLE);
        currentWindSpeedView.setVisibility(View.INVISIBLE);
        currentRainChanceView.setVisibility(View.INVISIBLE);
    }

    public void enableViews() {
        currentHiView.setVisibility(View.VISIBLE);
        currentLoView.setVisibility(View.VISIBLE);
        currentFeelView.setVisibility(View.VISIBLE);
        currentWindSpeedView.setVisibility(View.VISIBLE);
        currentRainChanceView.setVisibility(View.VISIBLE);

        setAnimationStuff(currentHiView);
        setAnimationStuff(currentLoView);
        setAnimationStuff(currentFeelView);
        setAnimationStuff(currentWindSpeedView);
        setAnimationStuff(currentRainChanceView);
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
