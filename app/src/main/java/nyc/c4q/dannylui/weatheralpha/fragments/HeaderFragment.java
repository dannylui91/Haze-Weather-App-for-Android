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
import nyc.c4q.dannylui.weatheralpha.models.RainModel;
import nyc.c4q.dannylui.weatheralpha.models.SunModel;
import nyc.c4q.dannylui.weatheralpha.models.TempModel;
import nyc.c4q.dannylui.weatheralpha.models.darksky.Forecast;
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

    private List<String> sunshineList;
    private List<String> highTempList;
    private List<String> rainChanceList;

    private Forecast forecastData;
    private static int currentPosition;

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

//        if (forecastData != null) {
//            setData();
//        }
    }

    public void updateAll(Forecast forecast, SunModel sunModel, TempModel tempModel, RainModel rainModel) {
        forecastData = forecast;
        sunshineList = sunModel.getSunshineList();
        highTempList = tempModel.getHighTempList();
        rainChanceList = rainModel.getRainChanceList();
        changeData(currentPosition);
    }

    public void changeData(int position) {
        currentPosition = position;
        switch (position) {
            case 0:
                setSunlightData();
                setSunTheme();
                break;
            case 1:
                setCurrentTempData();
                setCandyAppleTheme();
                break;
            case 2:
                setRainChanceData();
                setRainTheme();
                break;
        }
    }

    private void setCurrentTempData() {
        if (highTempList != null) {
            setData(highTempList);
        }
    }

    private void setSunlightData() {
        if (sunshineList != null) {
            setData(sunshineList);
        }
    }

    private void setRainChanceData() {
        if (rainChanceList != null) {
            setData(rainChanceList);
        }
    }

    public void setData(List<String> dataList) {
        dayOneView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(0).getTime()));
        dayTwoView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(1).getTime()));
        dayThreeView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(2).getTime()));
        dayFourView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(3).getTime()));
        dayFiveView.setText(ConvertUnixTs.toDayOfWeek(forecastData.getDaily().getData().get(4).getTime()));

        dayOneCircleView.setText(dataList.get(0));
        dayTwoCircleView.setText(dataList.get(1));
        dayThreeCircleView.setText(dataList.get(2));
        dayFourCircleView.setText(dataList.get(3));
        dayFiveCircleView.setText(dataList.get(4));
        //dayFiveCircleView.setText(String.valueOf(Math.round(forecastData.getDaily().getData().get(4).getTemperatureMax())));

        setCircleViewPositions(forecastData, dataList);
    }

    public void setCircleViewPositions(Forecast forecast, List<String> dataList) {
//        long dayOneTemp = Math.round(forecast.getDaily().getData().get(0).apparentTemperatureMax);
//        long dayTwoTemp = Math.round(forecast.getDaily().getData().get(1).apparentTemperatureMax);
//        long dayThreeTemp = Math.round(forecast.getDaily().getData().get(2).apparentTemperatureMax);
//        long dayFourTemp = Math.round(forecast.getDaily().getData().get(3).apparentTemperatureMax);
//        long dayFiveTemp = Math.round(forecast.getDaily().getData().get(4).apparentTemperatureMax);

        long dayOneTemp = Integer.parseInt(dataList.get(0));
        long dayTwoTemp = Integer.parseInt(dataList.get(1));
        long dayThreeTemp = Integer.parseInt(dataList.get(2));
        long dayFourTemp = Integer.parseInt(dataList.get(3));
        long dayFiveTemp = Integer.parseInt(dataList.get(4));


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
