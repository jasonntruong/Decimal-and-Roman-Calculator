package com.example.decimal2roman;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Roman2Decimal extends AppCompatActivity {

    TextView decimalTextView;
    TextView inputTextView;

    int operationcounter;

    float numstore;
    float decimaloutput;

    String roman;
    String romaninput;

    boolean add;
    boolean minus;
    boolean multiply;
    boolean division;

    protected String convertToDecimal(String roman) { //method convertToDecimal takes in a String parameter and returns its equivalent decimal value
        float decimal = 0;

        this.roman = roman;

        String[] toDecimalRoman = {"CM", "CD", "XC", "XL", "IX", "IV", "M", "D", "C", "L", "X", "V", "I"};
        //array that holds the letters that represent roman numerals (also includes the letters of numbers
        //that don't follow the addition pattern - like 4 and 9 which use subtraction)

        float[] toDecimalInt = {900, 400, 90, 40, 9, 4, 1000, 500, 100, 50, 10, 5, 1};
        //array that holds the corresponding numerical values to the roman numerals in the same index of the
        //toDecimalRoman array

        for (byte x = 0; x < toDecimalRoman.length; x++) { //for loop that runs when x is less than the
            //length of the array. Essentially x will act as
            //the index number which allows for the program to
            //go through each element stored in toDecimalRoman
            //and toDecimalInt array

            while (this.roman.indexOf(toDecimalRoman[x]) >= 0) { //while loop that checks if the
                //string/characters at index x in array
                //toDecimalString is found in the parameter.
                //If it is found in the parameter then its
                //index in the this.roman variable will be
                //returned. So checking if the returned
                //value is >=0 will essentially mean that
                //the string/character we are checking for
                //exists somewhere in the this.roman variable

                decimal += toDecimalInt[x];		//if string at index x in array toDecimalString is found in
                //parameter/this.roman variable, we add the corresponding
                //decimal value which is found at the index of x in array
                //toDecimalInt. (e.x this.roman = MX. We find M in MX so we
                //add 1000, which is M in roman numerals)

                this.roman = this.roman.replaceFirst(toDecimalRoman[x], ""); //This removes the string we
                //found in our parameter. So
                //continuing the example above,
                //we found M and added 1000, so
                //now we remove M from the
                //this.roman variable. Thus
                //this.roman now = X and the
                //loops will restart until
                //this.roman is empty/all
                //letters of the parameter have
                //been converted to decimal

            }

            if (decimal > 5000 || roman.length() == 0) { //if decimal is greater than 5000 or equal to 0...
                return ">:("; //the error message ">:(" is returned

            }
        }

        return String.valueOf((int)decimal); //returns the decimal equivalent of the roman numeral parameter
    }

    //this method is explained under MainActivity (same exact method. Couldn't use intent method to reuse the code for this method from MainActivity as each of these parameters would have to be sent over by storing as extra data in the intent, which made the program very laggy)
    protected float applyOperation (float numstore, float decimaloutput, boolean add, boolean subtract, boolean multiply, boolean division, float operationcounter){
        if (operationcounter == 0){
            numstore = decimaloutput;
        } else if (add == true){
            numstore += decimaloutput;
        } else if (subtract == true){
            numstore -= decimaloutput;
        } else if (multiply == true){
            numstore *= decimaloutput;
        } else if (division == true){
            numstore /= decimaloutput;
        }
        return numstore;
    }

    //this method is explained under MainActivity (same exact method. Couldn't use intent method to reuse the code for this method from MainActivity as each of these parameters would have to be sent over by storing as extra data in the intent, which made the program very laggy)
    protected float numberCreator (float num, String potentialnumber){
        try{
            num = Float.parseFloat(potentialnumber);

        } catch (NumberFormatException e){
            num = 0;
        }
        return num;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roman2_decimal);

//INITIALIZATION OF BUTTONS
        Button BTNM = (Button) findViewById(R.id.buttonM);
        Button BTND = (Button) findViewById(R.id.buttonD);
        Button BTNC = (Button) findViewById(R.id.buttonC);
        Button BTNL = (Button) findViewById(R.id.buttonL);
        Button BTNX = (Button) findViewById(R.id.buttonX);
        Button BTNV = (Button) findViewById(R.id.buttonV);
        Button BTNI = (Button) findViewById(R.id.buttonI);
        Button BTNADD = (Button) findViewById(R.id.buttonadd2);
        Button BTNMINUS = (Button) findViewById(R.id.buttonminus2);
        Button BTNMULTIPLY = (Button) findViewById(R.id.buttonmultiply2);
        Button BTNDIVIDE = (Button) findViewById(R.id.buttondivide2);
        Button BTNSWITCH = (Button) findViewById(R.id.tempswitch2);
        Button BTNCONVERT = (Button) findViewById(R.id.buttonCONVERT);
        Button BTNCLEAR = (Button) findViewById(R.id.buttonclear2);

//INITIALIZATION OF TEXTVIEWS
        decimalTextView = (TextView) findViewById(R.id.decimal);
        inputTextView = (TextView) findViewById(R.id.input);

//INITIALIZATION OF INT
        operationcounter = 0;

        BTNM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when BTNM is clicked...

                if (inputTextView.getText().toString().contains("ツ") == true) { //if the error message ¯\_(ツ)_/¯ is in the inputTextView, when BTNM (or any other roman numeral buttton) is clicked, it instantly clears the inputTextView then...
                    inputTextView.setText("");}

                inputTextView.setText(inputTextView.getText() + "M"); //a M (or the respective roman numeral) is added to the inputTextView
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + ""); //the decimal equivalent of the roman numeral inputted from inputTextView is displayed on the decimalTextView

            }
        });

        BTND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //all other roman numeral buttons are explained under BTNM
                if (inputTextView.getText().toString().contains("ツ") == true){
                    inputTextView.setText("");}
                inputTextView.setText(inputTextView.getText() + "D");
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + "");
            }
        });

        BTNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTextView.getText().toString().contains("ツ") == true){
                    inputTextView.setText("");}
                inputTextView.setText(inputTextView.getText() + "C");
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + "");
            }
        });

        BTNL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTextView.getText().toString().contains("ツ") == true){
                    inputTextView.setText("");}
                inputTextView.setText(inputTextView.getText() + "L");
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + "");
            }
        });

        BTNX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTextView.getText().toString().contains("ツ") == true){
                    inputTextView.setText("");}
                inputTextView.setText(inputTextView.getText() + "X");
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + "");
            }
        });

        BTNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTextView.getText().toString().contains("ツ") == true){
                    inputTextView.setText("");}
                inputTextView.setText(inputTextView.getText() + "V");
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + "");
            }
        });

        BTNI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTextView.getText().toString().contains("ツ") == true){
                    inputTextView.setText("");}
                inputTextView.setText(inputTextView.getText() + "I");
                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + "");
            }
        });

        BTNCLEAR.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) { //if BTNCLEAR is held...
                romaninput = ""; //roman input is cleared
                inputTextView.setText(""); //inputTextView is cleared
                decimalTextView.setText(""); //decimalTextView is cleared
                operationcounter = 0; //operationcounter is cleared

                return false;
            }
        });

        BTNCLEAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when BTNCLEAR is clicked...
                romaninput = inputTextView.getText().toString();

                if (romaninput != null && romaninput.length() > 0) { //if romaninput is not null and its length is greater than 0 / is not "" then...
                    inputTextView.setText(romaninput.substring(0, romaninput.length()-1)); //the last character/substring of romaninput is deleted
                }

                decimalTextView.setText((convertToDecimal(inputTextView.getText().toString())) + ""); //the decimalTextView is updated with the decimal equivalent of the new roman input / string at inputTextView after the end substring was deleted

            }
        });

        BTNSWITCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when BTNSWITCH is clicked...
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class); //initializes startIntent to open MainActivity screen
                startActivity(startIntent); //runs the startIntent and opens the MainActivity screen
            }
        });

        BTNADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when BTNADD is clicked...

                if (decimalTextView.getText().length() > 0) { //if decimalTextView length is greater than 0 (or is not "")
                    decimaloutput = numberCreator(decimaloutput, decimalTextView.getText().toString()); //runs numberCreator with the decimal on decimalTextView (essentially once the roman numerals are converted to decimals, math is performed on the decimals and then the final answer is outputted in the decimalTextView and that roman numeral equivalent is outputted to the inputTextView (explained more below))

                    numstore = applyOperation(numstore, decimaloutput, add, minus, multiply, division, operationcounter); //runs applyOperation to apply the operation before BTNADD was clicked and stores the answer to that operation under numstore

                    minus = false; //sets all the operations to false except add/the respective operation
                    multiply = false;
                    division = false;
                    add = true;
                    decimalTextView.setText(""); //clears decimalTextView so when an operation button is clicked, the decimal equivalent of the previous roman numeral does not appear
                    inputTextView.setText(""); //clears inputTextView (when an operation is clicked inputTextView should be cleared for user friendliness -- or else MM + MM will appear like MMMM)
                    operationcounter++;
                }

            }
        });

        BTNMINUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //all operation buttons are explained under BTNADD
                if (decimalTextView.getText().length() > 0){
                    decimaloutput = numberCreator(decimaloutput, decimalTextView.getText().toString());


                    numstore = applyOperation(numstore, decimaloutput, add, minus, multiply, division, operationcounter);

                    add = false;
                    multiply = false;
                    division = false;
                    minus = true;
                    decimalTextView.setText("");
                    inputTextView.setText("");
                    operationcounter++;
                }

            }
        });

        BTNMULTIPLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (decimalTextView.getText().length() > 0) {
                    //resultTextView.setText("");
                    decimaloutput = numberCreator(decimaloutput, decimalTextView.getText().toString());

                    numstore = applyOperation(numstore, decimaloutput, add, minus, multiply, division, operationcounter);

                    minus = false;
                    add = false;
                    division = false;
                    multiply = true;
                    decimalTextView.setText("");
                    inputTextView.setText("");
                    operationcounter++;
                }

            }
        });

        BTNDIVIDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (decimalTextView.getText().length() > 0) {
                    //resultTextView.setText("");
                    decimaloutput = numberCreator(decimaloutput, decimalTextView.getText().toString());


                    numstore = applyOperation(numstore, decimaloutput, add, minus, multiply, division, operationcounter);

                    minus = false;
                    add = false;
                    multiply = false;
                    division = true;
                    decimalTextView.setText("");
                    inputTextView.setText("");
                    operationcounter++;
                }

            }
        });

        BTNCONVERT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                romaninput = inputTextView.getText().toString();

                try{ //parses romaninput to a float
                    decimaloutput = Float.parseFloat(convertToDecimal(romaninput));
                } catch (NumberFormatException e){ //catches NumberFormatException for if romaninput cannot be parsed to float (this occurs when romaninput is the error message ¯\\_(ツ)_/¯)
                    decimaloutput = 0; //decimaloutput is set to 0, so if user tries performing an operation on ¯\\_(ツ)_/¯, it will act as a 0

                }

                decimalTextView.setText(decimaloutput + ""); //displays decimaloutput on decimalTextView

                if (decimalTextView.getText().length() > 0) { //below will run if decimalTextView length is greater than 0 / not ""
                    decimaloutput = numberCreator(decimaloutput, decimalTextView.getText().toString());

                    numstore = applyOperation(numstore, decimaloutput, add, minus, multiply, division, operationcounter); //this applies the last operation inputted before hitting BTNCONVERT

                    if (numstore <= 5000 && numstore > 0) { //checks if numstore is less than or equal to 5000 and greater than 0

                        if (numstore%1 != 0) { //if numstore/the final answer after all operations are applied is a decimal
                            decimalTextView.setText(numstore + ""); //decimalTextView is set to numstore (still a float value)
                            inputTextView.setText("¯\\_(ツ)_/¯");  //inputTextView is set to the error message ¯\_(ツ)_/¯ since there is no roman conversion for decimal numbers

                        } else if (numstore%1 == 0){ //if numstore is not a decimal / is an integer

                            decimalTextView.setText((int)numstore + ""); //decimalTextView is set to the int value of numstore

                            Intent intent = new Intent (getApplicationContext(), MainActivity.class); //intent is initialized
                            intent.putExtra("solution", Float.parseFloat(decimalTextView.getText().toString())); //float data is inputted into intent. Under "solution", the outputted decimal is stored
                            startActivityForResult(intent, 1); //this activity is started with the requestcode of 1 and takes intent as the Intent parameter
                            //intent is used in order to reuse the convertToRoman method code. Essentially we input the float data of the decimal conversion of the roman input the user entered in the inputTextView and send that information to the MainActivity class

                        }
                    } else { //else / if numstore is equal to or less than 0 or is greater than 5000 then...
                        decimalTextView.setText(">:("); //decimalTextView is set to the error message >:(
                        inputTextView.setText("¯\\_(ツ)_/¯"); //inputTextView is set to the error message ¯\_(ツ)_/¯
                    }

                    //VARIABLES RESET
                    numstore = 0;
                    operationcounter = 0;
                    add = false;
                    minus = false;
                    multiply = false;
                    division = false;
                    romaninput = "";
                    decimaloutput = 0;

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //this method is ran after the code returns from the MainActivity after converting the decimal output in decimalTextView to roman numerals
        super.onActivityResult(requestCode, resultCode, data);
        String result = "";

        if (requestCode == 1){ //checks if the requestCode is equal to 1
            if (resultCode == RESULT_OK) { //if the resultCode is RESULT_OK, then result is set to the extra String data we stored under "result" in the resultIntent intent that's in the MainActivity class (this extra String data is the roman numerals conversion)
                result = data.getStringExtra("result");

            } else if (resultCode == RESULT_CANCELED) { //if resultCode is RESULT_CANCELED, meaning the roman conversion of the decimal from decimalTextView has a length less than 1 and/or has more than 5 M's (over 5000), then...
                result = "¯\\_(ツ)_/¯"; //result is set to the error message ¯\_(ツ)_/¯
            }

            inputTextView.setText(result); //inputTextView is set to result (For e.x if the operation M+C were entered, decimalTextView will display 1100. So instead of inputTextView remaining as C as that was the last roman numeral entered, it should be updated to what the decimalTextView is (which would be MC))
        }

    }
}