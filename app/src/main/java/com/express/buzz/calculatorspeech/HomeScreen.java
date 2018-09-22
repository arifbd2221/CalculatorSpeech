package com.express.buzz.calculatorspeech;


import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity {

    String state = "", sNum = "", sign, a;
    private TextToSpeech mTts;
    double num = 0;
    String b = "";

    int pos = -1;
    int trackLoc = 0;
    boolean adv = true;
    TextView box;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, c, del, add, sub, mul, div, dot, eql;

    ArrayList<String> exp;

    ArrayList<String> exp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        box = findViewById(R.id.box);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        c = findViewById(R.id.c);
        del = findViewById(R.id.del);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        dot = findViewById(R.id.dot);
        eql = findViewById(R.id.eql);
        exp = new ArrayList<String>();
        exp2 = new ArrayList<String>();

        box.setText("");


        mTts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTts.setLanguage(Locale.UK);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




    public String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public String calculation() {
        if ((exp.get(0) == "*" || exp.get(0) == "/" || exp.get(exp.size() - 1) == "*" || exp.get(exp.size() - 1) == "/"
                || exp.get(exp.size() - 1) == "+" || exp.get(exp.size() - 1) == "-") && !exp.isEmpty()) {
            b = "Error";


        } else if (!exp.isEmpty()) {
            /*for (int i = 0; i < exp.size(); i++) {
                if((exp.get(i)=="*" && exp.get(i+1)=="*") || (exp.get(i)=="/" && exp.get(i+1)=="/") )
                {
                    adv=false;
                    b="Error2";
                    break;
                }

            }*/
            if (adv == true) {
                for (int i = 0; i < exp.size(); i++) {
                    if (exp.get(i).equals("*") || exp.get(i).equals("/") || exp.get(i).equals("+") || exp.get(i).equals("-")) {
                        sign = exp.get(i);
                        exp2.add(sNum);
                        exp2.add(sign);
                        sNum = "";
                        trackLoc = i;
                    } else {
                        sNum = sNum + exp.get(i);

                    }

                }
                sNum = "";
                for (int i = trackLoc + 1; i < exp.size(); i++) {
                    sNum = sNum + exp.get(i);

                }
                exp2.add(sNum);

                for (int i = 0; i < exp2.size(); i++) {
                    if (exp2.get(i).equals("*")) {
                        num = (Double.valueOf(exp2.get(i - 1))) * (Double.valueOf(exp2.get(i + 1)));
                        exp2.remove(i);
                        exp2.remove(i);

                        exp2.remove(i - 1);

                        exp2.add(i - 1, String.valueOf(num));
                        b = String.valueOf(num);
                        i = 0;

                    }

                }
                for (int i = 0; i < exp2.size(); i++) {
                    if (exp2.get(i).equals("/")) {
                        num = Double.valueOf(exp2.get(i - 1)) / Double.valueOf(exp2.get(i + 1));
                        exp2.remove(i);
                        exp2.remove(i);
                        exp2.remove(i - 1);
                        exp2.add(i - 1, String.valueOf(num));
                        b = String.valueOf(num);
                        i = 0;

                    }

                }
                for (int i = 0; i < exp2.size(); i++) {
                    if (exp2.get(i).equals("+")) {
                        num = (Double.valueOf(exp2.get(i - 1))) + (Double.valueOf(exp2.get(i + 1)));
                        exp2.remove(i);
                        exp2.remove(i);
                        exp2.remove(i - 1);
                        exp2.add(i - 1, String.valueOf(num));
                        b = String.valueOf(num);
                        i = 0;

                    }

                }
                for (int i = 0; i < exp2.size(); i++) {
                    if (exp2.get(i).equals("-")) {
                        num = Double.valueOf(exp2.get(i - 1)) - Double.valueOf(exp2.get(i + 1));
                        exp2.remove(i);
                        exp2.remove(i);
                        exp2.remove(i - 1);
                        exp2.add(i - 1, String.valueOf(num));
                        b = String.valueOf(num);
                        i = 0;

                    }

                }


            }
        }

        return b;
    }

    public void tappedButton(View view) {
        switch (view.getId()) {
            case R.id.one:
                state = box.getText().toString();
                box.setText(state + "1");
                exp.add("1");
                pos++;
                mTts.speak("One", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.two:
                state = box.getText().toString();
                box.setText(state + "2");
                exp.add("2");
                pos++;
                mTts.speak("Two", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.three:
                state = box.getText().toString();
                box.setText(state + "3");
                exp.add("3");
                pos++;
                mTts.speak("Three", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.four:
                state = box.getText().toString();
                box.setText(state + "4");
                exp.add("4");
                pos++;
                mTts.speak("Four", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.five:
                state = box.getText().toString();
                box.setText(state + "5");
                exp.add("5");
                pos++;
                mTts.speak("Five", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.six:
                state = box.getText().toString();
                box.setText(state + "6");
                exp.add("6");
                pos++;
                mTts.speak("Six", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.seven:
                state = box.getText().toString();
                box.setText(state + "7");
                exp.add("7");
                pos++;
                mTts.speak("Seven", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.eight:
                state = box.getText().toString();
                box.setText(state + "8");
                exp.add("8");
                pos++;
                mTts.speak("Eight", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.nine:
                state = box.getText().toString();
                box.setText(state + "9");
                exp.add("9");
                pos++;
                mTts.speak("Nine", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.zero:
                state = box.getText().toString();
                box.setText(state + "0");
                exp.add("0");
                pos++;
                mTts.speak("Zero", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.c:
                pos = -1;
                box.setText("");
                exp.clear();
                exp2.clear();
                state = "";
                mTts.speak("Display cleared", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.del:
                try {
                    state = removeLastChar(box.getText().toString());
                    box.setText(state);

                    exp.remove(pos);
                    pos--;

                    if (box.getText().toString() == "" || box.getText().toString() == null) {
                        mTts.speak("Nothing in the display", TextToSpeech.QUEUE_FLUSH, null);

                    } else {
                        mTts.speak("Last one deleted", TextToSpeech.QUEUE_FLUSH, null);
                    }
                } catch (Exception e) {

                }
                break;
            case R.id.add:

                state = box.getText().toString();
                box.setText(state + "+");
                exp.add("+");
                pos++;
                mTts.speak("Plus", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.sub:

                state = box.getText().toString();
                box.setText(state + "-");
                exp.add("-");
                pos++;
                mTts.speak("Minus", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.mul:

                state = box.getText().toString();
                box.setText(state + "*");
                exp.add("*");
                pos++;
                mTts.speak("Multiplied by", TextToSpeech.QUEUE_FLUSH, null);

                break;
            case R.id.div:

                state = box.getText().toString();
                box.setText(state + "/");
                exp.add("/");
                pos++;
                mTts.speak("Divided by", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.dot:
                state = box.getText().toString();
                box.setText(state + ".");
                exp.add(".");
                pos++;
                mTts.speak("Point", TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.eql:
                try {
                    a = calculation();
                    b="";
                    Log.e("calculation", "result: "+a);
                    box.setText(String.valueOf(a));
                    sNum="";
                    sign="";
                    num=0;
                    trackLoc=0;
                    pos = -1;
                    exp.clear();
                    exp2.clear();
                    state = "";
                    mTts.speak("Equal" + a, TextToSpeech.QUEUE_FLUSH, null);

                } catch (Exception e) {

                }

                break;
        }
    }


}
