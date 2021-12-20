package com.example.uchpraktika0102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    Button imt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setTitle("Индексы");
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.indexes);
        spinner = findViewById(R.id.spinner);
        /*spinner2 = findViewById(R.id.spinner2);*/

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.blue_for_colors));
                ((TextView) parent.getChildAt(0)).setTextSize(24);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        EditText vozrast = findViewById(R.id.vozrast);
        vozrast.setText("18");

        Button allTests = findViewById(R.id.buttomToStart);
        allTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,IMT_Vvod.class);
                    a.putExtra("loop","1");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button imt = findViewById(R.id.imt);
        imt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,IMT_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button dv = findViewById(R.id.dvigactive);
        dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,DvigatActiv_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button vv = findViewById(R.id.vinoslivost);
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,Vinoslivost_Vvdo.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button ser = findViewById(R.id.serdche);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,Sosudisto_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button zhiz = findViewById(R.id.shiznenindex);
        zhiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,ZhiznInd_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button Skibi = findViewById(R.id.skibinski);
        Skibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,KoefSkibin_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button kre = findViewById(R.id.kerdo);
        kre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,KredoInd_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });

        Button ff = findViewById(R.id.funcizmen);
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = vozrast.getText().toString();
                int h = checker(t);
                if(h != -1)
                {
                    Intent a = new Intent(MainMenu.this,FuncInd_Vvod.class);
                    a.putExtra("loop","0");
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    a.putExtra("pol",selected);
                    a.putExtra("vozr",String.valueOf(h));
                    startActivity(a);
                }
            }
        });
    }

    public int checker(String t)
    {
        int vozr = 0;
        if(t.length() > 0 && !t.contains(" "))
        {
            try {
                vozr = Integer.parseInt(t);
            }
            catch (Exception e)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Введено не ЦЕЛОЕ число!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Пробелы недопустимы, строка должна иметь значение!", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(vozr < 18 || vozr > 150)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Возраст должен быть не меньше 18 и не больше 150!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(vozr >= 18 && vozr <= 150)
            return vozr;

        return -1;
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent a = new Intent(MainMenu.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(MainMenu.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}