package com.example.myfirstapp;

import android.app.Activity;
import android.widget.ListView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.*;
import android.content.Intent;
import android.text.TextWatcher;
import android.text.Editable;

import android.os.Bundle;

import android.widget.Toast;

public class MainActivity extends Activity
{
    boolean hasSelected;
    boolean hasCorrectText;
    int position;
    Button button;
    String currentText;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        hasSelected = false;

        currentText = "";

        // Set the button properties
        button = (Button) findViewById(R.id.calculate);
        button.setEnabled(false);

        EditText text = (EditText) findViewById(R.id.edit_message);

        text.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
                updateCalculator(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                calculate();
            }

        });

    }

    public void onSupSup(View view){
        hasSelected = true;
        switch(view.getId()){
            case R.id.radio_letter:
                position = 0;
                break;
            case R.id.radio_letter_big:
                position = 1;
                break;
            case R.id.radio_parcel:
                position = 2;
                break;
        }
        updateCalculator(currentText);

    }
    /***********
     *
     * updateCalculator: Checks to make sure that a package type is
     *      selected and that the entered ounces are correct.
     *
     */
    public void updateCalculator(String input){
        currentText = input;
        if( input.length() > 0 && checkNumber(input)){
            int ounces = (int)Math.ceil(Double.parseDouble(input));
            if(ounces >= 0 && ounces <= 13){
                hasCorrectText = true;
            }
            else{
                new AlertDialog.Builder(this).setTitle("Careful!").setMessage("You input an invalid weight try a number between 1-13").setNeutralButton("Close", null).show();
                hasCorrectText = false;
            }
        }
        else{
            hasCorrectText = false;
        }
        if( hasSelected && hasCorrectText ){
            button.setEnabled(true);
        }
        else{
            button.setEnabled(false);
        }
    }

    public void calculate(){
        int ounces = (int)Math.ceil(Double.parseDouble(currentText));
        float multiplier;
        float initialPrice;
        float finalAmount;
        if(position < 1 && ounces < 4){
            initialPrice = .25f;
            multiplier = .2f;
        }
        else if(position <= 1){
            initialPrice = .70f;
            multiplier = .2f;
        }
        else if(position == 2 && ounces < 4){
            initialPrice = 1.95f;
            multiplier = 0.0f;
        }
        else{
            initialPrice = (float)1.95 - (float)(.17 * 3);
            multiplier = .17f;
        }

        finalAmount = initialPrice + multiplier * ounces;
        String cost = String.format("%1$,.2f", (float)finalAmount);
        new AlertDialog.Builder(this).setTitle("Answer").setMessage("Price for shipping: " + cost + "$").setNeutralButton("Close", null).show();

    }


    public static boolean checkNumber(String str){
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
