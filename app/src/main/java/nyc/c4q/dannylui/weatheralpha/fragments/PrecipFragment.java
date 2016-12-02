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
 * Created by dannylui on 11/29/16.
 */

public class PrecipFragment extends Fragment {
    private View rootView;
    private Forecast forecastData;

    private TextView rainChance;
    private TextView umbrellaIcon;
    private TextView currentRainFall;
    private TextView currentAirPressure;
    private TextView currentHumidity;

    private static int origWidth;
    private static int origHeight;

    private static boolean animationEnd = false;
    private static boolean isDisabled = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_precip, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rainChance = (TextView) view.findViewById(R.id.current_rainchance);
        umbrellaIcon = (TextView) view.findViewById(R.id.current_umbrella);
        currentRainFall = (TextView) view.findViewById(R.id.current_rainfall);
        currentAirPressure = (TextView) view.findViewById(R.id.current_pressure);
        currentHumidity = (TextView) view.findViewById(R.id.current_humidity);

        if (forecastData != null) {
            setForecastData();
        }

        origWidth = rainChance.getLayoutParams().width;
        origHeight = rainChance.getLayoutParams().height;

        disableViews();

        rainChance.setClickable(true);
        rainChance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDisabled) {
                    enableViews();
                    ViewGroup.LayoutParams params = rainChance.getLayoutParams();
                    params.height = rainChance.getHeight() - 100;
                    params.width = rainChance.getWidth() - 100;
                    rainChance.requestLayout();
                    isDisabled = false;
                }
                else {
                    if (animationEnd) {
                        animationEnd = false;
                        disableViews();
                        ViewGroup.LayoutParams params = rainChance.getLayoutParams();
                        params.height = rainChance.getHeight() + 100;
                        params.width = rainChance.getWidth() + 100;

                        rainChance.requestLayout();
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
        CharSequence currentRainChance = SpannableUtil.changeTextSize(String.valueOf((int)(forecastData.getCurrently().getPrecipProbability() * 100)), "\n% Rain");

        rainChance.setText(currentRainChance);
    }

    public void disableViews() {
        umbrellaIcon.setVisibility(View.INVISIBLE);
        currentRainFall.setVisibility(View.INVISIBLE);
        currentAirPressure.setVisibility(View.INVISIBLE);
        currentHumidity.setVisibility(View.INVISIBLE);
    }

    public void enableViews() {
        umbrellaIcon.setVisibility(View.VISIBLE);
        currentRainFall.setVisibility(View.VISIBLE);
        currentAirPressure.setVisibility(View.VISIBLE);
        currentHumidity.setVisibility(View.VISIBLE);

        setAnimationStuff(umbrellaIcon);
        setAnimationStuff(currentRainFall);
        setAnimationStuff(currentAirPressure);
        setAnimationStuff(currentHumidity);
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
