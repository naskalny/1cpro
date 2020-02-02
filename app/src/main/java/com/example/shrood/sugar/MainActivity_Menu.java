package com.example.shrood.sugar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity_Menu extends AppCompatActivity implements View.OnClickListener{
    Button button;
    FloatingActionButton fb;
    Switch mySwitch;
    Boolean tema = false;
    SharedPref sharedPref;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        sharedPref = new SharedPref(this);
            if (sharedPref.loadNightModeState() == true) {
                setTheme(R.style.DarkTheme);
            } else {
                setTheme(R.style.AppTheme_NoActionBar);
            }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.button1);
        fb = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        setTitle("Тесты Профессионал 1С");

        mySwitch = findViewById(R.id.switch1);
        if (sharedPref.loadNightModeState() == true) {
            mySwitch.setChecked(true);
        }
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                        sharedPref.setNightModStats(true);
                        setTheme(R.style.DarkTheme);
                        restartApp();
                }
                else {
                    sharedPref.setNightModStats(false);
                    restartApp();
                }

            }
            private void restartApp() {
                Intent i = new Intent(getApplicationContext(), MainActivity_Menu.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent  = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void  onClick3(View view) {
        Intent intent2  = new Intent(this, MainActivity_Menu_Voprosov.class);
        startActivity(intent2);
    }

    public void  onClick4(View view) {
        Intent intent3  = new Intent(this, FindQuestion.class);
        startActivity(intent3);
    }

    public void  onClick5(View view) {
        Intent intent3  = new Intent(this, TestPoRazdelam.class);
        startActivity(intent3);
    }

}
