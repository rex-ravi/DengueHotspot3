package com.example.ray.denguehotspot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class viewAdvisory extends AppCompatActivity {

    TextView textView;
    Button btn1,btn2;
    String json_string,JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_advisory);

        textView=(TextView) findViewById(R.id.textView);
        btn1=(Button)findViewById(R.id.button2);
        btn2=(Button)findViewById(R.id.button3);
    }

    //This button is for dos

    public void dos(View view){

        BackgroundTask backgroundTask=new BackgroundTask();
          backgroundTask.execute();

        if (JSON_STRING == null) {

            Toast.makeText(getApplicationContext(), "Click again", Toast.LENGTH_SHORT).show();
        } else {


Intent intent=new Intent(this,Dos.class);
            intent.putExtra("json_data", JSON_STRING);
            startActivity(intent);


        }

    }

//This button iis for Dont's

    public void donts(View view){

        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute();

        if (JSON_STRING == null) {

            Toast.makeText(getApplicationContext(), "Click again", Toast.LENGTH_SHORT).show();
        } else {

            Intent intent=new Intent(this,donts.class);
            intent.putExtra("json_data", JSON_STRING);
            startActivity(intent);

        }

    }

//This BackgroundTask class has fetched json data from database
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.myfastchoice.com/dosdonts.php";


        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {

                    stringBuilder.append(json_string + "\n");


                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            JSON_STRING = result;

        }

    }

}

