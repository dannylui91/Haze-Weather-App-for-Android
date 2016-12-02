package nyc.c4q.dannylui.weatheralpha.network;

import nyc.c4q.dannylui.weatheralpha.models.darksky.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.ipapi.Location;

/**
 * Created by dannylui on 11/30/16.
 */

public interface WeatherCallback {
    void getForecastData(Forecast data);
    void getLocationData(Location data);
}
