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

public class KoefSkibin_Vvod extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koef_skibin_vvod);

        setTitle("Циркулярно-респираторный коэффициент Скибински (усл. ед.)");
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

        EditText zhel_edit = findViewById(R.id.zhel);
        EditText chss_edit = findViewById(R.id.chss);
        EditText shtange_edit = findViewById(R.id.shtange);
        if(zhel != null && chss != null){
            zhel_edit.setText(zhel);
            zhel_edit.setEnabled(false);
            chss_edit.setText(chss);
            chss_edit.setEnabled(false);
        }

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chss_int = 0;
                int shtange_int = 0;
                int zhel_int = 0;
                try {
                    chss_int = Integer.parseInt(chss_edit.getText().toString());
                    zhel_int = Integer.parseInt(zhel_edit.getText().toString());
                    shtange_int = Integer.parseInt(shtange_edit.getText().toString());

                    if(chss_int <= 0 || zhel_int <= 0 || shtange_int <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                    Intent a = new Intent(KoefSkibin_Vvod.this,IMT_Res.class);
                    String opisanie = null;
                    double resulttets = ((zhel_int/100)*shtange_int)/chss_int;

                    if (resulttets <= 5) opisanie = "очень плохо";
                    if (resulttets > 5 && resulttets <= 10) opisanie = "неудовлетворительно";
                    if (resulttets > 10 && resulttets < 30) opisanie = "удовлетворительно";
                    if (resulttets >= 30 && resulttets < 60) opisanie = "хорошо";
                    if (resulttets >= 60) opisanie = "очень хорошо";


                    String metodrecom = null;
                    metodrecom = "Оценка индекса: \n" +
                            "<5 усл. ед. – очень плохо (низкий уровень выносливость сердечно-сосудистой и дыхательной систем), \n" +
                            "5-10 – неудовлетворительно, \n" +
                            "10-30 – удовлетворительно, \n" +
                            "30-60 – хорошо,\n" +
                            " > 60 – очень хорошо (высокий уровень выносливости). \n" +
                            "- проба Штанге (с), определяющая гипоксическую устойчивость организма (время задержки дыхания на вдохе). \n" +
                            "- индекс Скибински характеризует состояние сердечно-сосудистой и дыхательной систем.\n";

                    a.putExtra("recom",metodrecom);
                    a.putExtra("opisanie",opisanie);
                    a.putExtra("shags",shags);
                    DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
                    a.putExtra("chss",String.valueOf(chss_int));
                    a.putExtra("shtange",String.valueOf(shtange_int));
                    a.putExtra("sad",sad);
                    a.putExtra("dad",dad);
                    a.putExtra("pol",pol);
                    a.putExtra("vozr",vozr);
                    a.putExtra("loop",loop);
                    a.putExtra("rost",rost);
                    a.putExtra("ves",ves);
                    a.putExtra("zhel",String.valueOf(zhel_int));
                    a.putExtra("res",decimalFormat.format(resulttets));
                    a.putExtra("deistvie","6");

                    String name = "Циркулярно-респираторный коэффициент Скибински (усл. ед.)";
                    String resuli = "Результат: "+decimalFormat.format(resulttets);
                    String nm = "Нормой являются значения 10-60";
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String dt = formatForDateNow.format(dateNow);

                    ArrayAdapter<ModelResult> adapter;
                    List<ModelResult> users;
                    users = new ArrayList<>();
                    List<ModelResult> tes = new ArrayList<>();
                    tes = JSONHelper.importFromJSON(KoefSkibin_Vvod.this);
                    ModelResult resy = new ModelResult(name, resuli, nm, dt);
                    if(tes != null)
                        users.addAll(tes);
                    users.add(resy);
                    JSONHelper.exportToJSON(KoefSkibin_Vvod.this, users);

                    startActivity(a);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Только целые числа!", Toast.LENGTH_SHORT);
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
                    Intent a = new Intent(KoefSkibin_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(KoefSkibin_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(KoefSkibin_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}