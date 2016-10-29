package nyc.c4q.dannylui.weatheralpha.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.dannylui.weatheralpha.R;
import nyc.c4q.dannylui.weatheralpha.models.Datum__;
import nyc.c4q.dannylui.weatheralpha.utility.ConvertUnixTs;

/**
 * Created by dannylui on 10/26/16.
 */
public class DailyViewHolder extends RecyclerView.ViewHolder {
    private final View mView;
    private TextView dayView;
    private TextView dayDescView;
    private TextView hiView;
    private TextView loView;

    public DailyViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        mView = itemView;
        dayView = (TextView) mView.findViewById(R.id.day_view);
        dayDescView = (TextView) mView.findViewById(R.id.day_desc_view);
        hiView = (TextView) mView.findViewById(R.id.hi_view);
        loView = (TextView) mView.findViewById(R.id.lo_view);
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.daily_view_holder, parent, false);
    }

    public void bind(Datum__ data) {
        dayView.setText(ConvertUnixTs.toDayOfWeek(data.getTime()));
        dayDescView.setText(data.getSummary());
        hiView.setText(String.valueOf(Math.round(data.getTemperatureMax())) + "°");
        loView.setText(String.valueOf(Math.round(data.getTemperatureMin())) + "°");
    }
}
