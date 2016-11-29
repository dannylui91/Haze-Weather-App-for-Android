package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HeaderDailyFragment extends Fragment {
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

    private Forecast forecast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    }

    public void setData(Forecast forecast) {
        dayOneView.setText(ConvertUnixTs.toDayOfWeek(forecast.getDaily().getData().get(0).getTime()));
        dayTwoView.setText(ConvertUnixTs.toDayOfWeek(forecast.getDaily().getData().get(1).getTime()));
        dayThreeView.setText(ConvertUnixTs.toDayOfWeek(forecast.getDaily().getData().get(2).getTime()));
        dayFourView.setText(ConvertUnixTs.toDayOfWeek(forecast.getDaily().getData().get(3).getTime()));
        dayFiveView.setText(ConvertUnixTs.toDayOfWeek(forecast.getDaily().getData().get(4).getTime()));

        dayOneCircleView.setText(String.valueOf(Math.round(forecast.getDaily().getData().get(0).getTemperatureMax())));
        dayTwoCircleView.setText(String.valueOf(Math.round(forecast.getDaily().getData().get(1).getTemperatureMax())));
        dayThreeCircleView.setText(String.valueOf(Math.round(forecast.getDaily().getData().get(2).getTemperatureMax())));
        dayFourCircleView.setText(String.valueOf(Math.round(forecast.getDaily().getData().get(3).getTemperatureMax())));
        dayFiveCircleView.setText(String.valueOf(Math.round(forecast.getDaily().getData().get(4).getTemperatureMax())));

        setCircleViewPositions(forecast);
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
                dayView.animate().yBy(50f);
            }
            if (list.get(1) == day) {
                dayView.animate().yBy(25f);
            }
            if (list.get(2) == day) {
                dayView.animate().yBy(0f);
            }
            if (list.get(3) == day) {
                dayView.animate().yBy(-25f);
            }
            if (list.get(4) == day) {
                dayView.animate().yBy(-50f);
            }
        }
    }


}
