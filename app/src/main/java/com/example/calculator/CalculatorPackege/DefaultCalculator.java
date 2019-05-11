package com.example.calculator.CalculatorPackege;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DefaultCalculator implements ExampleCollector {

    private ArrayList<String> arraySelectTypeSign;       //хеш выбраных знаков
    private ArrayList<Integer> arrayNumberOfSign;
    private int exampleAnswer;
    private StringBuilder exampleBody = new StringBuilder();
    private Random random = new Random();
    private int noHardSign = 1;

    private StringBuilder oneSignText = new StringBuilder();
    private int oneSignInt;


    public DefaultCalculator(ArrayList<String> inputArraySelectTypeSign, ArrayList<Integer> inputArrayNumberOfSign) {
        arraySelectTypeSign = inputArraySelectTypeSign;
        arrayNumberOfSign = inputArrayNumberOfSign;
    }

    private int getOneRand(int number) {          //рандомное числа нужного деапозона
        int ten = 1;

        for (int i = 1; i < number; i++) {
            ten *= 10;
        }
        return (int) Math.floor((ten + 1) + Math.random() * ((9 * ten) - 1));
    }

    private void get2Sign(int sign, int nam1, int nam2) {        //создание одного примера
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
        } else {
            int n3 = n1 * n2;
            oneSignInt = n2;
            oneSignText.append(n3).append(" : ").append(n1);
        }
    }


    public String getExampleBody() {
        int numsign, singleNoHardSign;
        exampleAnswer = 0;
        exampleBody.setLength(0);

        String num = arraySelectTypeSign.get(random.nextInt(arraySelectTypeSign.size()));//код для создание одного примара 111 -> 2+3
        numsign = arrayNumberOfSign.get(random.nextInt(arrayNumberOfSign.size()));    //выбор количество знаков

        get2Sign(Character.getNumericValue(num.charAt(0)),
                Character.getNumericValue(num.charAt(1)),
                Character.getNumericValue(num.charAt(2)));

        exampleAnswer = oneSignInt;
        if (numsign == 1) {
            exampleBody.append(oneSignText);
        } else {
            exampleBody.append("( ").append(oneSignText).append(" )");
        }

        for (int i = 3; i <= numsign; i += 2) {
            num = arraySelectTypeSign.get(random.nextInt(arraySelectTypeSign.size()));

            get2Sign(Character.getNumericValue(num.charAt(0)),
                    Character.getNumericValue(num.charAt(1)),
                    Character.getNumericValue(num.charAt(2)));
            singleNoHardSign = random.nextInt(noHardSign) + 1;

            if (singleNoHardSign == 1) {
                exampleAnswer += oneSignInt;
                exampleBody.append(" + ");
            } else if (singleNoHardSign == 2) {
                exampleAnswer -= oneSignInt;
                exampleBody.append(" - ");
            } else {
                exampleAnswer *= oneSignInt;
                exampleBody.append(" * ");
            }

            exampleBody.append("( ").append(oneSignText).append(" )");
        }
        if (0 == numsign % 2) {
            singleNoHardSign = random.nextInt(noHardSign) + 1;

            if (singleNoHardSign == 1) {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)));
                exampleAnswer += randnum;
                exampleBody.append(" + ").append(randnum);
            } else {
                int randnum = getOneRand(Character.getNumericValue(num.charAt(2)));
                exampleAnswer -= randnum;
                exampleBody.append(" - ").append(randnum);
            }
        }
        exampleBody.append(" = ");

        return exampleBody.toString();
    }

    public int getExampleAnswer() {
        return exampleAnswer;
    }
}
