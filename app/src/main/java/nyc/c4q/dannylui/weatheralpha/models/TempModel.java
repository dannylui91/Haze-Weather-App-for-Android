package nyc.c4q.dannylui.weatheralpha.models;

import java.util.List;

/**
 * Created by Danny on 12/1/2016.
 */

public class TempModel {
    private String highTemp;
    private String lowTemp;
    private String feelTemp;
    private String windSpeed;
    private String windDirection;
    private String currentTemp;
    private List<String> highTempList;

    public TempModel(String highTemp, String lowTemp, String feelTemp, String windSpeed, String windDirection, String currentTemp, List<String> highTempList) {
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.feelTemp = feelTemp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.currentTemp = currentTemp;
        this.highTempList = highTempList;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getFeelTemp() {
        return feelTemp;
    }

    public void setFeelTemp(String feelTemp) {
        this.feelTemp = feelTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public List<String> getHighTempList() {
        return highTempList;
    }

    public void setHighTempList(List<String> highTempList) {
        this.highTempList = highTempList;
    }
}
