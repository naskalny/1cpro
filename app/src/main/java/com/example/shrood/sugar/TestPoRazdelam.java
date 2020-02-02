package com.example.shrood.sugar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.util.BitSet;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb;

public class TestPoRazdelam extends AppCompatActivity implements View.OnClickListener {

    TextView nomerVoprosa;
    TextView textVoprosa;
    TextView otvets;
    TextView textView4;
    Button next;
    int rand;
    Random random;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    int min;
    int max;
    int i = 0;
    int u = 0;
    RadioGroup radioGroup;
    int kolvo = 0;
    int razdel;
    Button buttonImg;
    TextView shetchick;
    TextView nmbr;
    int minute = 0;
    int secunde = 0;
    int jeka = 0;
    String[] kodStat = new String[160];
    String[] voprosStat = new String[160];

    int watafak = 0;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    int gg = 1;
    Spinner spinner;
    String one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen;
    Button prev;
    int n, m;
    int countSize;
    int[] arr;
    RadioButton otvet1;
    RadioButton otvet2;
    RadioButton otvet3;
    RadioButton otvet4;
    RadioButton otvet5;
    RadioButton otvet6;
    Button nazad;
    int arrSize;
    String[] yourAnswer;
    String[] rightAnswer;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu__voprosov);

        nomerVoprosa = (TextView) findViewById(R.id.nomerVoprosa);
        textVoprosa = (TextView) findViewById(R.id.vopros);
        textView4 = (TextView) findViewById(R.id.text4);
        next = (Button) findViewById(R.id.buttonNext);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        buttonImg = (Button) findViewById(R.id.buttonImage);
        shetchick = (TextView) findViewById(R.id.god);
        nmbr = (TextView) findViewById(R.id.number);
        textView10 = (TextView) findViewById(R.id.textStatKod);
        textView11 = (TextView) findViewById(R.id.textStatVopros);
        textView12 = (TextView) findViewById(R.id.textStatYourAnswer);
        textView13 = (TextView) findViewById(R.id.textStatRightAnswer);
        textView14 = (TextView) findViewById(R.id.textPlusMinus);
        prev = (Button) findViewById(R.id.buttonPrev);
        otvet1 = (RadioButton) findViewById(R.id.radioButton);
        otvet2 = (RadioButton) findViewById(R.id.radioButton2);
        otvet3 = (RadioButton) findViewById(R.id.radioButton3);
        otvet4 = (RadioButton) findViewById(R.id.radioButton4);
        otvet5 = (RadioButton) findViewById(R.id.radioButton5);
        otvet6 = (RadioButton) findViewById(R.id.radioButton6);
        nazad = (Button) findViewById(R.id.buttonPrev);

        String[] razdeli = {"1 Раздел", "2 Раздел", "3 Раздел", "4 Раздел", "5 Раздел", "6 Раздел", "7 Раздел", "8 Раздел", "9 Раздел", "10 Раздел", "11 Раздел", "12 Раздел", "13 Раздел", "14 Раздел",};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_adapter, R.id.textBlya, razdeli);
        adapter.setDropDownViewResource(R.layout.layout_adapter);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);
        nmbr.setVisibility(View.INVISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDBHelper = new DataBaseHelper(this);

        mDb = mDBHelper.getWritableDatabase();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);
                if (rb != null && checkedId > -1) {
                    // если такая кнопка есть и все нормально, то вызываем всплывающее окно
                    // с текстом радиокнопки
                    // Toast.makeText(MainActivity_Menu_Voprosov.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        setTitle("Тест по разделу");

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String) parent.getItemAtPosition(position);
                Toast toast2 = Toast.makeText(getApplicationContext(),
                        item, Toast.LENGTH_SHORT);
                toast2.show();
                switch (item) {
                    case "1 Раздел":
                        min = 0;
                        max = 71;
                        razdel = 1;
                        setTitle("Тест по разделу 1");
                        arrSize = 71;
                        break;
                    case "2 Раздел":
                        min = 71;
                        max = 140;
                        razdel = 2;
                        setTitle("Тест по разделу 2");
                        arrSize = 70;
                        break;
                    case "3 Раздел":
                        min = 140;
                        max = 210;
                        razdel = 3;
                        setTitle("Тест по разделу 3");
                        arrSize = 70;
                        break;
                    case "4 Раздел":
                        min = 210;
                        max = 280;
                        razdel = 4;
                        setTitle("Тест по разделу 4");
                        arrSize = 70;
                        break;
                    case "5 Раздел":
                        min = 280;
                        max = 354;
                        razdel = 5;
                        setTitle("Тест по разделу 5");
                        arrSize = 84;
                        break;
                    case "6 Раздел":
                        min = 354;
                        max = 438;
                        razdel = 6;
                        setTitle("Тест по разделу 6");
                        arrSize = 84;
                        break;
                    case "7 Раздел":
                        min = 438;
                        max = 486;
                        razdel = 7;
                        setTitle("Тест по разделу 7");
                        arrSize = 48;
                        break;
                    case "8 Раздел":
                        min = 486;
                        max = 540;
                        razdel = 8;
                        setTitle("Тест по разделу 8");
                        arrSize = 54;
                        break;
                    case "9 Раздел":
                        min = 540;
                        max = 607;
                        razdel = 9;
                        setTitle("Тест по разделу 9");
                        arrSize = 67;
                        break;
                    case "10 Раздел":
                        min = 607;
                        max = 737;
                        razdel = 10;
                        setTitle("Тест по разделу 10");
                        arrSize = 130;
                        break;
                    case "11 Раздел":
                        min = 737;
                        max = 785;
                        razdel = 11;
                        setTitle("Тест по разделу 11");
                        arrSize = 50;
                        break;
                    case "12 Раздел":
                        min = 785;
                        max = 865;
                        razdel = 12;
                        setTitle("Тест по разделу 12");
                        arrSize = 81;
                        break;
                    case "13 Раздел":
                        min = 865;
                        max = 930;
                        razdel = 13;
                        setTitle("Тест по разделу 13");
                        arrSize = 65;
                        break;
                    case "14 Раздел":
                        min = 930;
                        max = 987;
                        razdel = 14;
                        setTitle("Тест по разделу 14");
                        arrSize = 57;
                        break;
                }
                yourAnswer = new String[arrSize];
                rightAnswer = new String[arrSize];
                n = max-min;
                countSize = max-min;
                arr =new int [countSize];
                boolean alreadyThere;
                for (int k = 0; k < countSize;)
                {
                    alreadyThere = false;
                    Random radnom1 = new Random();
                    int newRandomValue = radnom1.nextInt(max- min)+min+1;
                    for (int j = 0; j<k; j++)
                    {
                        if (arr[j] == newRandomValue){
                            alreadyThere = true;
                            break;
                        }
                    }
                    if (!alreadyThere){
                        arr[k] = newRandomValue;
                        k++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


    }

    @Override
    public void onClick(View view) {
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        String answer = textView4.getText().toString().substring(0, 1);
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        spinner.setVisibility(View.INVISIBLE);
        String kod = "";
        String vopros = "";
        String varOtveta = "";
        String verniyOtver = "";
        String varOtveta1 = "";
        String varOtveta2 = "";
        String varOtveta3 = "";
        String varOtveta4 = "";
        String varOtveta5 = "";
        String varOtveta6 = "";
        String imageName = "";
        photoView.setVisibility(View.VISIBLE);

        switch (view.getId()) {
            case R.id.buttonNext:
//                if (i >0 && yourAnswer[i] != null ) {
//
//                        if (yourAnswer[i].toString().equals(rightAnswer[i].toString())) {
//                            kolvo++;
//                            //Toast.makeText(this,"Верно)", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                buttonImg.setText("Скрыть картинку");
                photoView.setVisibility(View.VISIBLE);
                photoView.setImageDrawable(null);
                next.setText("NEXT");
                radioGroup.setVisibility(View.VISIBLE);
                textVoprosa.setVisibility(View.VISIBLE);
                //otvets.setVisibility(View.VISIBLE);
                buttonImg.setVisibility(View.VISIBLE);
                nazad.setVisibility(View.VISIBLE);
                SQLiteOnWeb.init(this).start();
                SQLiteOnWeb.init(this, 9000).start();



                if (i < max - min-1) {

                    //Cursor cursor = mDb.rawQuery("select * from List1 where (_id > 1 and _id < 71 ) ORDER BY RANDOM() LIMIT 1", null);
                    //Cursor cursor = mDb.rawQuery("select * from List1 where (_id >" + min + " and _id <" + max + " ) ORDER BY RANDOM() LIMIT 1", null);
                    Cursor cursor = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + Integer.toString(arr[i]), null);

                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        kod += cursor.getString(1);
                        vopros += cursor.getString(2);
                        varOtveta += cursor.getString(3);
                        verniyOtver += cursor.getString(13);
                        varOtveta1 += cursor.getString(7);
                        varOtveta2 += cursor.getString(8);
                        varOtveta3 += cursor.getString(9);
                        varOtveta4 += cursor.getString(10);
                        varOtveta5 += cursor.getString(11);
                        varOtveta6 += cursor.getString(12);
                        imageName += cursor.getString(3);
                        cursor.moveToNext();
                    }
                    //Toast.makeText(this, verniyOtver, Toast.LENGTH_SHORT).show();

                    nmbr.setText(kod);
                    if (i>0) {
                        yourAnswer[i] = rb.getText().toString();
                        rightAnswer[i] = textView4.getText().toString();
                    }
                    i++;
                    cursor.close();
                    if (watafak < max - min) {
                        //String answer = textView4.getText().toString().substring(0, 1);
                        nomerVoprosa.setText(Integer.toString(i) + "/" + Integer.toString(max-min-1));
                        //otvets.setText(vopros);
                        textVoprosa.setText(vopros);
                        otvet1.setText(varOtveta1.replace(";", ")"));
                        otvet2.setText(varOtveta2.replace(";", ")"));
                        otvet3.setText(varOtveta3.replace(";", ")"));
                        otvet4.setText(varOtveta4.replace(";", ")"));
                        otvet5.setText(varOtveta5.replace(";", ")"));
                        otvet6.setText(varOtveta6.replace(";", ")"));
                        textView4.setText(verniyOtver.replace(";", ")"));
                        int drawableID = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                        photoView.setImageResource(drawableID);
                        photoView.invalidate();
                        makeVisible(varOtveta3, varOtveta4, varOtveta5, varOtveta6, otvet3, otvet4, otvet5, otvet6);
                        watafak++;
                    } else {
                        radioGroup.setVisibility(View.INVISIBLE);
                        nmbr.setVisibility(View.INVISIBLE);
                        shetchick.setVisibility(View.INVISIBLE);
                        buttonImg.setVisibility(View.INVISIBLE);
                        //otvets.setVisibility(View.INVISIBLE);
                        textVoprosa.setVisibility(View.INVISIBLE);
                        nomerVoprosa.setText("Нажмите завершить");
                        next.setText("Завершить");

                    }



                    //Запись в массив данных для статистики
//                kodStat[i] = kod;
//                voprosStat[i] = vopros;
//                rightAnswer[i] = verniyOtver;
//                yourAnswer[u] = rb.getText().toString();
                    u++;
                    //i++;
                    shetchick.setText(Integer.toString(kolvo));

                }

                else {
                    //Добавление в массив ответов последнего вопроса
                    yourAnswer[i] = rb.getText().toString();
                    rightAnswer[i] = textView4.getText().toString();
                    //
                    radioGroup.setVisibility(View.INVISIBLE);
                    nmbr.setVisibility(View.INVISIBLE);
                    shetchick.setVisibility(View.INVISIBLE);
                    buttonImg.setVisibility(View.INVISIBLE);
                    //otvets.setVisibility(View.INVISIBLE);
                    nomerVoprosa.setVisibility(View.INVISIBLE);
                    //otvets.setText(Integer.toString(kolvo) + " из " + Integer.toString(max-min-2));
                    int rez = kolvo*100/(max - min-2);
                    for (int j = 1; j < yourAnswer.length-2; j++) {

                        if (yourAnswer[j].length() > 0) {
                            if (yourAnswer[j].equals(rightAnswer[j])) {
                                kolvo++;
                            }
                        }
                    }
                    //if (Integer.toString(rez).substring(1,2).equals(2) || Integer.toString(rez).substring(1,2).equals(3) || Integer.toString(rez).substring(1,2).equals(4)){
                    //textVoprosa.setText("Вы выучили данный раздел на " + Integer.toString(rez)+ " %");
                    textVoprosa.setText("Вы выучили данный раздел на " + kolvo + " %");
                    prev.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.buttonImage:
                if(buttonImg.getText().toString().equals("Скрыть картинку")) {
                    photoView.setVisibility(View.INVISIBLE);
                    buttonImg.setText("Показать картинку");
                }
                else {
                    photoView.setVisibility(View.VISIBLE);
                    buttonImg.setText("Скрыть картинку");
                }
                break;
            case R.id.buttonPrev:
                i--;
                yourAnswer[Integer.parseInt(nmbr.getText().toString().substring(0,2))] = rb.getText().toString();
                rightAnswer[Integer.parseInt(nmbr.getText().toString().substring(0,2))] = textView4.getText().toString();
                Cursor cursor = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + Integer.toString(arr[i]), null);

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    kod += cursor.getString(1);
                    vopros += cursor.getString(2);
                    varOtveta += cursor.getString(3);
                    verniyOtver += cursor.getString(13);
                    varOtveta1 += cursor.getString(7);
                    varOtveta2 += cursor.getString(8);
                    varOtveta3 += cursor.getString(9);
                    varOtveta4 += cursor.getString(10);
                    varOtveta5 += cursor.getString(11);
                    varOtveta6 += cursor.getString(12);
                    imageName += cursor.getString(3);
                    cursor.moveToNext();
                }
                //Toast.makeText(this, verniyOtver, Toast.LENGTH_SHORT).show();
                nomerVoprosa.setText(Integer.toString(i) + "/" + Integer.toString(max-min-1));
                makeVisible(varOtveta3, varOtveta4, varOtveta5, varOtveta6, otvet3, otvet4, otvet5, otvet6);

                //otvets.setText(vopros);
                textVoprosa.setText(vopros);
                otvet1.setText(varOtveta1.replace(";", ")"));
                otvet2.setText(varOtveta2.replace(";", ")"));
                otvet3.setText(varOtveta3.replace(";", ")"));
                otvet4.setText(varOtveta4.replace(";", ")"));
                otvet5.setText(varOtveta5.replace(";", ")"));
                otvet6.setText(varOtveta6.replace(";", ")"));
                textView4.setText(verniyOtver.replace(";", ")"));
                int drawableID = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                photoView.setImageResource(drawableID);
                photoView.invalidate();
                nmbr.setText(kod);
                cursor.close();
                break;
        }
                }
    public static void makeVisible(String varOtveta3, String varOtveta4, String varOtveta5, String varOtveta6, TextView otvet3, TextView otvet4, TextView otvet5, TextView otvet6) {
        //Проверка на пустые ответы
        //
        //Если ответ пустой, то радио баттон скрывается
        //
        if (varOtveta3.equals("null") ) {otvet3.setVisibility(View.INVISIBLE);} else otvet3.setVisibility(View.VISIBLE);
        if (varOtveta4.equals("null") ) {otvet4.setVisibility(View.INVISIBLE);} else otvet4.setVisibility(View.VISIBLE);
        if (varOtveta5.equals("null") ) {otvet5.setVisibility(View.INVISIBLE);} else otvet5.setVisibility(View.VISIBLE);
        if (varOtveta6.isEmpty() ) {otvet6.setVisibility(View.INVISIBLE);} else otvet6.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                TestPoRazdelam.this);
        quitDialog.setTitle("Вы действительно хотите выйти?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        quitDialog.show();
    }
}



