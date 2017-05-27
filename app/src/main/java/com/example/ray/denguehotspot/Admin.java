package com.example.ray.denguehotspot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//In this class  getting user name and password from admin
public class Admin extends AppCompatActivity {

    EditText ET_name,ET_password;
    String login_name,login_password;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ET_name=(EditText)findViewById(R.id.editText9);
        ET_password=(EditText)findViewById(R.id.editText10);
        btn1=(Button)findViewById(R.id.button5);
    }

    public void userLogin(View view)
    {

        login_name=ET_name.getText().toString();
        login_password=ET_password.getText().toString();
        String type="login";
        admin_login admin=new admin_login(this);
        admin.execute(type,login_name,login_password);

    }

   

}


