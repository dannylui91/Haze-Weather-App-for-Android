package nyc.c4q.dannylui.weatheralpha.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Datum_;
import nyc.c4q.dannylui.weatheralpha.utility.ConvertUnixTs;

/**
 * Created by dannylui on 10/26/16.
 */
public class HourlyViewHolder extends RecyclerView.ViewHolder {
    private final View mView;
    private TextView hourView;
    private TextView descView;
    private TextView tempView;
    public HourlyViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView = itemView;
        hourView = (TextView) mView.findViewById(R.id.hour_view);
        descView = (TextView) mView.findViewById(R.id.hour_desc_view);
        tempView = (TextView) mView.findViewById(R.id.hour_temp_view);
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.hourly_view_holder, parent, false);
    }

    public void bind(Datum_ data) {
        hourView.setText(ConvertUnixTs.toHourMinute(data.getTime()));
        descView.setText(data.getSummary());
        tempView.setText(String.valueOf(Math.round(data.getTemperature())) + "°");

        ViewGroup.LayoutParams params = descView.getLayoutParams();
        params.width = (int)(Math.round(data.getTemperature())) * 7;
        descView.requestLayout();
    }
}
