package com.example.shrood.sugar;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.orm.dsl.Table;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb;

public class MainActivity_Menu_Voprosov extends AppCompatActivity implements View.OnClickListener {
    TextView nomerVoprosa;
    TextView textVoprosa;
    TextView textView4;
    Button next;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    int min;
    int max;
    int i = 1;
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
    String[] kodStat = new String[16];
    String[] voprosStat = new String[16];
    String[] yourAnswer = new String[15];
    String[] rightAnswer = new String[15];
    int watafak = 0;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    Button nazad;
    int gg = 1;
    String one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen;
    int[] arr = new int[16];
    TableRow tablerow;
    TableLayout tableLayout;
    String fkf = " ";
    RadioButton otvet1;
    RadioButton otvet2;
    RadioButton otvet3;
    RadioButton otvet4;
    RadioButton otvet5;
    RadioButton otvet6;
    int currentQuestion;
    CountDownTimer timer;
    private static long back_pressed;
    SharedPref sharedPref;
    Boolean isTimeOver = false;
    PhotoView photoView;
    TextView valueT;

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
        //textView10 = (TextView) findViewById(R.id.textStatKod);
//        textView11 = (TextView) findViewById(R.id.textStatVopros);
//        textView12 = (TextView) findViewById(R.id.textStatYourAnswer);
//        textView13 = (TextView) findViewById(R.id.textStatRightAnswer);
//        textView14 = (TextView) findViewById(R.id.textPlusMinus);
        nazad = (Button) findViewById(R.id.buttonPrev);
        otvet1 = (RadioButton) findViewById(R.id.radioButton);
        otvet2 = (RadioButton) findViewById(R.id.radioButton2);
        otvet3 = (RadioButton) findViewById(R.id.radioButton3);
        otvet4 = (RadioButton) findViewById(R.id.radioButton4);
        otvet5 = (RadioButton) findViewById(R.id.radioButton5);
        otvet6 = (RadioButton) findViewById(R.id.radioButton6);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //final PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        photoView = (PhotoView) findViewById(R.id.photo_view);

        mDBHelper = new DataBaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
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
        //Запуск таймера
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                //textTimer.setText(" "+l/1000);
                setTitle("Время: " + minute + ":" + secunde);
                secunde++;
            }

            @Override
            public void onFinish() {
                if (minute <29) {
                    minute++;
                    this.start();
                    secunde = 0;
                } else {
                    this.cancel();
                    setTitle("Время вышло");
                    nmbr.setVisibility(View.INVISIBLE);
                    shetchick.setVisibility(View.INVISIBLE);
                    buttonImg.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    nomerVoprosa.setText("Время вышло(");
                    textVoprosa.setVisibility(View.INVISIBLE);
                    nazad.setVisibility(View.INVISIBLE);
                    isTimeOver = true;
                    toolbar.setVisibility(View.INVISIBLE);
                    photoView.setVisibility(View.INVISIBLE);
                }
            }
        };
        Random random = new Random();

        for (int lol = 1; lol <= 14; ) {
            switch (lol) {
                case 1:
                    min = 1;
                    max = 71;
                    razdel = 1;
                    break;
                case 2:
                    min = 72;
                    max = 139;
                    razdel = 2;
                    break;
                case 3:
                    min = 140;
                    max = 210;
                    razdel = 3;
                    break;
                case 4:
                    min = 210;
                    max = 280;
                    razdel = 4;
                    break;
                case 5:
                    min = 280;
                    max = 354;
                    razdel = 5;
                    break;
                case 6:
                    min = 354;
                    max = 438;
                    razdel = 6;
                    break;
                case 7:
                    min = 438;
                    max = 486;
                    razdel = 7;
                    break;
                case 8:
                    min = 486;
                    max = 540;
                    razdel = 8;
                    break;
                case 9:
                    min = 540;
                    max = 607;
                    razdel = 9;
                    break;
                case 10:
                    min = 607;
                    max = 737;
                    razdel = 10;
                    break;
                case 11:
                    min = 737;
                    max = 785;
                    razdel = 11;
                    break;
                case 12:
                    min = 785;
                    max = 865;
                    razdel = 12;
                    break;
                case 13:
                    min = 865;
                    max = 930;
                    razdel = 13;
                    break;
                case 14:
                    min = 930;
                    max = 987;
                    razdel = 14;
                    break;
            }
            int randomValue = random.nextInt(max - min) + min + 1;
            arr[lol] = randomValue;
            lol++;
        }
        currentQuestion = 0;
        boolean df = false;
        String answer = textView4.getText().toString().substring(0, 1);
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

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
        currentQuestion++;
        buttonImg.setText("Скрыть картинку");
        photoView.setVisibility(View.VISIBLE);
        textVoprosa.setVisibility(View.VISIBLE);
        nazad.setVisibility(View.VISIBLE);
        photoView.setImageDrawable(null);
        next.setText("NEXT");
        if (jeka == 0) {
            timer.start();
            jeka = 1;
        }
        radioGroup.setVisibility(View.VISIBLE);
        //textVoprosa.setVisibility(View.VISIBLE);
        //.setVisibility(View.VISIBLE);
        buttonImg.setVisibility(View.VISIBLE);

        //Toast.makeText(this, verniyOtver, Toast.LENGTH_SHORT).show();

        SQLiteOnWeb.init(this).start();
        SQLiteOnWeb.init(this, 9000).start();
        Cursor cursor = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + arr[currentQuestion], null);
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
        cursor.close();

        //rightAnswer[currentQuestion] = verniyOtver;
        textVoprosa.setText(vopros);
