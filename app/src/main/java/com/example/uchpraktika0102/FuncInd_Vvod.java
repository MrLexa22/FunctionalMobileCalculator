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

public class FuncInd_Vvod extends AppCompatActivity {
    String pol;
    String vozr;
    String loop;
    String rost;
    String ves;
    String shags;
    String chss;
    String sad;
    String dad;
    String zhel;
    String shtange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_ind_vvod);
        setTitle("Индекс функциональных изменений");
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Bundle intent = getIntent().getExtras();
        loop = intent.getString("loop");
        vozr = intent.getString("vozr");
        pol = intent.getString("pol");
        rost = intent.getString("rost");
        ves = intent.getString("ves");
        shags = intent.getString("shags");
        chss = intent.getString("chss");
        sad = intent.getString("sad");
        dad = intent.getString("dad");
        zhel = intent.getString("zhel");
        shtange = intent.getString("shtange");

        EditText vozrast_edit = findViewById(R.id.vozrast);
        vozrast_edit.setText(vozr);
        vozrast_edit.setEnabled(false);

        EditText chss_edit = findViewById(R.id.chss);
        EditText sad_edit = findViewById(R.id.sad);
        EditText dad_edit = findViewById(R.id.dad);
        EditText ves_edit = findViewById(R.id.ves);
        EditText rost_edit = findViewById(R.id.rost);
        if(dad != null && chss != null && sad != null && ves != null && rost != null){
            sad_edit.setText(sad);
            sad_edit.setEnabled(false);
            dad_edit.setText(dad);
            dad_edit.setEnabled(false);
            chss_edit.setText(chss);
            chss_edit.setEnabled(false);
            ves_edit.setText(ves);
            ves_edit.setEnabled(false);
            rost_edit.setText(rost);
            rost_edit.setEnabled(false);
        }

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sad_int = 0;
                int dad_int = 0;
                int chss_int = 0;
                int vozr_int= 0;
                double ves_double = 0;
                double rost_double = 0;
                try {
                    String t = rost_edit.getText().toString();
                    rost_edit.setText(t.replaceAll(",","."));
                    t = ves_edit.getText().toString();
                    ves_edit.setText(t.replaceAll(",","."));

                    sad_int = Integer.parseInt(sad_edit.getText().toString());
                    dad_int = Integer.parseInt(dad_edit.getText().toString());
                    chss_int = Integer.parseInt(chss_edit.getText().toString());
                    vozr_int = Integer.parseInt(vozrast_edit.getText().toString());
                    ves_double = Double.parseDouble(ves_edit.getText().toString());
                    rost_double = Double.parseDouble(rost_edit.getText().toString());

                    if(sad_int <= 0 || dad_int <= 0 || chss_int <= 0 || vozr_int <= 0 || ves_double <= 0 || rost_double <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    if(rost_double >= 3)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Некорректный рост. Рост указывается в МЕТРАХ (1,75)", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    Intent a = new Intent(FuncInd_Vvod.this,IMT_Res.class);
                    String opisanie = null;
                    double resulttets = (0.011 * chss_int) + (0.014*sad_int) + (0.008 * dad_int) + (0.014*vozr_int) + (0.009*ves_double) - (0.009*(rost_double*100))-0.27;

                    if (resulttets < 2.6) opisanie = "функциональные возможности системы кровообращения хорошие";
                    if (resulttets >= 2.6  && resulttets <= 3.09) opisanie = "удовлетворительные функциональные возможности системы кровообращения";
                    if (resulttets > 3.09) opisanie = "сниженные, недостаточные возможности системы кровообращения";

                    String metodrecom = null;
                    metodrecom = "Оценку индекса функциональных изменений (ИФИ) осуществляют по следующей шкале:\n" +
                            "ИФИ менее 2,6 — функциональные возможности системы кровообращения хорошие. Механизмы адаптации устойчивы: действие неблагоприятных факторов студенческого образа жизни успешно компенсируется мобилизацией внутренних резервов организма.\n" +
                            "ИФИ, равный 2,6—3,09 — удовлетворительные функциональные возможности системы кровообращения с умеренным напряжением механизмов регуляции.\n" +
                            "ИФИ более 3,09 — сниженные, недостаточные возможности системы кровообращения.";

                    a.putExtra("recom",metodrecom);
                    a.putExtra("opisanie",opisanie);
                    a.putExtra("shags",shags);
                    DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
                    a.putExtra("chss",String.valueOf(chss_int));
                    a.putExtra("shtange",shtange);
                    a.putExtra("sad",sad);
                    a.putExtra("dad",String.valueOf(dad_int));
                    a.putExtra("pol",pol);
                    a.putExtra("vozr",vozr);
                    a.putExtra("loop",loop);
                    a.putExtra("rost",rost);
                    a.putExtra("ves",ves);
                    a.putExtra("zhel",zhel);
                    a.putExtra("res",decimalFormat.format(resulttets));
                    a.putExtra("deistvie","8");

                    String name = "Индекс функциональных изменений";
                    String resuli = "Результат: "+decimalFormat.format(resulttets);
                    String nm = "Нормой являются значения 2.6-3.09";
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String dt = formatForDateNow.format(dateNow);

                    ArrayAdapter<ModelResult> adapter;
                    List<ModelResult> users;
                    users = new ArrayList<>();
                    List<ModelResult> tes = new ArrayList<>();
                    tes = JSONHelper.importFromJSON(FuncInd_Vvod.this);
                    ModelResult resy = new ModelResult(name, resuli, nm, dt);
                    if(tes != null)
                        users.addAll(tes);
                    users.add(resy);
                    JSONHelper.exportToJSON(FuncInd_Vvod.this, users);

                    startActivity(a);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Только целые числа! Рост и вес - целое/с запятой", Toast.LENGTH_SHORT);
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
                    Intent a = new Intent(FuncInd_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(FuncInd_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(FuncInd_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}