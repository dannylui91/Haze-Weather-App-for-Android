package nyc.c4q.dannylui.weatheralpha.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nyc.c4q.dannylui.weatheralpha.models.Datum_;
import nyc.c4q.dannylui.weatheralpha.viewholders.HourlyViewHolder;

/**
 * Created by dannylui on 10/26/16.
 */
public class HourlyAdapter extends RecyclerView.Adapter {
    private List<Datum_> data = Collections.EMPTY_LIST;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HourlyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HourlyViewHolder hourHolder = (HourlyViewHolder) holder;
        hourHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Datum_> data) {
        List<Datum_> dataNext24Hours = new ArrayList<Datum_>();
        System.out.println(dataNext24Hours.size());
        for (int i = 1; i <= 24; i++) {
            dataNext24Hours.add(data.get(i));
        }
        System.out.println(dataNext24Hours.size());
        this.data = dataNext24Hours;

        notifyDataSetChanged();
    }

}
