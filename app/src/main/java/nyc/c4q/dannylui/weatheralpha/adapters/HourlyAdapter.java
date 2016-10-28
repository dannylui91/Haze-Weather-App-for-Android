package nyc.c4q.dannylui.weatheralpha.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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
        this.data = data;
        notifyDataSetChanged();
    }
}
