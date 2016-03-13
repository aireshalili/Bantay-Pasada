package com.example.aires.bantaypasada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class welcomeActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(welcomeActivity.this, MainActivity.class);
                welcomeActivity.this.startActivity(mainIntent);
                welcomeActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
