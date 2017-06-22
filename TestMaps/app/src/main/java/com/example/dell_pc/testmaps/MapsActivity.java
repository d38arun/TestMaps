package com.example.dell_pc.testmaps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener {

    private GoogleMap mMap;
    double latitude, longitude;
    Geocoder geocoder;
    List<Address> addresses;
    String address, city, country, knownName, postalCode, state;
    EditText addressText;
    String landmark;
    String lat, longe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        addressText = (EditText) findViewById(R.id.addressText);
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
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        if (mMap != null) {


            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                @Override
                public void onMyLocationChange(Location arg0) {
                    // TODO Auto-generated method stub
                    latitude=arg0.getLatitude();
                    longitude=arg0.getLongitude();
                    lat = String.valueOf(latitude);
                    longe = String.valueOf(longitude);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
                    try {
                        handleNewLocation(arg0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
        // Add a marker in Sydney and move the camera
      //  LatLng sydney = new LatLng(-34, 151);
     //   mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void handleNewLocation(Location location) throws IOException {
        geocoder = new Geocoder(this, Locale.getDefault());
        addresses = geocoder.getFromLocation(latitude, longitude, 1);
        address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        city = addresses.get(0).getLocality();
        state = addresses.get(0).getAdminArea();
        country = addresses.get(0).getCountryName();
        postalCode = addresses.get(0).getPostalCode();
        knownName = addresses.get(0).getFeatureName();

        //addressText.setText(knownName+" "+address+" , "+city+" , "+state+" , "+country+" - "+postalCode);
    }


    @Override
    public void onMyLocationChange(Location location) {

    }
    public void onPinClick(View view)
    {
        landmark=addressText.getText().toString();
        Intent intent = new Intent(this, DetailsActivity.class);
        // intent.putExtra("Address", this.address);
        // intent.putExtra("City", this.city);
        //  intent.putExtra("State", this.state);
        //  intent.putExtra("Country", this.country);
        //  intent.putExtra("PostalCode", this.postalCode);
        intent.putExtra("Latitude",this.lat);
        intent.putExtra("Longitude",this.longe);
        //  if (this.knownName != null) {
        //       intent.putExtra("KnownName", this.knownName);
        //  }
        //   intent.putExtra("Landmark", this.landmark);
        if(landmark!="")
            startActivity(intent);
        else
            Toast.makeText(getApplicationContext(), "Enter Landmark to continue", Toast.LENGTH_LONG).show();
    }


}

