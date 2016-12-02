package nyc.c4q.dannylui.weatheralpha.models.darksky;

/**
 * Created by dannylui on 10/25/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Datum {

    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("precipIntensity")
    @Expose
    public Double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    public Double precipProbability;

    public Integer getTime() {
        return time;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }
}