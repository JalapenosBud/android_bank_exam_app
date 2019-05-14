package com.example.examproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FrontPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        Button sign_in = (Button) findViewById(R.id.button_sign_in);

        Intent sign_in_intent = new Intent(this, HomeActivity.class);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startactivity starts a new activity
                startActivity(sign_in_intent);
            }
        });
    }
}