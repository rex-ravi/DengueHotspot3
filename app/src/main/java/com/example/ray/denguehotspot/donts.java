package com.example.ray.denguehotspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//In this class get data for "donts" from json object
public class donts extends AppCompatActivity {

    TextView txt1;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donts);
        txt1=(TextView) findViewById(R.id.textView);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            JSONObject jb= jsonArray.getJSONObject(0);
            String donts = jb.getString("donts");
            txt1.setText("donts: " + donts);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
