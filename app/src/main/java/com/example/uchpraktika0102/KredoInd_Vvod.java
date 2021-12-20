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

public class KredoInd_Vvod extends AppCompatActivity {
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
        setContentView(R.layout.activity_kredo_ind_vvod);

        setTitle("Вегетативный индекс Кердо (усл. ед)");
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

        EditText dad_edit = findViewById(R.id.dad);
        EditText chss_edit = findViewById(R.id.chss);
        if(dad != null && chss != null){
            chss_edit.setText(chss);
            chss_edit.setEnabled(false);
            dad_edit.setText(dad);
            dad_edit.setEnabled(false);
        }

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dad_int = 0;
                int chss_int = 0;
                try {
                    dad_int = Integer.parseInt(dad_edit.getText().toString());
                    chss_int = Integer.parseInt(chss_edit.getText().toString());
                    if(chss_int <= 0 || dad_int <= 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Числа не могут быть отрицательными!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    Intent a = new Intent(KredoInd_Vvod.this,IMT_Res.class);
                    String opisanie = null;
                    double resulttets = 100*(1-(dad_int/chss_int));

                    if (resulttets >= 31) opisanie = "выраженная симпатикотония";
                    if (resulttets >= 16 && resulttets <= 30) opisanie = "симпатикотония";
                    if (resulttets >= -15 && resulttets <= 15) opisanie = "уравновешенность симпатических и парасимпатических  влияний";
                    if (resulttets >= -16 && resulttets < -30) opisanie = "парасимпатикотония";
                    if (resulttets <= -30) opisanie = "парасимпатикотония";

                    String metodrecom = null;
                    metodrecom = "Индекс Кердо в норме равен 0 усл. ед., что демонстрирует оптимальный уровень вегетативной регуляции сердечно-сосудистой системы, \n" +
                            "- при преобладании симпатического тонуса отмечается увеличение, - при преобладании парасимпатического тонуса отмечается снижение индекса.\n";

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
                    a.putExtra("deistvie","7");

                    String name = "Вегетативный индекс Кердо (усл. ед)";
                    String resuli = "Результат: "+decimalFormat.format(resulttets);
                    String nm = "Нормой является приближение к нулю (0)";
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String dt = formatForDateNow.format(dateNow);

                    ArrayAdapter<ModelResult> adapter;
                    List<ModelResult> users;
                    users = new ArrayList<>();
                    List<ModelResult> tes = new ArrayList<>();
                    tes = JSONHelper.importFromJSON(KredoInd_Vvod.this);
                    ModelResult resy = new ModelResult(name, resuli, nm, dt);
                    if(tes != null)
                        users.addAll(tes);
                    users.add(resy);
                    JSONHelper.exportToJSON(KredoInd_Vvod.this, users);

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
                    Intent a = new Intent(KredoInd_Vvod.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(KredoInd_Vvod.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(KredoInd_Vvod.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}