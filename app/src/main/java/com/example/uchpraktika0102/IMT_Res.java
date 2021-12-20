package com.example.uchpraktika0102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IMT_Res extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imt_res);
        setTitle("Результат");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Bundle intent = getIntent().getExtras();
        String loop = intent.getString("loop");
        String pol = intent.getString("pol");
        String vozr = intent.getString("vozr");

        Button end = findViewById(R.id.buttomEnd);
        TextView result_imt = findViewById(R.id.result_imt);
        result_imt.setText("Ваш результат: "+intent.getString("res"));
        TextView opisanie_imt = findViewById(R.id.opisanie_imt);
        opisanie_imt.setText("Описание: \n"+intent.getString("opisanie"));
        TextView recomen = findViewById(R.id.recomen);
        recomen.setText(intent.getString("recom"));
        if(loop.equals("0"))
        {
            end.setText("Вернуться в меню");
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(IMT_Res.this,MainMenu.class);
                    startActivity(a);
                }
            });
        }
        else if(loop.equals("1"))
        {
            end.setText("Продолжить");
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String deist = intent.getString("deistvie");
                    if(deist.equals("1"))
                    {
                        Intent a = new Intent(IMT_Res.this,DvigatActiv_Vvod.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));

                        startActivity(a);
                    }
                    else if(deist.equals("2"))
                    {
                        Intent a = new Intent(IMT_Res.this,Vinoslivost_Vvdo.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));

                        startActivity(a);
                    }
                    else if(deist.equals("3"))
                    {
                        Intent a = new Intent(IMT_Res.this,Sosudisto_Vvod.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));
                        a.putExtra("chss",intent.getString("chss"));
                        a.putExtra("sad",intent.getString("sad"));
                        a.putExtra("dad",intent.getString("dad"));
                        startActivity(a);
                    }
                    else if(deist.equals("4"))
                    {
                        Intent a = new Intent(IMT_Res.this,ZhiznInd_Vvod.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));
                        a.putExtra("chss",intent.getString("chss"));
                        a.putExtra("sad",intent.getString("sad"));
                        a.putExtra("dad",intent.getString("dad"));
                        startActivity(a);
                    }
                    else if(deist.equals("5"))
                    {
                        Intent a = new Intent(IMT_Res.this,KoefSkibin_Vvod.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));
                        a.putExtra("chss",intent.getString("chss"));
                        a.putExtra("sad",intent.getString("sad"));
                        a.putExtra("dad",intent.getString("dad"));
                        a.putExtra("zhel",intent.getString("zhel"));
                        startActivity(a);
                    }
                    else if(deist.equals("6"))
                    {
                        Intent a = new Intent(IMT_Res.this,KredoInd_Vvod.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));
                        a.putExtra("chss",intent.getString("chss"));
                        a.putExtra("sad",intent.getString("sad"));
                        a.putExtra("dad",intent.getString("dad"));
                        a.putExtra("zhel",intent.getString("zhel"));
                        a.putExtra("shtange",intent.getString("shtange"));
                        startActivity(a);
                    }
                    else if(deist.equals("7"))
                    {
                        Intent a = new Intent(IMT_Res.this,FuncInd_Vvod.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));
                        a.putExtra("chss",intent.getString("chss"));
                        a.putExtra("sad",intent.getString("sad"));
                        a.putExtra("dad",intent.getString("dad"));
                        a.putExtra("zhel",intent.getString("zhel"));
                        a.putExtra("shtange",intent.getString("shtange"));
                        startActivity(a);
                    }
                    else if(deist.equals("8"))
                    {
                        Intent a = new Intent(IMT_Res.this,AllItog_Res.class);

                        a.putExtra("pol",pol);
                        a.putExtra("vozr",vozr);
                        a.putExtra("loop",loop);
                        a.putExtra("rost",intent.getString("rost"));
                        a.putExtra("ves",intent.getString("ves"));
                        a.putExtra("shags",intent.getString("shags"));
                        a.putExtra("chss",intent.getString("chss"));
                        a.putExtra("sad",intent.getString("sad"));
                        a.putExtra("dad",intent.getString("dad"));
                        a.putExtra("zhel",intent.getString("zhel"));
                        a.putExtra("shtange",intent.getString("shtange"));
/*                        Toast toast = Toast.makeText(getApplicationContext(), "Пол: "+pol+"\nВозр: "+vozr+"\nloop: "+loop+"\nРост: "+intent.getString("rost")+
                                "\nВес: "+intent.getString("ves")+"\nШаги: "+intent.getString("shags")+"\nЧСС: "+
                                intent.getString("chss")+"\nСАД: "+intent.getString("sad")+"\nДАД: "+intent.getString("dad")+"\nЖЕЛ: "+intent.getString("zhel")+
                                "\nШтанге: "+intent.getString("shtange"), Toast.LENGTH_LONG);
                        toast.show();*/
                        startActivity(a);
                    }
                }
            });
        }
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent a = new Intent(IMT_Res.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(IMT_Res.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(IMT_Res.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}