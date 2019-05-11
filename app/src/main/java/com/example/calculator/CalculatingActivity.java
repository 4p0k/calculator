package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.calculator.CalculatorPackege.DefaultCalculator;
import com.example.calculator.CalculatorPackege.ExampleCollector;

import java.util.ArrayList;
import java.util.Random;

public class CalculatingActivity extends AppCompatActivity {



    ArrayList<String> arrLisSelectTypeSign = new ArrayList<>();       //хеш выбраных знаков
    ArrayList<Integer> arrLisNumberOfSign = new ArrayList<>();     //хеш количество знаков
    //boolean nohard = false;                             //хеш выбора сложности
    //ArrayList<String> arrLisNoHard = new ArrayList<>();    //создание дом листа для промижуточных знаков
    String exampletxts ,extxt;                          //пример и правельный ответ
    StringBuilder extxtbut = new StringBuilder ();      //ответ пользователя
    boolean minus = false, end = false;                 //поставил ли пользователь минус и конец даного примера
    TextView textView;
    ExampleCollector exampleCollector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculating);

        textView = findViewById(R.id.textView5);

        arrLisSelectTypeSign = getIntent().getStringArrayListExtra("listnum");
        arrLisNumberOfSign = getIntent().getIntegerArrayListExtra("listsig");

        exampleCollector = new DefaultCalculator(arrLisSelectTypeSign, arrLisNumberOfSign);

        random_example ();
    }


    public void random_example (){

        exampletxts = exampleCollector.getExampleBody();

        textView.setText(exampletxts);

        extxt = Integer.toString(exampleCollector.getExampleAnswer());
        extxtbut.setLength(0);
        end = false;
        minus = false;
    }

    public void sysbtn(View v){
        int id = v.getId();
        if(id == R.id.next) {
                random_example();
            //Expression expression = new Expression(3);
            //textView.setText(expression + " = " + expression.getAnswer());
        }else if (id == R.id.oknext){
            if (extxt.compareToIgnoreCase(extxtbut.toString()) == 0 || end) {
                random_example();
            }
        }else if (id == R.id.exwut){
            textView.setText(String.valueOf(exampletxts + extxt));
            end = true;
        }/*else if (id == R.id.exit){
            Intent intent = new Intent(this, MainActivity.class);
            ActivityCompat.finishAffinity(this);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            finish();
        }*/
    }

    public void numbtn(View v){
        if (!end) {
            int id = v.getId();
            if (id == R.id.btn0) {
                extxtbut.append(0);
            } else if (id == R.id.btn1) {
                extxtbut.append(1);
            } else if (id == R.id.btn2) {
                extxtbut.append(2);
            } else if (id == R.id.btn3) {
                extxtbut.append(3);
            } else if (id == R.id.btn4) {
                extxtbut.append(4);
            } else if (id == R.id.btn5) {
                extxtbut.append(5);
            } else if (id == R.id.btn6) {
                extxtbut.append(6);
            } else if (id == R.id.btn7) {
                extxtbut.append(7);
            } else if (id == R.id.btn8) {
                extxtbut.append(8);
            } else if (id == R.id.btn9) {
                extxtbut.append(9);
            } else if (id == R.id.minus) {
                if (!minus) {
                    extxtbut.insert(0, "-");
                    minus = true;
                } else {
                    extxtbut.deleteCharAt(0);
                    minus = false;
                }

            } else if (id == R.id.del && extxtbut.length() != 0) {
                if (!minus) {
                    extxtbut.deleteCharAt(extxtbut.length() - 1);
                } else {
                    if (extxtbut.length() == 1) {
                        extxtbut.deleteCharAt(extxtbut.length() - 1);
                        minus = false;
                    } else {
                        extxtbut.deleteCharAt(extxtbut.length() - 1);
                    }
                }
                //extxtbut.deleteCharAt(0);

            }
            textView.setText(String.valueOf(exampletxts + extxtbut));
        }
    }
    //Log.d("som",Integer.toString(extxtbut.length()));
}
