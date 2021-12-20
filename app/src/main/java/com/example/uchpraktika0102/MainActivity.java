package com.example.uchpraktika0102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnnew = (Button)findViewById(R.id.buttonToMenu);
        btnnew.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(this, MainMenu.class);
        this.startActivity(myIntent);
    }
}