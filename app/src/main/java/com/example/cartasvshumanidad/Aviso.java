package com.example.cartasvshumanidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class Aviso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);


        //Lanzo el ScreenSplash y a los 4 segundos lanzo la activity login
        new Handler() .postDelayed (new Runnable() {
            @Override
            public void run(){

                //Lanzo un toast de bienvenida
                Context context = getApplicationContext();
                CharSequence text = "Bienvenido";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent=new Intent (Aviso.this,Login.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}