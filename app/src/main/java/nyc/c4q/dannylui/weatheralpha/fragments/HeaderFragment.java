package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.utility.ConvertUnixTs;

/**
 * Created by dannylui on 11/6/16.
 */

public class HeaderFragment extends Fragment {
    private LinearLayout dayOneBg;
    private LinearLayout dayTwoBg;
    private LinearLayout dayThreeBg;
    private LinearLayout dayFourBg;
    private LinearLayout dayFiveBg;

    private TextView dayOneView;
    private TextView dayTwoView;
    private TextView dayThreeView;
    private TextView dayFourView;
    private TextView dayFiveView;

    private TextView dayOneCircleView;
    private TextView dayTwoCircleView;
    private TextView dayThreeCircleView;
    private TextView dayFourCircleView;
    private TextView dayFiveCircleView;

    private Forecast forecastData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dayOneBg = (LinearLayout) view.findViewById(R.id.day_one_bg);
        dayTwoBg = (LinearLayout) view.findViewById(R.id.day_two_bg);
        dayThreeBg = (LinearLayout) view.findViewById(R.id.day_three_bg);
        dayFourBg = (LinearLayout) view.findViewById(R.id.day_four_bg);
        dayFiveBg = (LinearLayout) view.findViewById(R.id.day_five_bg);

        dayOneView = (TextView) view.findViewById(R.id.day_one_view);
        dayTwoView = (TextView) view.findViewById(R.id.day_two_view);
        dayThreeView = (TextView) view.findViewById(R.id.day_three_view);
        dayFourView = (TextView) view.findViewById(R.id.day_four_view);
        dayFiveView = (TextView) view.findViewById(R.id.day_five_view);

        dayOneCircleView = (TextView) view.findViewById(R.id.day_one_circle_view);
        dayTwoCircleView = (TextView) view.findViewById(R.id.day_two_circle_view);
        dayThreeCircleView = (TextView) view.findViewById(R.id.day_three_circle_view);
        dayFourCircleView = (TextView) view.findViewById(R.id.day_four_circle_view);
        dayFiveCircleView = (TextView) view.findViewById(R.id.day_five_circle_view);

        if (forecastData != null) {
            setData();
        }
    }

    public void update(Forecast forecast) {
        forecastData = forecast;
        setData();
    }

    public void setData() {
        dayOneView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(0).getTime()));
        dayTwoView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(1).getTime()));
        dayThreeView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(2).getTime()));
        dayFourView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(3).getTime()));
        dayFiveView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(4).getTime()));

        dayOneCircleView.setText(String.valueOf(Math.round(forecastData.getDaily().getData().get(0).getTemperatureMax())));
        dayTwoCircleView.setText(String.valueOf(Math.round(forecastData.getDaily().getData().get(1).getTemperatureMax())));
        dayThreeCircleView.setText(String.valueOf(Math.round(forecastData.getDaily().getData().get(2).getTemperatureMax())));
        dayFourCircleView.setText(String.valueOf(Math.round(forecastData.getDaily().getData().get(3).getTemperatureMax())));
        dayFiveCircleView.setText(String.valueOf(Math.round(forecastData.getDaily().getData().get(4).getTemperatureMax())));

        setCircleViewPositions(forecastData);
    }

    public void setCircleViewPositions(Forecast forecast) {
        long dayOneTemp = Math.round(forecast.getDaily().getData().get(0).apparentTemperatureMax);
        long dayTwoTemp = Math.round(forecast.getDaily().getData().get(1).apparentTemperatureMax);
        long dayThreeTemp = Math.round(forecast.getDaily().getData().get(2).apparentTemperatureMax);
        long dayFourTemp = Math.round(forecast.getDaily().getData().get(3).apparentTemperatureMax);
        long dayFiveTemp = Math.round(forecast.getDaily().getData().get(4).apparentTemperatureMax);

        List<Long> list = Arrays.asList(dayOneTemp, dayTwoTemp, dayThreeTemp, dayFourTemp, dayFiveTemp);
        Collections.sort(list);

        animateCircle(dayOneTemp, dayOneCircleView, list);
        animateCircle(dayTwoTemp, dayTwoCircleView, list);
        animateCircle(dayThreeTemp, dayThreeCircleView, list);
        animateCircle(dayFourTemp, dayFourCircleView, list);
        animateCircle(dayFiveTemp, dayFiveCircleView, list);
    }

    private void animateCircle(long day, TextView dayView, List<Long> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(0) == day) {
                dayView.animate().translationY(50f);
            }
            if (list.get(1) == day) {
                dayView.animate().translationY(25f);
            }
            if (list.get(2) == day) {
                dayView.animate().translationY(0f);
            }
            if (list.get(3) == day) {
                dayView.animate().translationY(-25f);
            }
            if (list.get(4) == day) {
                dayView.animate().translationY(-50f);
            }
        }
    }


    public void changeData(int position) {
        switch (position) {
            case 0:
                setDefaultPosition();
                setSunTheme();
                break;
            case 1:
                setCandyAppleTheme();
                break;
            case 2:
                setDefaultPosition();
                setRainTheme();
                break;
        }
    }

    public void setDefaultPosition() {
        dayOneCircleView.animate().translationY(0f);
        dayTwoCircleView.animate().translationY(0f);
        dayThreeCircleView.animate().translationY(0f);
        dayFourCircleView.animate().translationY(0f);
        dayFiveCircleView.animate().translationY(0f);
    }

    public void setSunTheme() {
        dayOneBg.setBackgroundResource(R.color.sunOne);
        dayTwoBg.setBackgroundResource(R.color.sunTwo);
        dayThreeBg.setBackgroundResource(R.color.sunThree);
        dayFourBg.setBackgroundResource(R.color.sunFour);
        dayFiveBg.setBackgroundResource(R.color.sunFive);
    }

    public void setCandyAppleTheme() {
        dayOneBg.setBackgroundResource(R.color.candyAppleOne);
        dayTwoBg.setBackgroundResource(R.color.candyAppleTwo);
        dayThreeBg.setBackgroundResource(R.color.candyAppleThree);
        dayFourBg.setBackgroundResource(R.color.candyAppleFour);
        dayFiveBg.setBackgroundResource(R.color.candyAppleFive);
    }

    public void setRainTheme() {
        dayOneBg.setBackgroundResource(R.color.rainOne);
        dayTwoBg.setBackgroundResource(R.color.rainTwo);
        dayThreeBg.setBackgroundResource(R.color.rainThree);
        dayFourBg.setBackgroundResource(R.color.rainFour);
        dayFiveBg.setBackgroundResource(R.color.rainFive);
    }
}
