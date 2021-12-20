package com.example.uchpraktika0102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class IMT_Vvod extends AppCompatActivity {
    String loop;
    EditText rost;
    EditText ves;
    String vozr;
    String pol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imt_vvod);
        setTitle("Индекс массы тела");
        Bundle intent = getIntent().getExtras();
        loop = intent.getString("loop");
        vozr = intent.getString("vozr");
        pol = intent.getString("pol");
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        rost = findViewById(R.id.rost);
        ves = findViewById(R.id.ves);
        Button end = findViewById(R.id.buttomEnd);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rost.length() > 0 && ves.length() > 0)
                {
                    Double rostd = null;
                    Double vestd = null;
                    try {
                        String t = rost.getText().toString();
                        rost.setText(t.replaceAll(",","."));
                        t = ves.getText().toString();
                        ves.setText(t.replaceAll(",","."));

                        rostd = Double.parseDouble(rost.getText().toString());
                        vestd = Double.parseDouble(ves.getText().toString());
                        if(rostd <= 0 || vestd <= 0)
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }
                        if(rostd >= 3)
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Некорректный рост. Рост указывается в МЕТРАХ (1,75)", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }


                        Double res = vestd/(rostd*rostd);

                        Intent a = new Intent(IMT_Vvod.this,IMT_Res.class);
                        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
                        a.putExtra("res",decimalFormat.format(res));

                        String opisanie = null;
                        if(res<18.5&&res>16||res<16) opisanie = "Наблюдается недостаток массы тела";
                        else if(res<25&&res>18.5) opisanie = "Наблюдается нормальная масса тела";
                        else if(res<30&&res>25) opisanie = "Наблюдается избыточная масса тела";
                        else if(res<35&&res>30) opisanie = "Наблюдается первая степень ожирения";
                        else if(res<40&&res>35) opisanie = "Наблюдается вторая степень ожирения";
                        else if(res>40) opisanie = "Наблюдается третья степень ожирения";
                        a.putExtra("opisanie",opisanie);

                        String metodrecom = null;
                        metodrecom = "- 16-18,5 кг/м2 и менее, то наблюдается недостаток массы тела, \n" +
                                "- 18,5-25 кг/м2 – норма, \n" +
                                "25-30 кг/м2 – избыточная масса тела, \n" +
                                "30-35 кг/м2 – первая степень ожирения, \n" +
                                "35-40 кг/м2 – вторая степень ожирения, \n" +
                                "свыше 40 кг/м2 – третья степень ожирения.\n" +
                                "- индекс не учитывает пол, возраст, распределение в организме жирового и мышечного компонента тела\n" +
                                "- средние значения индекса, полученные в РЭУ им. Г.В. Плеханова составили у девушек – 19-23 кг/м2, у юношей 20-23 кг/м2\n" +
                                "- у представителей молодого возраста (до 30 лет) индекс массы тела ниже по сравнению с представителями среднего возраста\n" +
                                "- для поддержания оптимальной массы тела необходимо ходить не менее 10 тысяч шагов в сутки\n" +
                                "- по индексу массы тела можно лишь косвенно судить о норме, недостатке или избыточной массе тела\n";
                        a.putExtra("recom",metodrecom);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",rost.getText().toString());
                        a.putExtra("ves",ves.getText().toString());
                        a.putExtra("deistvie","1");

                        ArrayAdapter<ModelResult> adapter;
                        List<ModelResult> users;
                        users = new ArrayList<>();

                        String name = "Индекс массы тела (кг/м2)";
                        String resuli = "Результат: "+decimalFormat.format(res);
                        String nm = "Нормой являются значения 18.5-25";
                        Date dateNow = new Date();
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                        String dt = formatForDateNow.format(dateNow);

                        List<ModelResult> tes = new ArrayList<>();
                        tes = JSONHelper.importFromJSON(IMT_Vvod.this);
                        ModelResult resy = new ModelResult(name, resuli, nm, dt);
                        if(tes != null)
                            users.addAll(tes);
                        users.add(resy);
                        JSONHelper.exportToJSON(IMT_Vvod.this, users);

                        startActivity(a);
                    }
                    catch (Exception e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Проблема при получении цифр!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Заполнены не все поля!", Toast.LENGTH_SHORT);
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
                    Intent a = new Intent(IMT_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(IMT_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(IMT_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}