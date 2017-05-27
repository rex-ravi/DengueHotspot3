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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//fetch data from jsonobject for listview(Backgroundtask2 class)
//send data to database for deleting data(Backgroundtask class)

public class manage_Admin extends AppCompatActivity {

    EditText edt1;
    Button btn1;
    String Delete;


    String JSON_STRING,json_string;
    //EditText edt2;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__admin);
        edt1=(EditText) findViewById(R.id.editText3);
        btn1=(Button) findViewById(R.id.button3);


        btn2=(Button)findViewById(R.id.button4);
    }

    public void delete(View view){

BackgroundTask backgroundTask = new BackgroundTask();
        Delete=edt1.getText().toString();
        backgroundTask.execute(Delete);
        finish();
    }

    //porerta

    public void parse(View view){

        BackgroundTask2 backgroundTask=new BackgroundTask2();
        backgroundTask.execute();

        if (JSON_STRING == null) {

            Toast.makeText(getApplicationContext(), "Click again", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent=new Intent(this,new_Advisory.class);
            intent.putExtra("json_data", JSON_STRING);
            startActivity(intent);


        }

    }
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String add_info_url;

        @Override
        protected void onPreExecute() {
            add_info_url = "http://myfastchoice.com/dell.php";  //This is the url where the data(latitude,lonngiitude and other data will be stored)
        }

        @Override
        protected String doInBackground(String... args) {
            String id;
            id=args[0];

            try {

                URL url = new URL(add_info_url);
                HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.setDoOutput(true);
                OutputStream outputstream = httpurlconnection.getOutputStream();
                BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));

                String data_string = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(id), "UTF-8");

                bufferedwriter.write(String.valueOf(data_string));
                bufferedwriter.flush();
                bufferedwriter.close();
                outputstream.close();
                InputStream inputstream = httpurlconnection.getInputStream();
                inputstream.close();
                httpurlconnection.disconnect();

                return "One row of data deleted";


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

    class BackgroundTask2 extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.myfastchoice.com/adminlist.php";
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





