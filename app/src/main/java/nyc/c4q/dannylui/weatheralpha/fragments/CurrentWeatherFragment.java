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
import android.widget.RelativeLayout;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.TempModel;

/**
 * Created by dannylui on 10/25/16.
 */

public class CurrentWeatherFragment extends Fragment {
    private View rootView;
    private TempModel tempModel;

    private RelativeLayout currentHiView;
    private RelativeLayout currentLoView;
    private TextView currentTempView;
    private RelativeLayout currentFeelView;
    private RelativeLayout currentWindSpeedView;
    private RelativeLayout currentRainChanceView;

    private TextView highTempTv;
    private TextView lowTempTv;
    private TextView feelTempTv;
    private TextView windSpeedTv;
    private TextView windDirectionTv;

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
        currentHiView = (RelativeLayout) view.findViewById(R.id.current_hi_view);
        currentLoView = (RelativeLayout) view.findViewById(R.id.current_lo_view);
        currentTempView = (TextView) view.findViewById(R.id.current_temp_view);
        currentFeelView = (RelativeLayout) view.findViewById(R.id.current_feel_view);
        currentWindSpeedView = (RelativeLayout) view.findViewById(R.id.current_windspeed_view);
        currentRainChanceView = (RelativeLayout) view.findViewById(R.id.current_winddirection_view);

        highTempTv = (TextView) view.findViewById(R.id.tv_hightemp);
        lowTempTv = (TextView) view.findViewById(R.id.tv_lowtemp);
        feelTempTv = (TextView) view.findViewById(R.id.tv_feeltemp);
        windSpeedTv = (TextView) view.findViewById(R.id.tv_windspeed);
        windDirectionTv = (TextView) view.findViewById(R.id.tv_winddirection);

        if (tempModel != null) {
            attachDataToViews(tempModel);
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
                } else {
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

    public void updateData(TempModel tempModel) {
        this.tempModel = tempModel;
        if (rootView != null) {
            attachDataToViews(tempModel);
        }
    }

    public void attachDataToViews(TempModel tempModel) {

        highTempTv.setText(tempModel.getHighTemp());
        lowTempTv.setText(tempModel.getLowTemp());
        currentTempView.setText(tempModel.getCurrentTemp());
        feelTempTv.setText(tempModel.getFeelTemp());
        windSpeedTv.setText(tempModel.getWindSpeed());
        windDirectionTv.setText(tempModel.getWindDirection());
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

    public void setAnimationStuff(RelativeLayout t) {
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
