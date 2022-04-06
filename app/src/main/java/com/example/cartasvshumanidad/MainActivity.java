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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Añado animación de desplazamiento a la imagen del ScreenSplash
        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        ImageView splash = findViewById(R.id.imgSplash);
        splash.setAnimation(animacion1);


        //Lanzo el ScreenSplash y a los 3 segundos lanzo la activity login
        new Handler() .postDelayed (new Runnable() {
            @Override
            public void run(){
                Intent intent=new Intent (MainActivity.this,Aviso.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}