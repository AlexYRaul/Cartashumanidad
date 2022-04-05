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
                Intent intent=new Intent (MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}