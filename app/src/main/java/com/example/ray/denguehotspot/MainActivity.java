package com.example.ray.denguehotspot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MainActivity extends AppCompatActivity {

    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.option, R.mipmap.plus);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.report_main)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.admin_main)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.images)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.die);
        // .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.download);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             Intent intent=new Intent(MainActivity.this,reporter.class);
                                                             startActivity(intent);



                                                             break;
                                                         case 1:
                                                             Toast.makeText(MainActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                                                             Intent intentt=new Intent(MainActivity.this,Admin.class);
                                                             startActivity(intentt);

                                                             break;
                                                         case 2:
                                                             Toast.makeText(MainActivity.this, "View Advisory", Toast.LENGTH_SHORT).show();
                                                             Intent intenttt=new Intent(MainActivity.this,viewAdvisory.class);
                                                             startActivity(intenttt);
                                                             break;
                                                         case 3:
                                                             Toast.makeText(MainActivity.this, "See dengue hotspot", Toast.LENGTH_SHORT).show();
                                                             Intent intentttt=new Intent(MainActivity.this,recentHistory.class);
                                                             startActivity(intentttt);

                                                             break;
                                                        /* case 4:
                                                             Toast.makeText(MainActivity.this, "GPS button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;*/
                                                     }
                                                 }
                                             }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {
                                                         //Toast.makeText(MainActivity.this, "Menu Opend", Toast.LENGTH_SHORT).show();
                                                     }

                                                     @Override
                                                     public void onMenuClosed() {
                                                         // Toast.makeText(MainActivity.this, "Menu Closed", Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
        );

    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }


}
