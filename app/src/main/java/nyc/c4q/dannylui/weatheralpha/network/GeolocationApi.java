package nyc.c4q.dannylui.weatheralpha.network;

import nyc.c4q.dannylui.weatheralpha.models.Location;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dannylui on 10/25/16.
 */

public interface GeolocationApi {
    @GET("json")
    Call<Location> getLocation();
}
