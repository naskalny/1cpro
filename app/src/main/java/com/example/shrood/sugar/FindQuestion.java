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

public class FindQuestion extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;
    TextView textView2;
    TextView otvet1;
    TextView otvet2;
    TextView otvet3;
    TextView otvet4;
    TextView otvet5;
    TextView otvet6;

    TextView textView4;
    Button button2;
    Button prev;
    int screenWidth;
    int screenHeight;
    int rand;
    Random random;
    String currentQuestion;
    PhotoView photoView;
    Button hideImg;
    Button find;
    TextView sticker;
    EditText editText;
    Exception exception;

    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    String[] razdeli = {"1 Раздел", "2 Раздел", "3 Раздел", "4 Раздел", "5 Раздел", "6 Раздел", "7 Раздел", "8 Раздел", "9 Раздел", "10 Раздел", "11 Раздел", "12 Раздел", "13 Раздел", "14 Раздел",};
    SharedPref sharedPref;
    TextView textBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textView2 = (TextView) findViewById(R.id.text2);
        otvet1 = (TextView) findViewById(R.id.textOtvet1);
        textBegin = (TextView) findViewById(R.id.textSticker);
        find = (Button) findViewById(R.id.buttonFind);
        sticker = (TextView) findViewById(R.id.textSticker);
        editText = (EditText) findViewById(R.id.editText);

        //Подсказка в начале
        textBegin.setVisibility(View.VISIBLE);
        textBegin.setText("Введите номер вопроса. Например 01.01");

        textView4 = (TextView) findViewById(R.id.text4);
        prev = (Button) findViewById(R.id.buttonPrev);
        hideImg = (Button) findViewById(R.id.buttonHideImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Поиск вопроса");

        find.setVisibility(View.VISIBLE);
        sticker.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);
        prev.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);

        mDBHelper = new DataBaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        //photoView.setImageResource(R.drawable.i2_24);
    }


    @Override
    public void onClick(View view) {
        prev.setText("Next");
        button.setText("Prev");
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);


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
            case R.id.buttonFind:
                textView2.setVisibility(View.VISIBLE);
                otvet1.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textBegin.setVisibility(View.INVISIBLE);
                hideImg.setVisibility(View.VISIBLE);
                hideImg.setText("Скрыть");
                editText.setVisibility(View.INVISIBLE);
                find.setVisibility(View.INVISIBLE);
                photoView.setImageDrawable(null);
                RelativeLayout mainL = (RelativeLayout) findViewById(R.id.RelativeLayout1);
                RelativeLayout.LayoutParams viewParams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                TextView txtV = new TextView(this);

                try {
                    Cursor cursor = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions.kodVoprosa =" + "'" + editText.getText().toString() + "'", null);
                    cursor.moveToFirst();


                while (!cursor.isAfterLast()) {
                    kod += cursor.getString(1);
                    vopros += cursor.getString(2);
                    imageName += cursor.getString(3);
                    varOtveta1 += cursor.getString(14);
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
                }
                catch (Exception exeption){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Такого вопроса не существует! \n Максимально допустимый вопрос - 14.57", Toast.LENGTH_SHORT);
                    toast.show();
                    textBegin.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    find.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    editText.setText("");
                }

                break;
            case R.id.buttonPrev:
                int intCurrentQuestion = Integer.parseInt(currentQuestion);
                intCurrentQuestion++;
                textView2.setVisibility(View.VISIBLE);
                otvet1.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textBegin.setVisibility(View.INVISIBLE);
                hideImg.setVisibility(View.VISIBLE);
                hideImg.setText("Скрыть");
                photoView.setImageDrawable(null);


                Cursor cursor1 = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + intCurrentQuestion , null);
                cursor1.moveToFirst();
                while (!cursor1.isAfterLast()) {
                    kod += cursor1.getString(1);
                    vopros += cursor1.getString(2);
                    imageName += cursor1.getString(3);
                    varOtveta1 += cursor1.getString(14);
                    verniyOtver += cursor1.getString(13);
                    currentQuestion = cursor1.getString(0);
                    cursor1.moveToNext();
                }
                cursor1.close();
                textView.setText(kod);
                textView2.setText(vopros);
                otvet1.setText(varOtveta1);

                int drawableID2 = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                photoView.setImageResource(drawableID2);
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
                intCurrentQuestion = Integer.parseInt(currentQuestion);
                intCurrentQuestion--;
                hideImg.setText("Скрыть");
                prev.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                photoView.setImageDrawable(null);
                Cursor cursor2 = mDb.rawQuery("SELECT * FROM Questions inner join Answers on Questions._id = Answers._id where Questions._id =" + intCurrentQuestion, null);
                cursor2.moveToFirst();
                while (!cursor2.isAfterLast()) {
                    kod += cursor2.getString(1);
                    vopros += cursor2.getString(2);
                    imageName += cursor2.getString(3);
                    varOtveta1 += cursor2.getString(14);
                    verniyOtver += cursor2.getString(13);
                    currentQuestion = cursor2.getString(0);
                    cursor2.moveToNext();
                }
                cursor2.close();

                textView.setText(kod);
                textView2.setText(vopros);
                otvet1.setText(varOtveta1);
                int drawableID3 = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
                photoView.setImageResource(drawableID3);
                photoView.invalidate();
                textView4.setText(verniyOtver);

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
