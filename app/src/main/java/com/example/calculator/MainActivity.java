package com.example.calculator;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv,solution_tv;
    MaterialButton button_c,button_open_bracket,button_close_bracket;
    MaterialButton button_divide,button_multiply, button_add, button_subtract,button_equal;
    MaterialButton button_one,button_two,button_three,button_four,button_five,button_six,button_seven,button_eight,button_nine,button_zero;
    MaterialButton button_reset,button_decimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assigId(button_c,R.id.button_c);
        assigId(button_open_bracket,R.id.button_open_bracket);
        assigId(button_close_bracket,R.id.button_close_bracket);
        assigId(button_divide,R.id.button_divide);
        assigId(button_multiply,R.id.button_multiply);
        assigId(button_add,R.id.button_add);
        assigId(button_subtract,R.id.button_subtract);
        assigId(button_equal,R.id.button_equal);
        assigId(button_one,R.id.button_one);
        assigId(button_two,R.id.button_two);
        assigId(button_three,R.id.button_three);
        assigId(button_four,R.id.button_four);
        assigId(button_five,R.id.button_five);
        assigId(button_six,R.id.button_six);
        assigId(button_seven,R.id.button_seven);
        assigId(button_eight,R.id.button_eight);
        assigId(button_nine,R.id.button_nine);
        assigId(button_zero,R.id.button_zero);
        assigId(button_reset,R.id.button_reset);
        assigId(button_decimal,R.id.button_decimal);
    }

    void assigId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;

        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution_tv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Error!")){
            result_tv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();

            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;


        }catch (Exception e){
            return "Error!";
        }
    }

}