package com.example.cycondlife;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.*;
import android.content.Intent;
import android.content.Context;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,
        View.OnClickListener {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private GoogleApiClient googleApiClient;
    private Location lastLocation ;
    private TextView myText = null;
    static Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(g==null) {
            g = new Game(mMap);
            g.generate_mMap();
        }
        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //googleMapOptions.mapType(googleMap.MAP_TYPE_HYBRID).compassEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng india = new LatLng(42.03, -92.03);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in Ames"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);

        //g.display_monsters();
    }

    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationServices.getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            lastLocation = location;
                         //   myText.setText("Your location is Lat:" + lastLocation.getLatitude()+"Long"+lastLocation.getLongitude());
                        } else {
                           // myText.setText("Error no location");
                        }
                        if (location != null) {
                            //Getting longitude and latitude
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();

                            //moving the map to location
                            moveMap();
                        }

                    }
                });
    }

                    private void moveMap() {
                        /*
                         * Creating the latlng object to store lat, long coordinates
                         * adding marker to map
                         * move the camera with animation
                         */
                        LatLng latLng = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .draggable(true)
                                .title("You are here!!!!"));
                        for(int i=0;i<g.num_monsters;i++)
                        {
                            if(g.monster_map.get(i).getResolve()<=0)
                            {
                                continue;
                            }
                            mMap.addMarker(new MarkerOptions().position(new LatLng(g.monster_map.get(i).get_latitude(),g.monster_map.get(i).get_longitude())).draggable(false).title("Monster: "+ i));
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                        mMap.getUiSettings().setZoomControlsEnabled(true);


                    }

    @Override
    public void onClick(View view) {
        Log.v(TAG,"view click event");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // mMap.clear();
        final Context context = this;
        Intent intent = new Intent(context, Combat.class);
        boolean found=false;
        Log.i("Cycond Life","LAT lang ="+latLng.latitude+" "+latLng.longitude);
        Character opponent;
        for(int i=0;i<g.num_monsters;i++)
        {
            if(Math.abs(Math.abs(g.monster_map.get(i).get_longitude())-Math.abs(latLng.longitude))<=.001&&Math.abs(Math.abs(g.monster_map.get(i).get_latitude())-Math.abs(latLng.latitude))<=.001)
            {
                opponent=g.monster_map.get(i);
                Combat.set_combatants(g.player,opponent,g);
                found=true;
                break;
            }

        }
        if(found) {
            startActivity(intent);
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerDragStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerDrag", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerClick", Toast.LENGTH_SHORT).show();
        return true;
    }


}
