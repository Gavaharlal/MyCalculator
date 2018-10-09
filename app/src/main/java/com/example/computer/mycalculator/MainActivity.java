package com.example.computer.mycalculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import net.objecthunter.exp4j.*;

public class MainActivity extends AppCompatActivity {

    final int defaultBlueColor = Color.argb(255, 225, 231, 253);
    final int onClickBlueColor = Color.argb(255, 212, 221, 255);
    final int defaultRedColor = Color.argb(255, 253, 225, 225);
    final int onClickRedColor = Color.argb(255, 255, 205, 205);
    final int MAX_EXPRESSION_LENGTH = 40;

    TextView b1;
    TextView b2;
    TextView b3;
    TextView b4;
    TextView b5;
    TextView b6;
    TextView b7;
    TextView b8;
    TextView b9;
    TextView b0;
    TextView bPlus;
    TextView bMinus;
    TextView bDiv;
    TextView bMul;
    TextView bDot;
    TextView bLeftBracket;
    TextView bRightBracket;
    TextView bGetRes;
    TextView del;

    TextView expressionText;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.B1);
        b2 = findViewById(R.id.B2);
        b3 = findViewById(R.id.B3);
        b4 = findViewById(R.id.B4);
        b5 = findViewById(R.id.B5);
        b6 = findViewById(R.id.B6);
        b7 = findViewById(R.id.B7);
        b8 = findViewById(R.id.B8);
        b9 = findViewById(R.id.B9);
        b0 = findViewById(R.id.B0);
        bLeftBracket = findViewById(R.id.BLeftBrack);
        bRightBracket = findViewById(R.id.BRightBrack);
        bPlus = findViewById(R.id.Bplus);
        bMinus = findViewById(R.id.Bmin);
        bMul = findViewById(R.id.Bmul);
        bDot = findViewById(R.id.Bdot);
        bDiv = findViewById(R.id.Bdivide);
        bGetRes = findViewById(R.id.BResult);

        del = findViewById(R.id.Bdel);

        expressionText = findViewById(R.id.expression);

        View.OnTouchListener onBlueTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (expressionText.length() < MAX_EXPRESSION_LENGTH) {
                            expressionText.append(getSymbFromView(v));
                        }
                        v.setBackgroundColor(onClickBlueColor);
                        break;
                    case MotionEvent.ACTION_UP:

                        v.setBackgroundColor(defaultBlueColor);
                        v.performClick();
                        break;
                }
                return true;
            }
        };

        View.OnTouchListener onRedTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (expressionText.length() < MAX_EXPRESSION_LENGTH) {
                            expressionText.append(getSymbFromView(v));
                        }
                        v.setBackgroundColor(onClickRedColor);
                        break;
                    case MotionEvent.ACTION_UP:

                        v.setBackgroundColor(defaultRedColor);
                        v.performClick();
                        break;
                }
                return true;
            }
        };


        b1.setOnTouchListener(onBlueTouchListener);
        b2.setOnTouchListener(onBlueTouchListener);
        b3.setOnTouchListener(onBlueTouchListener);
        b4.setOnTouchListener(onBlueTouchListener);
        b5.setOnTouchListener(onBlueTouchListener);
        b6.setOnTouchListener(onBlueTouchListener);
        b7.setOnTouchListener(onBlueTouchListener);
        b8.setOnTouchListener(onBlueTouchListener);
        b9.setOnTouchListener(onBlueTouchListener);
        b0.setOnTouchListener(onBlueTouchListener);
        bLeftBracket.setOnTouchListener(onBlueTouchListener);
        bRightBracket.setOnTouchListener(onBlueTouchListener);
        bDot.setOnTouchListener(onBlueTouchListener);


        bPlus.setOnTouchListener(onRedTouchListener);
        bMinus.setOnTouchListener(onRedTouchListener);
        bMul.setOnTouchListener(onRedTouchListener);
        bDiv.setOnTouchListener(onRedTouchListener);

        del.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (expressionText.length() > 0) {
                            expressionText.setText(expressionText.getText().toString().substring(0, expressionText.length() - 1));
                        }
                        v.setBackgroundColor(onClickRedColor);
                        break;
                    case MotionEvent.ACTION_UP:

                        v.setBackgroundColor(defaultRedColor);
                        v.performClick();
                        break;
                }
                return true;
            }
        });

        bGetRes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        v.setBackgroundColor(onClickBlueColor);

                        try {
                            Expression e = new ExpressionBuilder(expressionText.getText().toString()).build();
                            expressionText.setText(String.valueOf(e.evaluate()));
                        } catch (Exception e){
                            bGetRes.setBackgroundColor(Color.RED);
                        }

                        break;
                    case MotionEvent.ACTION_UP:

                        v.setBackgroundColor(defaultBlueColor);
                        v.performClick();
                        break;
                }
                return true;
            }
        });

    }

    private static String getSymbFromView(View v) {
        switch (v.getId()) {
            case R.id.B0:
                return "0";
            case R.id.B1:
                return "1";
            case R.id.B2:
                return "2";
            case R.id.B3:
                return "3";
            case R.id.B4:
                return "4";
            case R.id.B5:
                return "5";
            case R.id.B6:
                return "6";
            case R.id.B7:
                return "7";
            case R.id.B8:
                return "8";
            case R.id.B9:
                return "9";
            case R.id.BLeftBrack:
                return "(";
            case R.id.BRightBrack:
                return ")";
            case R.id.Bdivide:
                return "/";
            case R.id.Bmul:
                return "*";
            case R.id.Bmin:
                return "-";
            case R.id.Bplus:
                return "+";
            case R.id.Bdot:
                return ".";
            default:
                return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("expression", expressionText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("expression")){
            expressionText.setText(savedInstanceState.getString("expression"));
        }
    }
}
