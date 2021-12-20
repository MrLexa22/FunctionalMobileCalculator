package com.example.uchpraktika0102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DvigatActiv_Vvod extends AppCompatActivity {

    String loop;
    String rost;
    String ves;
    String vozr;
    String pol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvigat_activ_vvod);
        setTitle("Уровень двигательной активности");
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Bundle intent = getIntent().getExtras();
        loop = intent.getString("loop");
        vozr = intent.getString("vozr");
        pol = intent.getString("pol");
        rost = intent.getString("rost");
        ves = intent.getString("ves");
        EditText shagi = findViewById(R.id.shagi);

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shags = 0;
                try {
                    shags = Integer.parseInt(shagi.getText().toString());
                    if(shags <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Количетсво шагов не может быть отрицательным!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    else {
                        Intent a = new Intent(DvigatActiv_Vvod.this,IMT_Res.class);
                        String opisanie = null;
                        if (shags <= 5000) {
                            opisanie = ("Сидячая работа");
                        }
                        if (shags > 5000 && shags <= 9999) {
                            opisanie = ("Несколько активная работа");
                        }
                        if (shags >= 10000 && shags <= 12499) {
                            opisanie = ("Активный образ жизни");
                        }
                        if (shags >= 12500) {
                            opisanie = ("Очень активный образ жизни");
                        }

                        String metodrecom = null;
                        metodrecom = "<5000 шагов в день – «сидячая работа»; \n" +
                                "7500-9999 шагов в день – «несколько активная работа»; \n" +
                                "10-12 тыс. шагов – «активный образ жизни»; \n" +
                                "свыше 12,5 тыс. шагов – «очень активный образ жизни»\n";

                        a.putExtra("recom",metodrecom);
                        a.putExtra("opisanie",opisanie);
                        a.putExtra("shags",String.valueOf(shags));
                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",rost);
                        a.putExtra("ves",ves);
                        a.putExtra("res",String.valueOf(shags));
                        a.putExtra("deistvie","2");

                        String name = "Уровень двигательной активности (число шагов в сутки)";
                        String resuli = "Результат: "+shags;
                        String nm = "Нормой являются значения>10000";
                        Date dateNow = new Date();
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                        String dt = formatForDateNow.format(dateNow);

                        ArrayAdapter<ModelResult> adapter;
                        List<ModelResult> users;
                        users = new ArrayList<>();
                        List<ModelResult> tes = new ArrayList<>();
                        tes = JSONHelper.importFromJSON(DvigatActiv_Vvod.this);
                        ModelResult resy = new ModelResult(name, resuli, nm, dt);
                        if(tes != null)
                            users.addAll(tes);
                        users.add(resy);
                        JSONHelper.exportToJSON(DvigatActiv_Vvod.this, users);

                        startActivity(a);
                    }
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Укажите ЦЕЛОЕ количество шагов за сутки", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent a = new Intent(DvigatActiv_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(DvigatActiv_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(DvigatActiv_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}