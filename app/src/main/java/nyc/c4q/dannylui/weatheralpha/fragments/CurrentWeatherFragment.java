package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Currently;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;
import nyc.c4q.dannylui.weatheralpha.utility.SpannableUtil;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by dannylui on 10/25/16.
 */

public class CurrentWeatherFragment extends Fragment implements UpdateableFragment {
    private TextView currentHiView;
    private TextView currentLoView;
    private TextView currentTempView;
    private TextView currentFeelView;
    private TextView currentWindSpeedView;
    private TextView currentRainChanceView;

    private static int origWidth;
    private static int origHeight;


    private static boolean isDisabled = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_weather_fragment, container, false);
        currentHiView = (TextView) view.findViewById(R.id.current_hi_view);
        currentLoView = (TextView) view.findViewById(R.id.current_lo_view);
        currentTempView = (TextView) view.findViewById(R.id.current_temp_view);
        currentFeelView = (TextView) view.findViewById(R.id.current_feel_view);
        currentWindSpeedView = (TextView) view.findViewById(R.id.current_wind_view);
        currentRainChanceView = (TextView) view.findViewById(R.id.current_rain_chance_view);



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
                    disableViews();
                    ViewGroup.LayoutParams params = currentTempView.getLayoutParams();
                    params.height = currentTempView.getHeight() + 100;
                    params.width = currentTempView.getWidth() + 100;

                    currentTempView.requestLayout();
                    isDisabled = true;
                }
            }
        });
        return view;
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
        fadeIn.setDuration(1000);
/*
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
*/
        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        //animation.addAnimation(fadeOut);
        t.setAnimation(animation);
    }

    @Override
    public void update(Location locationData, Forecast forecastData) {

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
}
