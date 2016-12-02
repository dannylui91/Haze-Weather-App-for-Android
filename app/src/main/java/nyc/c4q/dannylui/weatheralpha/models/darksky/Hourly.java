package nyc.c4q.dannylui.weatheralpha.models.darksky;

/**
 * Created by dannylui on 10/25/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Hourly {

    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("data")
    @Expose
    public List<Datum_> data = new ArrayList<Datum_>();

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public List<Datum_> getData() {
        return data;
    }
}
