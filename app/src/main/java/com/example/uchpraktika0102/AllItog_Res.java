package com.example.uchpraktika0102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllItog_Res extends AppCompatActivity {
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
        setContentView(R.layout.activity_all_itog_res);
        setTitle("Итоги");
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

        double rost_double = Double.parseDouble(rost);
        double ves_double = Double.parseDouble(ves);
        int vozr_int = Integer.parseInt(vozr);
        int shags_int = Integer.parseInt(shags);
        int chss_int = Integer.parseInt(chss);
        int sad_int = Integer.parseInt(sad);
        int dad_int = Integer.parseInt(dad);
        int zhel_int = Integer.parseInt(zhel);
        int shtange_int = Integer.parseInt(shtange);


        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
        TextView imt = findViewById(R.id.imt);
        imt.setText(decimalFormat.format(ves_double/(rost_double*rost_double)));

        TextView zhizn = findViewById(R.id.zhizn);
        zhizn.setText(decimalFormat.format(zhel_int/ves_double));

        TextView vinoslivost = findViewById(R.id.vinoslivost);
        vinoslivost.setText(decimalFormat.format((chss_int*10)/(sad_int-dad_int)));

        TextView serdechnososud = findViewById(R.id.serdechnososud);
        serdechnososud.setText(decimalFormat.format((dad_int*sad_int)/100));

        TextView skibinski = findViewById(R.id.skibinski);
        skibinski.setText(decimalFormat.format(((zhel_int/100)*shtange_int)/chss_int));

        TextView kredo = findViewById(R.id.kredo);
        kredo.setText(decimalFormat.format(100*(1-(dad_int/chss_int))));

        TextView funcizmen = findViewById(R.id.funcizmen);
        funcizmen.setText(decimalFormat.format((0.011 * chss_int) + (0.014*sad_int) + (0.008 * dad_int) + (0.014*vozr_int) + (0.009*ves_double) - (0.009*(rost_double*100))-0.27));

        TextView activnost = findViewById(R.id.activnost);
        activnost.setText(String.valueOf(shags_int));

        Button buttomEnd = findViewById(R.id.buttomEnd);
        buttomEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AllItog_Res.this,MainMenu.class);
                startActivity(a);
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent a = new Intent(AllItog_Res.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.indexes:
                    a = new Intent(AllItog_Res.this,MainMenu.class);
                    startActivity(a);
                    break;
                case R.id.itog:
                    a = new Intent(AllItog_Res.this,Results.class);
                    startActivity(a);
                    break;
            }
            return true;
        }
    };
}