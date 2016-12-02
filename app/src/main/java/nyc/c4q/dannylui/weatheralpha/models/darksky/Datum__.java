package nyc.c4q.dannylui.weatheralpha.models.darksky;

/**
 * Created by dannylui on 10/25/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Datum__ {

    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("sunriseTime")
    @Expose
    public Integer sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    public Integer sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    public Double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    public Double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    public Double precipIntensityMax;
    @SerializedName("precipProbability")
    @Expose
    public Double precipProbability;
    @SerializedName("temperatureMin")
    @Expose
    public Double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    public Integer temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    public Double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    public Integer temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    public Double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    public Integer apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    public Double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    public Integer apparentTemperatureMaxTime;
    @SerializedName("dewPoint")
    @Expose
    public Double dewPoint;
    @SerializedName("humidity")
    @Expose
    public Double humidity;
    @SerializedName("windSpeed")
    @Expose
    public Double windSpeed;
    @SerializedName("windBearing")
    @Expose
    public Integer windBearing;
    @SerializedName("visibility")
    @Expose
    public Double visibility;
    @SerializedName("cloudCover")
    @Expose
    public Double cloudCover;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("ozone")
    @Expose
    public Double ozone;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    public Integer precipIntensityMaxTime;
    @SerializedName("precipType")
    @Expose
    public String precipType;

    public Integer getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getSunriseTime() {
        return sunriseTime;
    }

    public Integer getSunsetTime() {
        return sunsetTime;
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public Integer getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public Integer getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public Integer getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public Integer getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindBearing() {
        return windBearing;
    }

    public Double getVisibility() {
        return visibility;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getOzone() {
        return ozone;
    }

    public Integer getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public String getPrecipType() {
        return precipType;
    }
}
