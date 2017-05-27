package com.example.ray.denguehotspot;

import android.*;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static java.sql.Types.NULL;

public class reporter extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {

    EditText name,age,email;
    Button send;
    Button settingButton;


    GoogleApiClient googleApiClient; //This variable is used for creating googleapi client object
    private FusedLocationProviderApi locationProvider = LocationServices.FusedLocationApi;

    private LocationRequest locationRequest;
    private double myLatitude;  //used for receiving latitude in onlocationchanged() method for using Location listener Interface
    private double myLongitude; //used for receiving longitude in onlocationchanged() method for using Location listener Interface
    private static final int MY_PERMISSON_REQUEST_FINE_LOCATION = 101;
    private static final int MY_PERMISSON_REQUEST_COARSE_LOCATION = 102;
    private boolean permissionIsGranted = false;


    String reversecoder;
    String JSON_STRING;
    String a, b;
    String c;
    String Name,Age,Email;
    List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporter);

        //Here all entity defined by ID
        name=(EditText)findViewById(R.id.editText);
        age=(EditText)findViewById(R.id.editText2);
        email=(EditText)findViewById(R.id.editText4);
        send=(Button)findViewById(R.id.button);
        settingButton=(Button)findViewById(R.id.button1);



        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = new LocationRequest();
        locationRequest.setInterval(60 * 1000);
        locationRequest.setFastestInterval(15 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{}, MY_PERMISSON_REQUEST_FINE_LOCATION);
                } else {

                    permissionIsGranted = true;
                }

            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdates();
    }

    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSON_REQUEST_FINE_LOCATION);
            } else {
                permissionIsGranted = true;
            }
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }


    //Interface is addConnection callback
    // to do something location services are suspended

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();
        Geocoder gc= new Geocoder(this);
        try {
            addresses = gc.getFromLocation(myLatitude,myLongitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //part of life cycle of main activity.after starting the app thi9s will call

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    //part of life cycle of main activity
    @Override
    protected void onResume() {
        super.onResume();
        if (permissionIsGranted) {
            if (googleApiClient.isConnected()) {

                requestLocationUpdates();
            }
        }
    }
    //part of life cycle of main activity
    @Override
    protected void onPause() {
        super.onPause();
        if (permissionIsGranted) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    //part of life cycle of main activity.
    @Override
    protected void onStop() {
        super.onStop();
        if (permissionIsGranted)
            googleApiClient.disconnect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSON_REQUEST_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //permissiongranted
                    permissionIsGranted = true;
                } else {
                    permissionIsGranted = false;
                    // latitudeText.setText("Latitude permission is not granted");
                    ///longitudeText.setText("Longitude permission is not granted");
                }
                break;
            case MY_PERMISSON_REQUEST_COARSE_LOCATION:
                break;

        }
    }

    public void save(View view) {
        //
        Object c1[];
               // []=new Object[9];

        //
        Name=name.getText().toString();
        Age=age.getText().toString();
        Email=email.getText().toString();
        //newwwwwwwwwwwwwwwwwwwwwwwwwwwww

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(Email);
if(m.matches()==true){

        a = Double.toString(myLatitude);
        b = Double.toString(myLongitude);

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

        String state = addresses.get(0).getAdminArea();

        String knownName = addresses.get(0).getAddressLine(1);

        String knownName1 = addresses.get(0).getAddressLine(2);

        c = address+" "+knownName+" "+knownName1;
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(Name,Age,Email,a, b,c);
        finish();}
        else{
    Toast.makeText(getApplicationContext(), "Email is incorrect", Toast.LENGTH_LONG).show();

}

    }

    class BackgroundTask extends AsyncTask<String, Void, String> {

        String add_info_url;

        @Override
        protected void onPreExecute() {
            add_info_url = "http://myfastchoice.com/insertdataintodatabase.php";  //This is the url where the data(latitude,lonngiitude and other data will be stored)
        }

        @Override
        protected String doInBackground(String... args) {
            String latt, longg,namee,agee,emaill;
            double lattt,longgg;
            namee=args[0];
            agee=args[1];
            emaill=args[2];
            latt = args[3];
            lattt=Double.parseDouble(latt);
            longg = args[4];
            longgg=Double.parseDouble(longg);
            reversecoder= args[5];

            try {
                // if(name>0 && email>0){
                // if (lattt > 1.0 && longgg > 1.0) {
                URL url = new URL(add_info_url);
                HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.setDoOutput(true);
                OutputStream outputstream = httpurlconnection.getOutputStream();
                BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));

                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(namee), "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(agee), "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(emaill), "UTF-8") + "&" +
                        URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(lattt), "UTF-8") + "&" +
                        URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(longgg), "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(reversecoder, "UTF-8");
                bufferedwriter.write(String.valueOf(data_string));
                bufferedwriter.flush();
                bufferedwriter.close();
                outputstream.close();
                InputStream inputstream = httpurlconnection.getInputStream();
                inputstream.close();
                httpurlconnection.disconnect();

                return "One row of data insert";
                // } //else {
                // return "Please turn on your Application Location";
                // }

            }

            catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate (Void...values){
            super.onProgressUpdate(values);
        }

        //This method is used for receiving th result

        @Override
        protected void onPostExecute (String result){
            // String JSON_STRING=result;

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


        }
    }
}
