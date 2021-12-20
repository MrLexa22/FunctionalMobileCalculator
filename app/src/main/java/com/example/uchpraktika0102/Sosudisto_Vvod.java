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

public class Sosudisto_Vvod extends AppCompatActivity {
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
        setContentView(R.layout.activity_sosudisto_vvod);
        setTitle("Уровень регуляции сердечно-сосудистой системы");
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

        EditText sad_edit = findViewById(R.id.sad);
        EditText dad_edit = findViewById(R.id.dad);
        if(sad != null && dad != null){
            sad_edit.setText(sad);
            dad_edit.setEnabled(false);
            dad_edit.setText(dad);
            sad_edit.setEnabled(false);
        }

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chss_int = 0;
                int sad_int = 0;
                int dad_int = 0;
                try {
                    sad_int = Integer.parseInt(sad_edit.getText().toString());
                    dad_int = Integer.parseInt(dad_edit.getText().toString());
                    if(sad_int <= 0 || dad_int <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    Intent a = new Intent(Sosudisto_Vvod.this,IMT_Res.class);
                    String opisanie = null;
                    double resulttets = (dad_int*sad_int)/100;
                    if(resulttets <= 74) opisanie = "Высокий уровень регуляции сердечно-сосудистой системы";
                    if(resulttets > 74 && resulttets <= 81) opisanie = "Выше среднего";
                    if(resulttets > 81 && resulttets <= 90) opisanie = "Средний";
                    if(resulttets > 90 && resulttets < 101) opisanie = "Ниже среднего";
                    if(resulttets >= 101) opisanie = "низкое значение регуляции";

                    String metodrecom = null;
                    metodrecom = "до 74 усл. ед. – высокий уровень регуляции сердечно-сосудистой системы; \n" +
                            "75-80 – выше среднего; \n" +
                            "81-90 – средний; \n" +
                            "91-100 – ниже среднего; \n" +
                            "101 и выше – низкое значение регуляции. \n" +
                            "- показатели регуляции сердечно-сосудистой системы у спортсменов ниже, чем у нетренированных лиц, так как сердце спортсмена в условиях покоя работает в более экономичном режиме, при меньшем потреблении кислорода.\n" +
                            "индекс используется для косвенного определения степени обеспеченности миокарда кислородом\n" +
                            "- результаты индекса на студентах РЭУ им. Г.В. Плеханова: \n" +
                            "девушки 90-101 усл. ед., юноши 92,3-96 усл. ед\n" +
                            "- в процессе регулярных занятий ФКиС значения индекса постепенно снижаются, и могут достигать среднего, выше среднего и высокого уровня регуляции сердечно-сосудистой системы\n";

                    a.putExtra("recom",metodrecom);
                    a.putExtra("opisanie",opisanie);
                    a.putExtra("shags",shags);
                    DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
                    a.putExtra("chss",chss);
                    a.putExtra("sad",String.valueOf(sad_int));
                    a.putExtra("dad",String.valueOf(dad_int));
                    a.putExtra("pol",pol);
                    a.putExtra("vozr",vozr);
                    a.putExtra("loop",loop);
                    a.putExtra("rost",rost);
                    a.putExtra("ves",ves);
                    a.putExtra("res",decimalFormat.format(resulttets));
                    a.putExtra("deistvie","4");

                    String name = "Уровень регуляции сердечно-сосудистой системы";
                    String resuli = "Результат: "+decimalFormat.format(resulttets);
                    String nm = "Нормой являются значения 81-100";
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String dt = formatForDateNow.format(dateNow);

                    ArrayAdapter<ModelResult> adapter;
                    List<ModelResult> users;
                    users = new ArrayList<>();
                    List<ModelResult> tes = new ArrayList<>();
                    tes = JSONHelper.importFromJSON(Sosudisto_Vvod.this);
                    ModelResult resy = new ModelResult(name, resuli, nm, dt);
                    if(tes != null)
                        users.addAll(tes);
                    users.add(resy);
                    JSONHelper.exportToJSON(Sosudisto_Vvod.this, users);

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
                    Intent a = new Intent(Sosudisto_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(Sosudisto_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(Sosudisto_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}