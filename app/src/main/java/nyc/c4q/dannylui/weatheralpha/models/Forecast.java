package nyc.c4q.dannylui.weatheralpha.models;

/**
 * Created by dannylui on 10/25/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Forecast {

    @SerializedName("latitude")
    @Expose
    public Double latitude;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("currently")
    @Expose
    public Currently currently;
    @SerializedName("minutely")
    @Expose
    public Minutely minutely;
    @SerializedName("hourly")
    @Expose
    public Hourly hourly;
    @SerializedName("daily")
    @Expose
    public Daily daily;
    @SerializedName("flags")
    @Expose
    public Flags flags;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public Integer getOffset() {
        return offset;
    }

    public Currently getCurrently() {
        return currently;
    }

    public Minutely getMinutely() {
        return minutely;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public Daily getDaily() {
        return daily;
    }

    public Flags getFlags() {
        return flags;
    }
}
