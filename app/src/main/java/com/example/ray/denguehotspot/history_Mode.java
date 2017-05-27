package com.example.ray.denguehotspot;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class history_Mode extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String json_string;
    String fund,fund2;
    String datee="k";
    String latitudee="a",longg="b",addd="d";
    double a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengue_case);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        json_string = getIntent().getExtras().getString("json_data");
        fund= (String) getIntent().getExtras().get("ok");
        fund2=fund.toLowerCase();
        fund2 = fund2.replaceAll("\\s+", "");
        try {

            JSONObject parentobject = new JSONObject(json_string);
            JSONArray parentarray = parentobject.getJSONArray("server_response");

            for (int i = 0; i < parentarray.length(); i++) {
                JSONObject finalobject = parentarray.getJSONObject(i);
                latitudee = finalobject.getString("latitude");
                longg = finalobject.getString("longitude");
                addd = finalobject.getString("address");
                datee=finalobject.getString("date");



                a = Double.parseDouble(latitudee);//Crash problem solved
                b = Double.parseDouble(longg);


                if (addd.length() <= 1) {
                    addd = addd.toLowerCase();}
                else {
                    addd =addd.toLowerCase();
                    addd = addd.replaceAll("\\s+", "");

                    if((addd.contains(fund2)))
                    {

                        LatLng sydney = new LatLng(a, b);
                        mMap.addMarker(new MarkerOptions().position(sydney).title(datee));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    }}}} catch (JSONException e1) {
            e1.printStackTrace();
        }


    }
}

