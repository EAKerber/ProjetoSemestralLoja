package com.example.projetosemestralloja.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.ui.LoginScreen;

public class SplashScreen extends AppCompatActivity {

    ImageView logo_riter;
    Animation animacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo_riter = (ImageView) findViewById(R.id.ritter_logo2);

        animacao = AnimationUtils.loadAnimation(this,R.anim.animacao);
        logo_riter.setAnimation(animacao);

        getSupportActionBar().hide();
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getBaseContext(), LoginScreen.class) );

                finish();

            }
        }, 6000);


    }
}