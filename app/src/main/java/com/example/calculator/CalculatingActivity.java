package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class CalculatingActivity extends AppCompatActivity {



    ArrayList<String> arrLisSelectTypeSign = new ArrayList<>();       //хеш выбраных знаков
    ArrayList<Integer> arrLisNumberOfSign = new ArrayList<>();     //хеш количество знаков
    //boolean nohard = false;                             //хеш выбора сложности
    ArrayList<String> arrLisNoHard = new ArrayList<>();    //создание дом листа для промижуточных знаков
    String exampletxts ,extxt;                          //пример и правельный ответ
    StringBuilder extxtbut = new StringBuilder ();      //ответ пользователя
    boolean minus = false, end = false;                 //поставил ли пользователь минус и конец даного примера
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculating);

        arrLisSelectTypeSign = getIntent().getStringArrayListExtra("listnum");
        arrLisNumberOfSign = getIntent().getIntegerArrayListExtra("listsig");
        //nohard = getIntent().getExtras().getBoolean("mult");

        for (int i = 0; 2 >= Character.getNumericValue(arrLisSelectTypeSign.get( i ).charAt(0)); i++) {
            arrLisNoHard.add(arrLisSelectTypeSign.get(i));
        }
        if (arrLisNoHard.size() == 0) {
            arrLisNoHard.add("122");
            arrLisNoHard.add("222");
        }
        random_example ();
    }

    public int getOneRand (int number){          //рандомное числа нужного деапозона
        int ten = 1;

        for (int i = 1; i < number; i++){
            ten *= 10;
        }

        return (int)Math.floor((ten + 1) + Math.random() * ((9 * ten) - 1));
    }


    StringBuilder oneSignText = new StringBuilder ();
    int oneSignInt;
    public void get2Sign (int sign,int nam1,int nam2) {        //создание одного примера
        oneSignInt = 0;
        oneSignText.setLength(0);

        int n1 = getOneRand(nam1);
        int n2 = getOneRand(nam2);
        if (sign == 1) {
            oneSignInt = n1 + n2;
            oneSignText.append(n1).append(" + ").append(n2);
        } else if (sign == 2) {
            oneSignInt = n1 - n2;
            oneSignText.append(n1).append(" - ").append(n2);
        } else if (sign == 3) {
            oneSignInt = n1 * n2;
            oneSignText.append(n1).append(" * ").append(n2);
        }else {
            int n3 = n1 * n2;
            oneSignInt = n2;
            oneSignText.append(n3).append(" : ").append(n1);
        }
    }

    public void random_example (){
        StringBuilder exampletxt = new StringBuilder ();                        //сбор примера
        int example = 0;                                                        //ответ
        int numsign = arrLisNumberOfSign.get( random.nextInt(arrLisNumberOfSign.size()) );    //выбор количество знаков
        String num;                                                             //код для создание одного примара 111 -> 2+3
        //String[] extmas;

        num = arrLisSelectTypeSign.get( random.nextInt(arrLisSelectTypeSign.size()) );

        get2Sign(  Character.getNumericValue(num.charAt(0)),
                    Character.getNumericValue(num.charAt(1)),
                    Character.getNumericValue(num.charAt(2)) );

        example = oneSignInt;
        if (numsign == 1) {
            exampletxt.append(oneSignText);
        }else {
            exampletxt.append("( ").append(oneSignText).append(" )");
        }

        for (int i = 3; i <= numsign; i += 2) {
            num = arrLisSelectTypeSign.get(random.nextInt(arrLisSelectTypeSign.size()));

            get2Sign(  Character.getNumericValue(num.charAt(0)),
                        Character.getNumericValue(num.charAt(1)),
                        Character.getNumericValue(num.charAt(2)) );

            num = arrLisNoHard.get(random.nextInt(arrLisNoHard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                example -= oneSignInt;
                exampletxt.append(" - ");
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                example *= oneSignInt;
                exampletxt.append(" * ");
            } else {
                example += oneSignInt;
                exampletxt.append(" + ");
            }

            exampletxt.append("( ").append(oneSignText).append(" )");
        }
        if ( 0 == numsign % 2){
            num = arrLisNoHard.get(random.nextInt(arrLisNoHard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)));
                example -= randnum;
                exampletxt.append(" - ").append(randnum);
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)));
                example *= randnum;
                exampletxt.append(" * ").append(randnum);
            } else {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)));
                example += randnum;
                exampletxt.append(" + ").append(randnum);
            }
        }


        exampletxt.append(" = ");
        TextView textView = findViewById(R.id.textView5);
        textView.setText(exampletxt);

        exampletxts = exampletxt.toString();
        extxt = Integer.toString(example);
        extxtbut.setLength(0);
        end = false;
        minus = false;
    }

    public void sysbtn(View v){
        TextView textView = findViewById(R.id.textView5);
        int id = v.getId();
        if(id == R.id.next) {
            for (int i = 1; i < 100; i++) {
                random_example();
                Log.d("som", exampletxts + extxt);
            }
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
            TextView textView = findViewById(R.id.textView5);
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
