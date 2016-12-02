package nyc.c4q.dannylui.weatheralpha.models;

import java.util.List;

/**
 * Created by Danny on 12/1/2016.
 */

public class SunModel {
    private int iconId;
    private String uvIndex;
    private String sunriseTime;
    private String sunsetTime;
    private String cloudCoverage;
    private String sunTime;
    private List<String> sunshineList;

    public SunModel(int iconId, String uvIndex, String sunriseTime, String sunsetTime, String cloudCoverage, String sunTime, List<String> sunshineList) {
        this.iconId = iconId;
        this.uvIndex = uvIndex;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.cloudCoverage = cloudCoverage;
        this.sunTime = sunTime;
        this.sunshineList = sunshineList;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getCloudCoverage() {
        return cloudCoverage;
    }

    public void setCloudCoverage(String cloudCoverage) {
        this.cloudCoverage = cloudCoverage;
    }

    public String getSunTime() {
        return sunTime;
    }

    public List<String> getSunshineList() {
        return sunshineList;
    }
}
