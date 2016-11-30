package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nyc.c4q.dannylui.weatheralpha.R;

/**
 * Created by dannylui on 11/30/16.
 */

public class FooterFragment extends Fragment {
    private ImageView firstDotView;
    private ImageView secondDotView;
    private ImageView thirdDotView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_footer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstDotView = (ImageView) view.findViewById(R.id.first_dot_view);
        secondDotView = (ImageView) view.findViewById(R.id.second_dot_view);
        thirdDotView = (ImageView) view.findViewById(R.id.third_dot_view);
    }

    public void changeDotPosition(int position) {
        switch (position) {
            case 0:
                firstDotView.setBackgroundResource(R.drawable.dot_selected);
                secondDotView.setBackgroundResource(R.drawable.dot);
                thirdDotView.setBackgroundResource(R.drawable.dot);
                break;
            case 1:
                firstDotView.setBackgroundResource(R.drawable.dot);
                secondDotView.setBackgroundResource(R.drawable.dot_selected);
                thirdDotView.setBackgroundResource(R.drawable.dot);
                break;
            case 2:
                firstDotView.setBackgroundResource(R.drawable.dot);
                secondDotView.setBackgroundResource(R.drawable.dot);
                thirdDotView.setBackgroundResource(R.drawable.dot_selected);
                break;
        }
    }
}
