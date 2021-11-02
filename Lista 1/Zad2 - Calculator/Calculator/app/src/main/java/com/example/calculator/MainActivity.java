package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    float in1 = 0, in2 = 0;
    String eval2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        toggleEnableButtons(false);

        if (savedInstanceState != null){
            textView.setText(savedInstanceState.getString("calculator_state"));
            toggleEnableButtons(savedInstanceState.getBoolean("toggle_state"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String eval2 = textView.getText().toString();

        outState.putString("calculator_state", eval2);
        outState.putBoolean("toggle_state", findViewById(R.id.button_plus).isEnabled());
    }

    public void onClickButton(View view) {
        textView.append(((Button) view).getText());

        if (textView.getText().length() < 2){
            toggleEnableButtons(true);
        }

    }

    public void onClickButtonOperation(View view) {
        textView.append(((Button) view).getText());
        toggleEnableButtons(false);
    }

    public void onClickButtonClear(View view) {
        in1 = 0;
        in2 = 0;
        textView.setText(null);
        toggleEnableButtons(false);
    }

    @SuppressLint("SetTextI18n")
    public void onClickButtonEqual(View view) {
        eval2 = textView.getText().toString();
        char[] chars = eval2.toCharArray();

        String[] eval = eval2.split("[-+*/]");

        if (!(Character.isDigit(chars[0]))) // Wynik poprzedzniego dzialania moze byc ujemny - sprawdzam 1 wartosc w tablicy jest cyfra
        {
            eval = Arrays.copyOfRange(eval, 1, eval.length);
            in1 = Float.parseFloat(eval[0]);
            in1 = -in1;
        }

        else { in1 = Float.parseFloat(eval[0]); }

        in2 = Float.parseFloat(eval[1]);
        toggleEnableButtons(true);


        if (eval2.contains("/")){
            if (in2 != 0){ textView.setText(Float.toString(in1 / in2)); }
            else {textView.setText(Float.toString(in1));}
        }
        else if (eval2.contains("*")) { textView.setText(Float.toString(in1 * in2));}
        else if (eval2.contains("+")) { textView.setText(Float.toString(in1 + in2));}
        else { textView.setText(Float.toString(in1 - in2));}

    }

    public void toggleEnableButtons(boolean decision) {
        findViewById(R.id.button_plus).setEnabled(decision);
        findViewById(R.id.button_minus).setEnabled(decision);
        findViewById(R.id.button_multiply).setEnabled(decision);
        findViewById(R.id.button_divide).setEnabled(decision);
    }

}