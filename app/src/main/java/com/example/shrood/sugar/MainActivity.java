package com.example.shrood.sugar;

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;
    TextView textView2;
    TextView otvet1;
    String currentQuestion;
    TextView textView4;
    Button button2;
    Button prev;
    int rand;
    Random random;
    int min;
    int max;
    int razdel;
    PhotoView photoView;
    Button hideImg;


    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    String[] razdeli = {"1 Раздел", "2 Раздел", "3 Раздел", "4 Раздел", "5 Раздел", "6 Раздел", "7 Раздел", "8 Раздел", "9 Раздел", "10 Раздел", "11 Раздел", "12 Раздел", "13 Раздел", "14 Раздел",};
    Spinner spinner;
    SharedPref sharedPref;
    TextView textBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textView2 = (TextView) findViewById(R.id.text2);
        otvet1 = (TextView) findViewById(R.id.textOtvet1);
        textBegin = (TextView) findViewById(R.id.textSticker);

        //Подсказка в начале
        textBegin.setVisibility(View.VISIBLE);
        textBegin.setText("Выберите раздел");

        textView4 = (TextView) findViewById(R.id.text4);
        prev = (Button) findViewById(R.id.buttonPrev);
        hideImg =(Button) findViewById(R.id.buttonHideImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Вопросы");


        spinner = (Spinner) findViewById(R.id.spinner2);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, razdeli);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_adapter, R.id.textBlya, razdeli);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.layout_adapter);
        spinner.setAdapter(adapter);

        mDBHelper = new DataBaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        //photoView.setImageResource(R.drawable.i2_24);



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
                        break;
                    case "2 Раздел":
                        min = 71;
                        max = 140;
                        razdel = 2;
                        break;
                    case "3 Раздел":
                        min = 140;
                        max = 210;
                        razdel = 3;
                        break;
                    case "4 Раздел":
                        min = 210;
                        max = 280;
                        razdel = 4;
                        break;
                    case "5 Раздел":
                        min = 280;
                        max = 354;
                        razdel = 5;
                        break;
                    case "6 Раздел":
                        min = 354;
                        max = 438;
                        razdel = 6;
                        break;
                    case "7 Раздел":
                        min = 438;
                        max = 486;
                        razdel = 7;
                        break;
                    case "8 Раздел":
                        min = 486;
                        max = 540;
                        razdel = 8;
                        break;
                    case "9 Раздел":
                        min = 540;
                        max = 607;
                        razdel = 9;
                        break;
                    case "10 Раздел":
                        min = 607;
                        max = 737;
                        razdel = 10;
                        break;
                    case "11 Раздел":
                        min = 737;
                        max = 785;
                        razdel = 11;
                        break;
                    case "12 Раздел":
                        min = 785;
                        max = 865;
                        razdel = 12;
                        break;
                    case "13 Раздел":
                        min = 865;
                        max = 930;
                        razdel = 13;
                        break;
                    case "14 Раздел":
                        min = 930;
                        max = 987;
                        razdel = 14;
                        break;
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
        prev.setText("Next");
        button.setText("Prev");
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
//        otvet2.setVisibility(View.VISIBLE);
//        otvet3.setVisibility(View.VISIBLE);
//        otvet4.setVisibility(View.VISIBLE);
//        otvet5.setVisibility(View.VISIBLE);
//        otvet6.setVisibility(View.VISIBLE);

        SQLiteOnWeb.init(this).start();
        SQLiteOnWeb.init(this, 9000).start();
        String kod = "";
        String vopros = "";
        String varOtveta1 = "";
        String imageName = "";

        String verniyOtver = "";

        final Random random = new Random();
        rand = random.nextInt(987);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        //photoView.setImageDrawable(null);


        switch (view.getId()) {
            case R.id.buttonPrev:
                textView2.setVisibility(View.VISIBLE);
                otvet1.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.INVISIBLE);
                textBegin.setVisibility(View.INVISIBLE);
                hideImg.setVisibility(View.VISIBLE);
                hideImg.setText("Скрыть");
                photoView.setImageDrawable(null);
                min++;
                RelativeLayout mainL = (RelativeLayout) findViewById(R.id.RelativeLayout1);
                RelativeLayout.LayoutParams viewParams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                TextView txtV = new TextView(this);


                //Cursor cursor = mDb.rawQuery("SELECT * FROM List1 where _id=" + min, null);
                Cursor cursor = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id = " + min, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    kod += cursor.getString(1);
                    vopros += cursor.getString(2);
                    imageName += cursor.getString(3);
                    varOtveta1 += cursor.getString(14).replace(";", ")");
                    verniyOtver += cursor.getString(13);
                    currentQuestion = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
                textView.setText(kod);
                textView2.setText(vopros);

                otvet1.setText(varOtveta1);
                int drawableID = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                photoView.setImageResource(drawableID);
                photoView.invalidate();
                textView4.setText(verniyOtver);
                hideButtons(currentQuestion, button, prev);
                break;
            case R.id.button2:
                Toast toast = Toast.makeText(getApplicationContext(),
                        textView4.getText(), Toast.LENGTH_SHORT);
                toast.show();
                break;
            //Назад
            case R.id.button:
                button.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);
                hideImg.setText("Скрыть");
                photoView.setImageDrawable(null);
                int cureentId = min--;
                Cursor cursor2 = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id = " + min, null);
                cursor2.moveToFirst();
                while (!cursor2.isAfterLast()) {
                    kod += cursor2.getString(1);
                    vopros += cursor2.getString(2);
                    imageName += cursor2.getString(3);
                    varOtveta1 += cursor2.getString(14).replace(";", ")");
                    verniyOtver += cursor2.getString(13);
                    currentQuestion = cursor2.getString(0);
                    cursor2.moveToNext();
                }
                cursor2.close();

                textView.setText(kod);
                textView2.setText(vopros);
                otvet1.setText(varOtveta1);
                int drawableID2 = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                photoView.setImageResource(drawableID2);
                photoView.invalidate();
                textView4.setText(verniyOtver);
                checkHaveImage(kod, razdel);
                hideImg.setText("Скрыть");
                hideButtons(currentQuestion, button, prev);
                break;


        case R.id.buttonHideImage:
            if (hideImg.getText().toString().equals("Скрыть")) {
                photoView.setVisibility(View.INVISIBLE);
                hideImg.setText("Показать");
            } else {
                photoView.setVisibility(View.VISIBLE);
                hideImg.setText("Скрыть");
            }
            break;
        }
    }

    public static void checkHaveImage(String kod, int razdel) {

    }

    //Скрывает кнопки на границах БД
    public static void hideButtons(String currentQuestion, Button button1, Button button2) {
        if (Integer.parseInt(currentQuestion) == 1) {
            button1.setVisibility(View.INVISIBLE);
        }
        else if(Integer.parseInt(currentQuestion) == 987) {
            button2.setVisibility(View.INVISIBLE);
        }
    }
}
