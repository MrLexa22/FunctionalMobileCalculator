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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vinoslivost_Vvdo extends AppCompatActivity {
    String loop;
    String rost;
    String ves;
    String vozr;
    String pol;
    String shags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinoslivost_vvdo);
        setTitle("Коэффициент выносливости");
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Bundle intent = getIntent().getExtras();
        loop = intent.getString("loop");
        vozr = intent.getString("vozr");
        pol = intent.getString("pol");
        rost = intent.getString("rost");
        ves = intent.getString("ves");
        shags = intent.getString("shags");

        EditText chss = findViewById(R.id.chss);
        EditText sad = findViewById(R.id.sad);
        EditText dad = findViewById(R.id.dad);

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chss_int = 0;
                int sad_int = 0;
                int dad_int = 0;
                try {
                    chss_int = Integer.parseInt(chss.getText().toString());
                    sad_int = Integer.parseInt(sad.getText().toString());
                    dad_int = Integer.parseInt(dad.getText().toString());
                    if(chss_int <= 0 || sad_int <= 0 || dad_int <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                        Intent a = new Intent(Vinoslivost_Vvdo.this,IMT_Res.class);
                        String opisanie = null;
                        double resulttets = (chss_int*10)/(sad_int-dad_int);

                    if(resulttets < 16){
                        opisanie = ("Усиление кардиореспираторной системы\n");
                    }
                    if(resulttets >= 17){
                        opisanie =("Укрепленная сердечно-сосудистая система");
                    }

                        String metodrecom = null;
                        metodrecom = "В норме коэффициент выносливости – 16 усл. ед., \n" +
                                "- уменьшение – на усиление кардиореспираторной системы. \n" +
                                "- увеличение указывает на ослабление деятельности сердечно-сосудистой системы или детренированности обследуемого\n" +
                                "- при занятиях спортом коэффициент выносливости может быть ниже 16 усл.ед., из-за укрепления сердечно-сосудистой системы, коэффициент выносливости коррелирует с уровнем физической работоспособности организма\n" +
                                "- пульсовое давление ПД (САД-ДАД) в среднем составляет 40 мм рт.ст.\n" +
                                "- для улучшения коэффициента выносливости сердечно-сосудистой системы следует заниматься ФКиС не менее трех раз в неделю, совершать 10-12,5 тысяч шагов в сутки\n";

                        a.putExtra("recom",metodrecom);
                        a.putExtra("opisanie",opisanie);
                        a.putExtra("shags",shags);
                        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
                        a.putExtra("vinoslivost",decimalFormat.format(resulttets));
                        a.putExtra("chss",String.valueOf(chss_int));
                        a.putExtra("sad",String.valueOf(sad_int));
                        a.putExtra("dad",String.valueOf(dad_int));
                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",rost);
                        a.putExtra("ves",ves);
                        a.putExtra("res",decimalFormat.format(resulttets));
                        a.putExtra("deistvie","3");

                        String name = "Коэффициент выносливости";
                        String resuli = "Результат: "+decimalFormat.format(resulttets);
                        String nm = "Нормой явлется значение 16";
                        Date dateNow = new Date();
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                        String dt = formatForDateNow.format(dateNow);

                        ArrayAdapter<ModelResult> adapter;
                        List<ModelResult> users;
                        users = new ArrayList<>();
                        List<ModelResult> tes = new ArrayList<>();
                        tes = JSONHelper.importFromJSON(Vinoslivost_Vvdo.this);
                        ModelResult resy = new ModelResult(name, resuli, nm, dt);
                        if(tes != null)
                            users.addAll(tes);
                        users.add(resy);
                        JSONHelper.exportToJSON(Vinoslivost_Vvdo.this, users);

                        startActivity(a);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Укажите ЦЕЛОЕ число", Toast.LENGTH_SHORT);
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
                    Intent a = new Intent(Vinoslivost_Vvdo.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(Vinoslivost_Vvdo.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(Vinoslivost_Vvdo.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}