package com.example.ray.denguehotspot;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ray on 5/16/2017.
 */

public class advisory22adapter extends ArrayAdapter {
    List list=new ArrayList();


    public advisory22adapter( Context context, int resource) {
        super(context, resource);
    }


    public void add( advisory22 object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View row;
        row=convertView;
          NewFolder newFolder;
        if(row==null){

            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row2,parent,false);
            newFolder=new NewFolder();
            newFolder.tx_latitude=(TextView) row.findViewById(R.id.tx_latitude);
            newFolder.tx_longitude=(TextView) row.findViewById(R.id.tx_longitude);
            newFolder.tx_id=(TextView) row.findViewById(R.id.tx_id);
            newFolder.tx_date=(TextView)row.findViewById(R.id.tx_date);
            row.setTag(newFolder);
        }

        else{

            newFolder=(NewFolder) row.getTag();
        }
        advisory22 advisorys=(advisory22) this.getItem(position);
        newFolder.tx_latitude.setText(advisorys.getLatitude());
        newFolder.tx_longitude.setText(advisorys.getLongitude());
        newFolder.tx_id.setText(advisorys.getId());
        newFolder.tx_date.setText(advisorys.getDate());
        return row;
    }

    static class NewFolder{

        TextView tx_latitude,tx_longitude,tx_id,tx_date;
    }
}
