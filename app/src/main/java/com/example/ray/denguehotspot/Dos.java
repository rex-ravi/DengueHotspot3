package com.example.ray.denguehotspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//In this class get data for "dos" from json object
public class Dos extends AppCompatActivity {

    TextView txt1;
    String json_string;

    JSONObject jsonObject;
    JSONArray jsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisory);
        txt1=(TextView) findViewById(R.id.textView);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            //"server response is the array name"
            jsonArray=jsonObject.getJSONArray("server_response");
            JSONObject jb= jsonArray.getJSONObject(0);
            //fetch data from json object(dos)
            String dos = jb.getString("dos");
            txt1.setText("dos: " + dos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
