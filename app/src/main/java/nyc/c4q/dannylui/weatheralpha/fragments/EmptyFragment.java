package nyc.c4q.dannylui.weatheralpha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import nyc.c4q.dannylui.weatheralpha.R;

/**
 * Created by dannylui on 11/29/16.
 */

public class EmptyFragment extends Fragment {
    private View rootView;
    private RelativeLayout emptyFragBg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_empty, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyFragBg = (RelativeLayout) view.findViewById(R.id.empty_frag_bg);
    }

    public void changeBackground(int position) {
        switch (position) {
            case 0:
                emptyFragBg.setBackgroundResource(R.color.brown);
                break;
            case 1:
                ImageView backgroundGifView = (ImageView) rootView.findViewById(R.id.empty_frag_gif);
                Glide.with(this).load(R.drawable.animation_bg_candy).into(backgroundGifView);
                break;
            case 2:
                emptyFragBg.setBackgroundResource(R.color.blue);
                break;
        }
    }
}
