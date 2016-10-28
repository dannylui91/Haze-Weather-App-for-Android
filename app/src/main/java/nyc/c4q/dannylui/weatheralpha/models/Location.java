package nyc.c4q.dannylui.weatheralpha.models;

/**
 * Created by dannylui on 10/25/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Location {

    @SerializedName("as")
    @Expose
    public String as;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("countryCode")
    @Expose
    public String countryCode;
    @SerializedName("isp")
    @Expose
    public String isp;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lon")
    @Expose
    public Double lon;
    @SerializedName("org")
    @Expose
    public String org;
    @SerializedName("query")
    @Expose
    public String query;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("regionName")
    @Expose
    public String regionName;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("zip")
    @Expose
    public String zip;

    public String getAs() {
        return as;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getIsp() {
        return isp;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getOrg() {
        return org;
    }

    public String getQuery() {
        return query;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getStatus() {
        return status;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getZip() {
        return zip;
    }
}