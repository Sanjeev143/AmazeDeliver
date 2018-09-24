package com.sanjeev.er.amazedeliver;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sanjeev.er.amazedeliver.model.Constant;

/* Author Sanjeev Sangral*/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private double lat,lng;
    private String imageUrl,address,description;
    private TextView descriptionTxt;
    private ImageView profileImageView;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Getting all the bind data from bundle
        bundle = getIntent().getExtras();
        lat = bundle.getDouble(Constant.LATITUDE);
        lng = bundle.getDouble(Constant.LONGITUDE);
        imageUrl = bundle.getString(Constant.IMAGEURL);
        address = bundle.getString(Constant.ADDRESS);
        description = bundle.getString(Constant.DESCRIPTION);
        Log.d("DATA",""+lat+lng);

        init();

    }

    private void init() {
        profileImageView = (ImageView)findViewById(R.id.image);
        descriptionTxt = (TextView)findViewById(R.id.description);

        Glide.with(this).load(imageUrl).into(profileImageView);
        descriptionTxt.setText(description + " at "+address);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = new LatLng(lat, lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 18));
        mMap.addMarker(new MarkerOptions().position(location).title(description+ " at "+address)
                .snippet("Lalamove sample app")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("mGoogleMap1", "Activity_Calling");
                return false;
            }
        });
    }
}
