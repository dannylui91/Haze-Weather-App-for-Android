package nyc.c4q.dannylui.weatheralpha.fragments;

import nyc.c4q.dannylui.weatheralpha.models.Forecast;

/**
 * Created by dannylui on 10/26/16.
 */

public interface UpdateableFragment {
    public void update(Forecast newData);
}
