package nyc.c4q.dannylui.weatheralpha.models;

import java.util.List;

/**
 * Created by Danny on 12/1/2016.
 */

public class RainModel {
    private int iconId;
    private String rainDropped;
    private String pressure;
    private String humidity;
    private String rainChance;
    private List<String> rainChanceList;

    public RainModel(int iconId, String rainDropped, String pressure, String humidity, String rainChance, List<String> rainChanceList) {
        this.iconId = iconId;
        this.rainDropped = rainDropped;
        this.pressure = pressure;
        this.humidity = humidity;
        this.rainChance = rainChance;
        this.rainChanceList = rainChanceList;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getRainDropped() {
        return rainDropped;
    }

    public void setRainDropped(String rainDropped) {
        this.rainDropped = rainDropped;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRainChance() {
        return rainChance;
    }

    public void setRainChance(String rainChance) {
        this.rainChance = rainChance;
    }

    public List<String> getRainChanceList() {
        return rainChanceList;
    }

    public void setRainChanceList(List<String> rainChanceList) {
        this.rainChanceList = rainChanceList;
    }
}
