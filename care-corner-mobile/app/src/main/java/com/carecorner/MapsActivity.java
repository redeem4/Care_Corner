package com.carecorner;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // check if network provider is enable
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 25, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    // get latitude
                    double latitude = location.getLatitude();
                    // get longitude
                    double longitude = location.getLongitude();
                    // instantiate the class LatLong
                    LatLng latLng = new LatLng(latitude, longitude);
                    // instantiate GeoCoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList;
                        addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ""
                                + addressList.get(0).getPostalCode() + "\n"
                                + addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title("str"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        } else if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 25, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    // get latitude
                    double latitude = location.getLatitude();
                    // get longitude
                    double longitude = location.getLongitude();
                    // instantiate the class LatLong
                    LatLng latLng = new LatLng(latitude, longitude);
                    // instantiate GeoCoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList;
                        addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String shelter  = addressList.get(0).getLocality() + ""
                                + addressList.get(0).getPostalCode() + "\n"
                                + addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title("shelter"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


}

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);

    }
}