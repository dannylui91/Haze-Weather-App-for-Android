package nyc.c4q.dannylui.weatheralpha.network;

import nyc.c4q.dannylui.weatheralpha.models.RainModel;
import nyc.c4q.dannylui.weatheralpha.models.SunModel;
import nyc.c4q.dannylui.weatheralpha.models.TempModel;
import nyc.c4q.dannylui.weatheralpha.models.ipapi.Location;

/**
 * Created by dannylui on 11/30/16.
 */

public interface WeatherCallback {
    void getForecastData(SunModel sunModel, TempModel tempModel, RainModel rainModel);
    void getLocationData(Location data);
}