//        for (int iterator = 0; iterator <=6; iterator++){
//            makeVisible(iterator, varOtveta1, varOtveta2, varOtveta3, varOtveta4, varOtveta5, varOtveta6);
//        }

        otvet1.setText(varOtveta1.replace(";", ")"));
        otvet2.setText(varOtveta2.replace(";", ")"));
        otvet3.setText(varOtveta3.replace(";", ")"));
        otvet4.setText(varOtveta4.replace(";", ")"));
        otvet5.setText(varOtveta5.replace(";", ")"));
        otvet6.setText(varOtveta6.replace(";", ")"));
        textView4.setText(verniyOtver.replace(";", ")"));

        makeVisible(varOtveta3, varOtveta4, varOtveta5, varOtveta6, otvet3, otvet4, otvet5, otvet6);

        int drawableID = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
        photoView.setImageResource(drawableID);
        photoView.invalidate();

        nmbr.setText(Integer.toString(Integer.parseInt(kod.substring(0,2))));
        for (int iterator = 1; iterator < 15; iterator++) {
            yourAnswer[iterator] = "7";
            rightAnswer[iterator] = "8";
        }
        if (Integer.parseInt(nmbr.getText().toString()) == 1) {
            nazad.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            openQuitDialog(item);
        }

        return false;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isTimeOver) {
            Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        boolean df = false;
        String answer = textView4.getText().toString().substring(0, 1);
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

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
        switch (view.getId()) {
            case R.id.buttonNext:
                yourAnswer[Integer.parseInt(nmbr.getText().toString())] = rb.getText().toString();
                rightAnswer[Integer.parseInt(nmbr.getText().toString())] = textView4.getText().toString();
                if (currentQuestion < 14)
                {
                currentQuestion++;
                buttonImg.setText("Скрыть картинку");
                photoView.setVisibility(View.VISIBLE);
                textVoprosa.setVisibility(View.VISIBLE);
                nazad.setVisibility(View.VISIBLE);
                photoView.setImageDrawable(null);
                next.setText("NEXT");
                radioGroup.setVisibility(View.VISIBLE);
                //textVoprosa.setVisibility(View.VISIBLE);
                //.setVisibility(View.VISIBLE);
                buttonImg.setVisibility(View.VISIBLE);
                SQLiteOnWeb.init(this).start();
                SQLiteOnWeb.init(this, 9000).start();

                //Cursor cursor = mDb.rawQuery("select * from List1 where (_id > 1 and _id < 71 ) ORDER BY RANDOM() LIMIT 1", null);
                //Cursor cursor = mDb.rawQuery("select * from List1 where (_id >" + min + " and _id <" + max + " ) ORDER BY RANDOM() LIMIT 1", null);
                //Cursor cursor = mDb.rawQuery("select * from List1 where _id =" + arr[i], null);

                    Cursor cursor = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + arr[currentQuestion], null);
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

                    textVoprosa.setText(vopros);
                    otvet1.setText(varOtveta1.replace(";", ")"));
                    otvet2.setText(varOtveta2.replace(";", ")"));
                    otvet3.setText(varOtveta3.replace(";", ")"));
                    otvet4.setText(varOtveta4.replace(";", ")"));
                    otvet5.setText(varOtveta5.replace(";", ")"));
                    otvet6.setText(varOtveta6.replace(";", ")"));
                    textView4.setText(verniyOtver.replace(";", ")"));

                    makeVisible(varOtveta3, varOtveta4, varOtveta5, varOtveta6, otvet3, otvet4, otvet5, otvet6);

                    int drawableID = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                    photoView.setImageResource(drawableID);
                    photoView.invalidate();

                    nmbr.setText(Integer.toString(Integer.parseInt(kod.substring(0,2))));
                    cursor.close();
                    if (Integer.parseInt(nmbr.getText().toString()) == 14) {
                        next.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.buttonImage:
                if (buttonImg.getText().toString().equals("Скрыть картинку")) {
                    photoView.setVisibility(View.INVISIBLE);
                    buttonImg.setText("Показать картинку");
                } else {
                    photoView.setVisibility(View.VISIBLE);
                    buttonImg.setText("Скрыть картинку");
                }
                break;
            case R.id.buttonPrev:
                next.setVisibility(View.VISIBLE);
                yourAnswer[Integer.parseInt(nmbr.getText().toString())] = rb.getText().toString();
                rightAnswer[Integer.parseInt(nmbr.getText().toString())] = textView4.getText().toString();
                currentQuestion--;
                Cursor cursor2 = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + arr[currentQuestion], null);
                cursor2.moveToFirst();
                while (!cursor2.isAfterLast()) {
                    kod += cursor2.getString(1);
                    vopros += cursor2.getString(2);
                    varOtveta += cursor2.getString(3);
                    verniyOtver += cursor2.getString(13);
                    varOtveta1 += cursor2.getString(7);
                    varOtveta2 += cursor2.getString(8);
                    varOtveta3 += cursor2.getString(9);
                    varOtveta4 += cursor2.getString(10);
                    varOtveta5 += cursor2.getString(11);
                    varOtveta6 += cursor2.getString(12);
                    imageName += cursor2.getString(3);
                    cursor2.moveToNext();
                }

                textVoprosa.setText(vopros);
                otvet1.setText(varOtveta1.replace(";", ")"));
                otvet2.setText(varOtveta2.replace(";", ")"));
                otvet3.setText(varOtveta3.replace(";", ")"));
                otvet4.setText(varOtveta4.replace(";", ")"));
                otvet5.setText(varOtveta5.replace(";", ")"));
                otvet6.setText(varOtveta6.replace(";", ")"));
                textView4.setText(verniyOtver.replace(";", ")"));


                makeVisible(varOtveta3, varOtveta4, varOtveta5, varOtveta6, otvet3, otvet4, otvet5, otvet6);

                int drawableID2 = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                photoView.setImageResource(drawableID2);
                photoView.invalidate();
                nmbr.setText(Integer.toString(Integer.parseInt(kod.substring(0,2))));
                cursor2.close();
                if (Integer.parseInt(nmbr.getText().toString()) == 1) {
                    nazad.setVisibility(View.INVISIBLE);
                }
                break;
        }

    }

    //Проверка на пустые ответы
    //
    //Если ответ пустой, то радио баттон скрывается
    //
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
    //Выслывающее окно, не дающее выйти если нажать кнопку назад
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //openQuitDialog();
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    private void openQuitDialog(final MenuItem item) {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity_Menu_Voprosov.this);
        quitDialog.setTitle("Вы действительно хотите завершить тестирование?");
        //item.setVisible(false);

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
                radioGroup.setVisibility(View.INVISIBLE);
            RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
            String[] wrongAnswers = new String[16];
            yourAnswer[Integer.parseInt(nmbr.getText().toString())] = rb.getText().toString();
            rightAnswer[Integer.parseInt(nmbr.getText().toString())] = textView4.getText().toString();
            for (int j = 1; j < yourAnswer.length; j++) {

                if (yourAnswer[j].length() > 0) {
                    if (yourAnswer[j].equals(rightAnswer[j])) {
                        watafak++;
                    }
                    else wrongAnswers[j] = yourAnswer[j];
                }
            }
            timer.cancel();
            setTitle("Итоги");
            nmbr.setText("Вы набрали");
            nmbr.setTextSize(20);
            if (watafak >=12) {
                textVoprosa.setText(watafak + " /14 балла \nВремя прохождения теста: " + minute + ":" + secunde + "\nТест сдан. Поздравляем!");
            }else {
                textVoprosa.setText(watafak + " /14 балла \nВремя прохождения теста: " + minute + ":" + secunde + "\nТест не сдан");

            }
            textVoprosa.setTextSize(20);


            ///////////////



            buttonImg.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
            nazad.setVisibility(View.INVISIBLE);
            photoView.setVisibility(View.INVISIBLE);
                item.setVisible(false);
                textView11.setVisibility(View.VISIBLE);
            for (Integer au = 0; au<wrongAnswers.length; au++) {
                textView11.setText(wrongAnswers[au]);
            }

            //////////////////////////////
                // при создании объекта View всегда передаем контекст в которм мы его создаем
                //TableLayout containerTableLayout = new TableLayout(getApplicationContext());
                tableLayout = (TableLayout) findViewById(R.id.tableLayout);
                // создаем три растянутые на всю ширину строки
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
                TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);

                tableLayout.addView(tr); //добавляем созданную строку в таблицу




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




