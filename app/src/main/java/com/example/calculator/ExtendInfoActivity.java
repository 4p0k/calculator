package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.calculator.CalculatorPackege.SaveReadSettings;

public class ExtendInfoActivity extends AppCompatActivity {

    SaveReadSettings srs = new SaveReadSettings();
    CheckBox[] arrayCheckBox = new CheckBox[4];
    String sign = "add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_info);

        arrayCheckBox[0] = findViewById(R.id.checkBox1);
        arrayCheckBox[1] = findViewById(R.id.checkBox2);
        arrayCheckBox[2] = findViewById(R.id.checkBox3);
        arrayCheckBox[3] = findViewById(R.id.checkBox4);

        int typeSign = getIntent().getIntExtra("type", 1);

        switch (typeSign) {
            case 1:
                arrayCheckBox[0].setText("3 + 7");
                ((TextView) findViewById(R.id.textView1)).setText("Сложение в пределах однозначных чисел");
                arrayCheckBox[1].setText("55 + 77");
                ((TextView) findViewById(R.id.textView2)).setText("Сложение в пределах двузначных чисел");
                arrayCheckBox[2].setText("555 + 777");
                ((TextView) findViewById(R.id.textView3)).setText("Сложение в пределах трёхзначных чисел");
                arrayCheckBox[3].setText("5555 + 7777");
                ((TextView) findViewById(R.id.textView4)).setText("Сложение в пределах четырёхзначных чисел");
                sign = "add";
                break;
            case 2:
                arrayCheckBox[0].setText("7 − 5");
                ((TextView) findViewById(R.id.textView1)).setText("Вычитание в пределах однозначных чисел");
                arrayCheckBox[1].setText("77 − 55");
                ((TextView) findViewById(R.id.textView2)).setText("Вычитание в пределах двузначных чисел");
                arrayCheckBox[2].setText("777 − 555");
                ((TextView) findViewById(R.id.textView3)).setText("Вычитание в пределах трёхзначных чисел");
                arrayCheckBox[3].setText("7777 − 5555");
                ((TextView) findViewById(R.id.textView4)).setText("Вычитание в пределах четырёхзначных чисел");
                sign = "sub";
                break;
            case 3:
                arrayCheckBox[0].setText("5 * 5");
                ((TextView) findViewById(R.id.textView1)).setText("Умножение однозначных чисел");
                arrayCheckBox[1].setText("5 * 25");
                ((TextView) findViewById(R.id.textView2)).setText("Умножение двузначного числа на однозначное");
                arrayCheckBox[2].setText("5 * 525");
                ((TextView) findViewById(R.id.textView3)).setText("Умножение трёхзначного числа на однозначное");
                arrayCheckBox[3].setText("25 * 25");
                ((TextView) findViewById(R.id.textView4)).setText("Умножение двузначных чисел");
                sign = "mul";
                break;
            case 4:
                arrayCheckBox[0].setText("25 : 5");
                ((TextView) findViewById(R.id.textView1)).setText("Деление двузначного числа на однозначное");
                arrayCheckBox[1].setText("275 : 5");
                ((TextView) findViewById(R.id.textView2)).setText("Деление трёхзначного числа на однозначное");
                arrayCheckBox[2].setText("2775 : 5");
                ((TextView) findViewById(R.id.textView3)).setText("Деление четырёхзначного числа на однозначное");
                arrayCheckBox[3].setText("3025 : 25");
                ((TextView) findViewById(R.id.textView4)).setText("Деление четырёхзначного числа на двузначное");
                sign = "div";
                break;
            case 5:
                arrayCheckBox[0].setText("25 : 5");
                ((TextView) findViewById(R.id.textView1)).setText("Деление двузначного числа на однозначное");
                arrayCheckBox[1].setText("275 : 5");
                ((TextView) findViewById(R.id.textView2)).setText("Деление трёхзначного числа на однозначное");
                arrayCheckBox[2].setText("2775 : 5");
                ((TextView) findViewById(R.id.textView3)).setText("Деление четырёхзначного числа на однозначное");
                arrayCheckBox[3].setText("3025 : 25");
                ((TextView) findViewById(R.id.textView4)).setText("Деление четырёхзначного числа на двузначное");
                sign = "num";
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Boolean arraySetCheckBox[] = srs.loadSetCheckBoxArray(sign, arrayCheckBox.length, this);

        for (int i = 0; i < arrayCheckBox.length; i++) {
            if (arraySetCheckBox[i]) {
                arrayCheckBox[i].setChecked(true);
            }
        }
    }

    boolean saveDone = false;
    public void saveSetCheckBox() {
        if (!saveDone) {
            Boolean[] arraySetCheckBox = new Boolean[arrayCheckBox.length];

            for (int i = 0; i < arrayCheckBox.length; i++) {
                arraySetCheckBox[i] = arrayCheckBox[i].isChecked();
            }
            srs.saveSetCheckBoxArray(sign, arraySetCheckBox, this);

            saveDone = true;
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        saveSetCheckBox();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        saveSetCheckBox();
        super.onDestroy();
    }

}
