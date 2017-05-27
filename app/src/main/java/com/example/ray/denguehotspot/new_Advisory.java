package com.example.ray.denguehotspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//Extract data for list view
public class new_Advisory extends AppCompatActivity {


    String json_string;

    JSONObject jsonObject;
    JSONArray jsonArray;

    advisory22adapter advisory22adaptere;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__advisory);
        listView=(ListView) findViewById(R.id.listview);


        advisory22adaptere=new advisory22adapter(this,R.layout.row2);
        listView.setAdapter(advisory22adaptere);

        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            String latitude,longitude,id,date;
            while(count<=jsonArray.length()){

                JSONObject jo=jsonArray.getJSONObject(count);

                latitude=jo.getString("id");
                longitude=jo.getString("name");
               // id=jo.getString("date");
                id=" ";
                //
                date=jo.getString("address")+"\n REPORTED DATE:"+jo.getString("date");
                //
                advisory22 add=new advisory22(latitude,longitude,id,date);
                advisory22adaptere.add(add);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
