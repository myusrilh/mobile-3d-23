package org.aplas.checkboxes_and_etc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chocolate;
    CheckBox sprinkles;
    CheckBox nuts;
    CheckBox cherries;
    CheckBox oreo;
    Button showToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chocolate = findViewById(R.id.chocolate_syrup);
        sprinkles = findViewById(R.id.sprinkles);
        nuts =  findViewById(R.id.crushed_nuts);
        cherries = findViewById(R.id.cherries);
        oreo = findViewById(R.id.oreo_cookies);
        showToast = findViewById(R.id.show_toast);

    }

    public void onClickToast(View view) {
        String message = "";

        if(chocolate.isChecked()){
            if(message.equals("")){
                message += " " +getString(R.string.chocolate_syrup);
            }else{
                message += " + " +getString(R.string.chocolate_syrup);
            }
        }
        if(sprinkles.isChecked()){
            if(message.equals("")){
                message += " " +getString(R.string.sprinkles);
            }else{
                message += " + " +getString(R.string.sprinkles);
            }
        }
        if(nuts.isChecked()){
            if(message.equals("")){
                message += " " +getString(R.string.crushed_nuts);
            }else{
                message += " + " +getString(R.string.crushed_nuts);
            }
        }
        if(cherries.isChecked()){
            if(message.equals("")){
                message += " " +getString(R.string.cherries);
            }else{
                message += " + " +getString(R.string.cherries);
            }
        }
        if(oreo.isChecked()){
            if(message.equals("")){
                message += " " +getString(R.string.oreo_cookies_crumbles);
            }else{
                message += " + " +getString(R.string.oreo_cookies_crumbles);
            }
        }


        Toast.makeText(getApplicationContext(),"Toppings : " + message,Toast.LENGTH_SHORT).show();
    }
}