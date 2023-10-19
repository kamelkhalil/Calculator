package com.example.my_calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv ,solutionTv;
   // MaterialButton button_C,button_open_bracket,button_close_bracket,button_divide,button_7,button_8,button_9,button_multiple,button_4,button_5,button_6,button_add,button_1,button_2,button_3,button_substract,button_AC,button_0,button_dot,button_equal;
   MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
   MaterialButton buttonDivide,buttonMultiple,buttonPlus,buttonMinus,buttonEquals;
   MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
   MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.Solution_tv);
        asigenId(buttonC,R.id.button_C);
        asigenId(buttonAC,R.id.button_AC);
        asigenId(buttonBrackOpen,R.id.button_open_bracket);
        asigenId(buttonBrackClose,R.id.button_close_bracket);
        asigenId(buttonDivide,R.id.button_divide);
        asigenId(buttonMultiple,R.id.button_multiple);
        asigenId(buttonPlus,R.id.button_add);
        asigenId(buttonMinus,R.id.button_substract);
        asigenId(buttonEquals,R.id.button_equal);
        asigenId(button0,R.id.button_0);
        asigenId(button1,R.id.button_1);
        asigenId(button2,R.id.button_2);
        asigenId(button3,R.id.button_3);
        asigenId(button4,R.id.button_4);
        asigenId(button5,R.id.button_5);
        asigenId(button6,R.id.button_6);
        asigenId(button7,R.id.button_7);
        asigenId(button8,R.id.button_8);
        asigenId(button9,R.id.button_9);
        asigenId(buttonDot,R.id.button_dot);

    }
    void asigenId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        //solutionTv.setText(buttonText);
        String dataToCalculate = solutionTv.getText().toString();
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("erroe")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "erroe";
        }
        //return "calculated";
    }
}