package com.example.ray.denguehotspot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class recentHistory extends AppCompatActivity {

    EditText editText;
    Button recent,recent2;
    String json_string;
    String JSON_STRING;
    String r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_history);
        recent = (Button) findViewById(R.id.dengue);
        recent2=(Button)findViewById(R.id.button4);
        editText = (EditText) findViewById(R.id.editText6);
    }


    public void deng(View view) {
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute();
        if (JSON_STRING == null) {

            Toast.makeText(getApplicationContext(), "Click again", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(getApplicationContext(),"Data agaya",Toast.LENGTH_SHORT).show();


            r = String.valueOf(editText.getText());//Mine
            Intent intent = new Intent(this,DengueCase.class );
            intent.putExtra("json_data", JSON_STRING);
            intent.putExtra("ok", r);
            startActivity(intent);


        }


    }
    public void deng2(View view) {
        BackgroundTask backgroundTask2 = new BackgroundTask();
        backgroundTask2.execute();
        if (JSON_STRING == null) {

            Toast.makeText(getApplicationContext(), "Click again", Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(getApplicationContext(),"Data agaya",Toast.LENGTH_SHORT).show();


            r = String.valueOf(editText.getText());//Mine
            Intent intent = new Intent(this,history_Mode.class );
            intent.putExtra("json_data", JSON_STRING);
            intent.putExtra("ok", r);
            startActivity(intent);


        }


    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.myfastchoice.com/next.php";
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

    class BackgroundTask2 extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.myfastchoice.com/next2.php";
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
