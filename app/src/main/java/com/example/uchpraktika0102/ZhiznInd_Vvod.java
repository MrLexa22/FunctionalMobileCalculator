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

public class ZhiznInd_Vvod extends AppCompatActivity {
    String pol;
    String vozr;
    String loop;
    String rost;
    String ves;
    String shags;
    String chss;
    String sad;
    String dad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhizn_ind_vvod);
        setTitle("Жизненный индекс (мл/кг)");
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

        EditText ves_edit = findViewById(R.id.massa);
        EditText zhel_edit = findViewById(R.id.zhel);
        if(ves != null){
            ves_edit.setText(ves);
            ves_edit.setEnabled(false);
        }

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ves_double = 0;
                int zhel_int = 0;
                try {
                    String tyt = ves_edit.getText().toString();
                    ves_edit.setText(tyt.replaceAll(",","."));

                    ves_double = Double.parseDouble(ves_edit.getText().toString());
                    zhel_int = Integer.parseInt(zhel_edit.getText().toString());

                    if(ves_double <= 0 || zhel_int <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                    Intent a = new Intent(ZhiznInd_Vvod.this,IMT_Res.class);
                    String opisanie = null;
                    double resulttets = zhel_int/ves_double;
                    if(resulttets<=61 && resulttets>=50) opisanie = "норма";
                    if(resulttets>61) opisanie= "выше нормы";
                    if(resulttets<50) opisanie= "ниже нормы";

                    String metodrecom = null;
                    metodrecom = "- норма 50-61 мл/кг; если показатель меньше, то это может свидетельствовать о недостаточности кислород обеспечения организма, недостаточной жизненной емкости легких, либо избыточной массе тела \n" +
                            "- для обследуемых, имеющих жизненный индекс ниже нормы рекомендуются физические упражнения средней интенсивности, наряду с этим допускается чередование высокоинтенсивных (молодой и средний возраст) и малоинтенсивных тренировочных нагрузок.\n" +
                            "- на увеличение жизненной емкости легких влияют регулярные занятия физической культурой и спортом, особенно занятия аэробной направленности (бег, велопрогулки, плавание, аэробика, работа на кардиотренажерах, спортивные игры и другие).\n" +
                            "-при большой жизненной емкости (ЖЕЛ) легкие лучше вентилируются и организм получает больше кислорода. Норма ЖЕЛ у мужчин 3500-5000 мл, у женщин 2500-4000 мл. В исследовании РЭУ им. Г.В. Плеханова ЖЕЖ у девушек 2100-2600 мл, у юношей 3800-4150 мл.\n" +
                            "-у лиц, не занимающихся физической культурой и спортом чаще всего, жизненная емкость легких ниже по сравнению с лицами регулярно занимающимися ФКиС\n";

                    a.putExtra("recom",metodrecom);
                    a.putExtra("opisanie",opisanie);
                    a.putExtra("shags",shags);
                    DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
                    a.putExtra("chss",chss);
                    a.putExtra("sad",sad);
                    a.putExtra("dad",dad);
                    a.putExtra("pol",pol);
                    a.putExtra("vozr",vozr);
                    a.putExtra("loop",loop);
                    a.putExtra("rost",rost);
                    a.putExtra("ves",String.valueOf(ves_double));
                    a.putExtra("zhel",String.valueOf(zhel_int));
                    a.putExtra("res",decimalFormat.format(resulttets));
                    a.putExtra("deistvie","5");

                    String name = "Жизненный индекс (мл/кг)";
                    String resuli = "Результат: "+decimalFormat.format(resulttets);
                    String nm = "Нормой являются значения 50-61";
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String dt = formatForDateNow.format(dateNow);

                    ArrayAdapter<ModelResult> adapter;
                    List<ModelResult> users;
                    users = new ArrayList<>();
                    List<ModelResult> tes = new ArrayList<>();
                    tes = JSONHelper.importFromJSON(ZhiznInd_Vvod.this);
                    ModelResult resy = new ModelResult(name, resuli, nm, dt);
                    if(tes != null)
                        users.addAll(tes);
                    users.add(resy);
                    JSONHelper.exportToJSON(ZhiznInd_Vvod.this, users);

                    startActivity(a);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "ЖЕЛ - целое число (МЛ). Вес - целое/с запятой", Toast.LENGTH_SHORT);
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
                    Intent a = new Intent(ZhiznInd_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(ZhiznInd_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(ZhiznInd_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}