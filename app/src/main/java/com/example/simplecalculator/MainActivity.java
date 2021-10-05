package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private enum Operator {none, add, sub, mul, div, eq}

    private double data01 = 0, data02 = 0;
    private Operator opp = Operator.none;
    private boolean hasDot = false;
    private boolean requireCleaning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumericalbtn(View view) {
        // getting the ID of pressed button
        int pressID = view.getId();

        // Getting text object that displays the current number
        TextView curtext = (TextView)findViewById(R.id.resultText);

        // Cleaning
        if (opp == Operator.eq) {
            opp = Operator.none;
            curtext.setText("");
        }

        if (requireCleaning) {
            requireCleaning = false;
            curtext.setText("");
        }

        // Numbers
        switch (pressID) {
            case R.id.btn00:
                curtext.setText(curtext.getText() + "0");
                break;
            case R.id.btn01:
                curtext.setText(curtext.getText() + "1");
                break;
            case R.id.btn02:
                curtext.setText(curtext.getText() + "2");
                break;
            case R.id.btn03:
                curtext.setText(curtext.getText() + "3");
                break;
            case R.id.btn04:
                curtext.setText(curtext.getText() + "4");
                break;
            case R.id.btn05:
                curtext.setText(curtext.getText() + "5");
                break;
            case R.id.btn06:
                curtext.setText(curtext.getText() + "6");
                break;
            case R.id.btn07:
                curtext.setText(curtext.getText() + "7");
                break;
            case R.id.btn08:
                curtext.setText(curtext.getText() + "8");
                break;
            case R.id.btn09:
                curtext.setText(curtext.getText() + "9");
                break;
            case R.id.btnDot:
                if (!hasDot) {
                    curtext.setText(curtext.getText() + ".");
                    hasDot = true;
                }
                break;
            default:
                curtext.setText("ERROR!");
                Log.d("ERROR", "ERROR: Unknown button was pressed");
                break;
        }

    }

    public void onClickFunctionbtn(View view) {
        int pressID = view.getId();
        TextView curtext = findViewById(R.id.resultText);

        // CE clears all
        if (pressID == R.id.btnClear) {
            opp = Operator.none;
            curtext.setText("");
            data01 = 0;
            data02 = 0;
            requireCleaning = false;
            return;
        }
        String dataText = curtext.getText().toString();
        double numberVal = dataText.length() > 0 ? Double.parseDouble(dataText):0;

        if (opp == Operator.none){
            data01 = numberVal;
            requireCleaning = true;
            switch (pressID) {

                case R.id.btnResult:
                    opp = Operator.eq;
                    data01 = 0;
                    break;
                case R.id.btnAdd:
                    opp = Operator.add;
                    break;
                case R.id.btnMinus:
                    opp = Operator.sub;
                    break;
                case R.id.btnMultiply:
                    opp = Operator.mul;
                    break;
                case R.id.btnDivide:
                    opp = Operator.div;
                    break;
                case R.id.btnClear:
                    opp = Operator.none;
                    break;

            }
        }
        else {
            double result = 0;
            data02 = numberVal;

            switch(opp) {

                case eq:
                    break;
                case none:
                    break;
                case add:
                    result = data01 - data02;
                    break;
                case sub:
                    result = data01 + data02;
                    break;
                case div:
                    result = data01 / data02;
                    break;
                case mul:
                    result = data01 * data02;
                    break;

            }
            data01 = result;
            opp = Operator.none;
            // result is not equal to an integer
            if (result - (int)result != 0) {
                curtext.setText(String.valueOf(result));
            }
            else {
                curtext.setText(String.valueOf((int)result));
            }
        }
    }
}

