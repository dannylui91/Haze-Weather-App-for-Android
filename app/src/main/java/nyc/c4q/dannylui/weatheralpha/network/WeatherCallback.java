package nyc.c4q.dannylui.weatheralpha.network;

import nyc.c4q.dannylui.weatheralpha.models.Forecast;
import nyc.c4q.dannylui.weatheralpha.models.Location;

/**
 * Created by dannylui on 11/30/16.
 */

public interface WeatherCallback {
    void getForecastData(Forecast data);
    void getLocationData(Location data);
}
