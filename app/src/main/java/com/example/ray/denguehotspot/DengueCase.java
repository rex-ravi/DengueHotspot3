package com.example.ray.denguehotspot;

        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;
        import android.widget.Toast;
//god promise
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;


        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class DengueCase extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String json_string;
    String fund;
    String datee="k";
    String latitudee="a",longg="b",addd="d";
    double a=0.0,b=0.0;
    long u=12345,g=12345,diff=12345;
    String kanaa="a";
    String test="b",testt="a";
    String testtt="g",testttt;
    String difff="a";

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


        try {

            SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");



            JSONObject parentobject = new JSONObject(json_string);
            JSONArray parentarray = parentobject.getJSONArray("server_response");

            //  Date date=formatter.parse(datee);
            //f=date.getTime();
            Date currentDate=new Date();
            g=currentDate.getTime();//Current date
            // testt=Long.toString(g);//Current date into string for testing pp


            for (int i = 0; i < parentarray.length(); i++) {
                JSONObject finalobject = parentarray.getJSONObject(i);
                latitudee = finalobject.getString("latitude");
                longg = finalobject.getString("longitude");
                addd = finalobject.getString("address");
                datee=finalobject.getString("date");

                //}

                a = Double.parseDouble(latitudee);//Crash problem solved
                b = Double.parseDouble(longg);


                Date convertedDate = null;
                try {
                    SimpleDateFormat readFormat = new SimpleDateFormat( "yyyy-MM-dd", java.util.Locale.getDefault());
                   // SimpleDateFormat writeFormat = new SimpleDateFormat( "dd MMM, yyyy", java.util.Locale.getDefault());

                    convertedDate = readFormat.parse( datee );
                    u=convertedDate.getTime();
                    test=Long.toString(u);
                    // String formattedDate = writeFormat.format( convertedDate );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//difference between current and database date in long
                  diff=g-u;

                if((addd.contains(fund))&&(g>diff)&&(diff<=864000000))
                {

                        LatLng sydney = new LatLng(a, b);
                        mMap.addMarker(new MarkerOptions().position(sydney).title(addd));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                }}} catch (JSONException e1) {
            e1.printStackTrace();
        }


    }
}

