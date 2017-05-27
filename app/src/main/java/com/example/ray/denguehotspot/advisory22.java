package com.example.ray.denguehotspot;

/**
 * Created by ray on 5/16/2017.
 */

public class advisory22 {

    private String latitude,longitude,id,date;

    public advisory22(String latitude,String longitude,String id,String date){

        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setId(id);
        this.setDate(date);


    }

    public String getLatitude(){
        return latitude;

    }

    public void setLatitude(String latitude){

        this.latitude=latitude;
    }

    public String getLongitude(){
        return longitude;

    }

    public void setLongitude(String longitude){

        this.longitude=longitude;
    }

    public String getId(){
        return id;

    }

    public void setId(String id){

        this.id=id;
    }


    public String getDate(){
        return date;

    }

    public void setDate(String date){

        this.date=date;
    }



}

