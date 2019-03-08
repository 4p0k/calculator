package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class calculating extends AppCompatActivity {



    ArrayList<String> lstnum = new ArrayList<>();       //хеш выбраных знаков
    ArrayList<Integer> lstsign = new ArrayList<>();     //хеш количество знаков
    //boolean nohard = false;                             //хеш выбора сложности
    ArrayList<String> arrnohard = new ArrayList<>();    //создание дом листа для промижуточных знаков
    String exampletxts ,extxt;                          //пример и правельный ответ
    StringBuilder extxtbut = new StringBuilder ();      //ответ пользователя
    boolean minus = false, end = false;                 //поставил ли пользователь минус и конец даного примера
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculating);

        lstnum = getIntent().getStringArrayListExtra("listnum");
        lstsign = getIntent().getIntegerArrayListExtra("listsig");
        //nohard = getIntent().getExtras().getBoolean("mult");
        for (int i = 0; i < lstnum.size(); i++) {

            if (2 >= Character.getNumericValue(lstnum.get( i ).charAt(0))) {

                arrnohard.add(lstnum.get(i));
            }else {break;}
        }
        if (arrnohard.size() == 0) {
            arrnohard.add("122");
            arrnohard.add("222");
        }
        random_example ();
    }


    public int getOneRand (int r, int z){          //рандом числа нужного деапозона
        int rz, ten = 1;

        if (z == 4){
            ten = 2;
            for (int i =1; i < r; i++){
                ten = ten * 10 + 2;
            }
        }else {
            for (int i = 1; i < r; i++) {
                ten *= 10;
            }
        }
        rz = (int)Math.floor(ten + Math.random() * (9 * ten));
        return rz;
    }

    public String[] get2Sign (int z,int nam1,int nam2) {        //создание одного примера
        int ext;
        StringBuilder textext = new StringBuilder ();

        int n1 = getOneRand(nam1, z);
        int n2 = getOneRand(nam2, z);
        if (z == 1) {
            ext = n1 + n2;
            textext.append(n1).append(" + ").append(n2);
        } else if (z == 2) {
            ext = n1 - n2;
            textext.append(n1).append(" - ").append(n2);
        } else if (z == 3) {
            ext = n1 * n2;
            textext.append(n1).append(" * ").append(n2);
        }else {
            int n3 = n1 * n2;
            ext = n1;
            textext.append(n3).append(" / ").append(n2);
        }

        String[] extmas = {Integer.toString(ext), textext.toString()};
        return extmas;
    }

    public void random_example (){
        StringBuilder exampletxt = new StringBuilder ();                        //сбор примера
        int example = 0;                                                        //ответ
        int numsign = lstsign.get( random.nextInt(lstsign.size()) );    //выбор количество знаков
        String num;                                                             //код для создание одного примара 111 -> 2+3
        String[] extmas;

        num = lstnum.get( random.nextInt(lstnum.size()) );

        extmas = get2Sign(  Character.getNumericValue(num.charAt(0)),
                            Character.getNumericValue(num.charAt(1)),
                            Character.getNumericValue(num.charAt(2)) );

        example = Integer.parseInt(extmas[0]);
        if (numsign == 1) {
            exampletxt.append(extmas[1]);
        }else {
            exampletxt.append("( ").append(extmas[1]).append(" )");
        }

        for (int i = 3; i <= numsign; i += 2) {
            num = lstnum.get(random.nextInt(lstnum.size()));

            extmas = get2Sign(  Character.getNumericValue(num.charAt(0)),
                                Character.getNumericValue(num.charAt(1)),
                                Character.getNumericValue(num.charAt(2)) );

            num = arrnohard.get(random.nextInt(arrnohard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                example -= Integer.parseInt(extmas[0]);
                exampletxt.append(" - ");
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                example *= Integer.parseInt(extmas[0]);
                exampletxt.append(" * ");
            } else {
                example += Integer.parseInt(extmas[0]);
                exampletxt.append(" + ");
            }

            exampletxt.append("( ").append(extmas[1]).append(" )");
        }
        if ( 0 == numsign % 2){
            num = arrnohard.get(random.nextInt(arrnohard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example -= randnum;
                exampletxt.append(" - ").append(randnum);
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example *= randnum;
                exampletxt.append(" * ").append(randnum);
            } else {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example += randnum;
                exampletxt.append(" + ").append(randnum);
            }
        }




/*        if (numsign >= 1){
            num = lstnum.get( random.nextInt(lstnum.size()) );

             extmas = get2Sign(Character.getNumericValue(num.charAt(0)), Character.getNumericValue(num.charAt(1)), Character.getNumericValue(num.charAt(2))  );

            example = Integer.parseInt(extmas[0]);
            if (numsign == 1) {
                exampletxt.append(extmas[1]);
            }else {
                exampletxt.append("( ").append(extmas[1]).append(" )");
            }
        }
        if (numsign == 2) {
            num = arrnohard.get( random.nextInt(arrnohard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example -= randnum;
                exampletxt.append(" - ").append(randnum);
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example *= randnum;
                exampletxt.append(" * ").append(randnum);
            } else {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example += randnum;
                exampletxt.append(" + ").append(randnum);
            }
        }
        if (numsign >= 3) {
            num = lstnum.get(random.nextInt(lstnum.size()));

            extmas = get2Sign(Character.getNumericValue(num.charAt(0)), Character.getNumericValue(num.charAt(1)), Character.getNumericValue(num.charAt(2)));

            int ex1 = Integer.parseInt(extmas[0]);

            num = arrnohard.get(random.nextInt(arrnohard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                example -= ex1;
                exampletxt.append(" - ");
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                example *= ex1;
                exampletxt.append(" * ");
            } else {
                example += ex1;
                exampletxt.append(" + ");
            }

            exampletxt.append("( ").append(extmas[1]).append(" )");

        }
        if (numsign == 4){
            num = arrnohard.get(random.nextInt(arrnohard.size()));

            if (Character.getNumericValue(num.charAt(0)) == 2) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example -= randnum;
                exampletxt.append(" - ").append(randnum);
            } else if (Character.getNumericValue(num.charAt(0)) == 3) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example *= randnum;
                exampletxt.append(" * ").append(randnum);
            } else {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)),1);
                example += randnum;
                exampletxt.append(" + ").append(randnum);
            }
        }*/

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
            random_example();
            //Expression expression = new Expression(3);
            //textView.setText(expression + " = " + expression.getAnswer());
        }else if (id == R.id.oknext){
            if (extxt.compareToIgnoreCase(extxtbut.toString()) == 0 || end) {
                random_example();
            }
        }else if (id == R.id.exwut){
            textView.setText(exampletxts + extxt);
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
            textView.setText(exampletxts + extxtbut);
        }
    }
    //Log.d("som",Integer.toString(extxtbut.length()));
}
