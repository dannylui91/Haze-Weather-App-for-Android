package nyc.c4q.dannylui.weatheralpha.models;

/**
 * Created by dannylui on 10/25/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Flags {

    @SerializedName("sources")
    @Expose
    public List<String> sources = new ArrayList<String>();
    @SerializedName("darksky-stations")
    @Expose
    public List<String> darkskyStations = new ArrayList<String>();
    @SerializedName("lamp-stations")
    @Expose
    public List<String> lampStations = new ArrayList<String>();
    @SerializedName("isd-stations")
    @Expose
    public List<String> isdStations = new ArrayList<String>();
    @SerializedName("madis-stations")
    @Expose
    public List<String> madisStations = new ArrayList<String>();
    @SerializedName("units")
    @Expose
    public String units;

    public List<String> getSources() {
        return sources;
    }

    public List<String> getDarkskyStations() {
        return darkskyStations;
    }

    public List<String> getLampStations() {
        return lampStations;
    }

    public List<String> getIsdStations() {
        return isdStations;
    }

    public List<String> getMadisStations() {
        return madisStations;
    }

    public String getUnits() {
        return units;
    }
}