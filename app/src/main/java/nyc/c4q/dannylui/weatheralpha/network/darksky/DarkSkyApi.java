package nyc.c4q.dannylui.weatheralpha.network.darksky;

import nyc.c4q.dannylui.weatheralpha.models.darksky.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dannylui on 10/25/16.
 */

public interface DarkSkyApi {
    @GET("forecast/{key}/{latitudeLongitude}")
    Call<Forecast> getForecast(
            @Path("key") String apiKey,
            @Path("latitudeLongitude") String latLong);
}
