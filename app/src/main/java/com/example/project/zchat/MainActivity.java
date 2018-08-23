package com.example.project.zchat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView4);

        // to add animation to welcome screen
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 1440f);
        animatorX.setDuration(3000);
        animatorX.start();

        // new intent
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(homeIntent);
                finish();
            }
        },3000);


    }

}
