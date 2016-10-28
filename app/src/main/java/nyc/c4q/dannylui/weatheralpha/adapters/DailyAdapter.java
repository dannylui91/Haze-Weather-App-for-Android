package nyc.c4q.dannylui.weatheralpha.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import nyc.c4q.dannylui.weatheralpha.models.Datum__;
import nyc.c4q.dannylui.weatheralpha.viewholders.DailyViewHolder;

/**
 * Created by dannylui on 10/26/16.
 */

public class DailyAdapter extends RecyclerView.Adapter{
    private List<Datum__> dataList = Collections.EMPTY_LIST;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyViewHolder dailyHolder = (DailyViewHolder) holder;
        dailyHolder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<Datum__> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
}
