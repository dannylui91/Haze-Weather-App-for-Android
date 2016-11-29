package nyc.c4q.dannylui.weatheralpha;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by dannylui on 11/2/16.
 */
public class MyLocationListener implements LocationListener {
    private static final String TAG = "MyLocationListener";
    private String latitude;
    private String longitude;

    @Override
    public void onLocationChanged(Location loc) {
        latitude = "" + loc.getLongitude();
        longitude = "" + loc.getLatitude();
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
