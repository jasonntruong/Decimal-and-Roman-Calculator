package com.example.decimal2roman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    TextView romanTextView;

    int operationcounter;

    float num1;
    float numstore;

    boolean add;
    boolean minus;
    boolean multiply;
    boolean division;

    protected String convertToRoman (String decimalstring) { //method convertToRoman takes in a decimal parameter and
        //returns its roman numeral equivalent
        String roman;
        float decimal;

        try{
            if (decimalstring == "" || Float.parseFloat(decimalstring) > 5000){ //if decimalstring has no input or is greater than 5000 then "" or no roman numeral is returned
                return "";
            }
            else {
                decimal = Float.parseFloat(decimalstring); //sets the decimal variable to what the parameter value is
            }

        } catch (NumberFormatException e){ //catches when decimalstring cannot be parsed to float as it is a string. This is for when decimalstring is the error message \_(ツ)_/
            return ""; //returns "" as the roman conversion
        }

        roman = ""; //initializes roman variable so that program can use += to add the roman letters to it

        String[] toStringRoman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        //array that holds the letters that represent roman numerals (also includes the letters of numbers
        //that don't follow the addition pattern - like 4 and 9 which use subtraction)

        int[] toStringInt = {1000, 900, 500, 400, 100, 90, 50 ,40, 10, 9, 5, 4, 1};
        //array that holds the corresponding numerical values to the roman numerals in the same index of the
        //toStringRoman array

        for (byte i = 0; i < toStringRoman.length; i++) { //for loop that runs when i is less than the length
            //of the array. Essentially i will act as the index
            //number which allows for the program to go through
            //each element stored in toStringRoman and
            //toStringInt array

            while (decimal >= toStringInt[i]) { //sees if the parameter value is greater or equal to the
                //value at index i in the array toStringInt. Ex. while loop
                //runs if decimal >= 1000, if not, i increases by 1 so
                //loop checks if decimal >= 900

                decimal -= toStringInt[i];  //if decimal is greater or equal to value at index i in
                //toStringInt array then decimal is subtracted by value
                //at index i [e.x decimal = 102. checks
                //decimal >= 100. It is so decimal -= 100
                //and thus decimal = 2.]

                roman += toStringRoman[i];		//then roman/the string that is returned has the letter at
                //index i of array toStringRoman added to it. Since both
                //arrays have corresponding values at the same index
                //(M and 1000 are both index 0), then whatever decimal
                //was subtracted by, the letter that corresponds to that
                //value is added to roman

            }
        }

        return roman; //roman is returned
    }
    //this method applies operations to the numbers
    protected float applyOperation (float numstore, float num1, boolean add, boolean subtract, boolean multiply, boolean division, int operationcounter){
        //rather than storing numbers to variables and performing operations on them (e.x num1 + num2), it would be better to store the current number (num1) and apply operations to variable that holds the final answer (numstore)
        //by doing it this way, it does not limit the amount of operations we can do before clicking equals. E.x num1 and num2 can only store 2 numbers (1 per variable) & apply 1 operation before having to click equals, meanwhile this method allows for infinite amount of operations (This: 1+8*3/2-7 can be done all at once without having to click equals per operation)
        if (operationcounter == 0){
            numstore = num1; //if this is the first number clicked, it becomes numstore/first number (e.x first number clicked is 1 so numstore becomes 1)
        } else if (add == true){
            numstore += num1; //if the add button was clicked, num1 is added to numstore
        } else if (subtract == true){
            numstore -= num1; //if the subtraction button was clicked, numstore is subtracted by num1
        } else if (multiply == true){
            numstore *= num1; //if the multiply button was clicked, numstore is multiplied by num1
        } else if (division == true){
            numstore /= num1; //if division button was clicked, numstore is divided by num1
        }
        return numstore; //numstore is returned
    }


    protected float numberCreator (float num, String potentialnumber) { //runs a try and catch so that only valid strings are parsed to floats. Ex if the error message \_(ツ)_/ is potentialnumber parameter, then it will not become the num parameter
        try{
            num = Float.parseFloat(potentialnumber); //tries to parse potentialnumber to float

        } catch (NumberFormatException e) { //if there is a NumberFormat error then instead of setting num as potentialnumber, num is set as 0 (so if you try doing operations on \_(ツ)_/ then \_(ツ)_/ will act as a 0)
            num = 0;
        }
        return num;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent resultIntent = getIntent();
        float numfromr2d = resultIntent.getFloatExtra("solution", 5001); //gets float data from resultIntent under name "solution". This float data is the decimal value outputted from the roman to decimal screen / class

        if (numfromr2d <= 5000) { //"solution" or numfromr2d is never over 5000 (unless from its defaultValue) as any outputted decimal over 5000 automatically becomes ">:(". This if statement checks if we are here to convert a decimal to roman and go back to Roman2Decimal, or if we're here to use the MainActivity class code
            //if this statement is reached from using MainActivity then numfromr2d will be 5001 (not under 5000), however, if the statement is reached from Roman2Decimal code, then numfromr2d will be under 5000
            //Intent resultIntent = new Intent();
            int mcounter = 0; //mcounter counts the amount of times "M" appears

            resultIntent.putExtra("result", convertToRoman(String.valueOf(numfromr2d))); //converts the numfromr2d / the decimal outputted from the Roman2Decimal screen to roman numerals and stores the data in the intent under the name "result"

            setResult(RESULT_CANCELED); //sets result automatically to RESULT_CANCELED

            if (resultIntent.getStringExtra("result").length() > 0) { //checks if the length of the roman numeral conversion is greater than 0 / not ""
                for (int i = 0; i < resultIntent.getStringExtra("result").length(); i++) { //for loop that counts the amount of times "M" occurs in the roman numeral conversion and stores that number in mcounter
                    if (resultIntent.getStringExtra("result").charAt(i) == 'M') {
                        mcounter++;
                    }


                }

                if (mcounter <= 5) { //if mcounter is less than or equal to 5 (less than or equal to 5000) then...
                    setResult(RESULT_OK, resultIntent); //result is set to RESULT_OK (meaning if there are more than 5 M's result would remain RESULT_CANCELED)
                }

            }

            finish(); //at this point we've got all the information we needed (the roman conversion of the outputted decimal) so the code resumes back in the Roman2Decimal class
        }

//INITIALIZATION OF ALL THE BUTTONS
        Button BTN9 = (Button) findViewById(R.id.button9);
        Button BTN8 = (Button) findViewById(R.id.button8);
        Button BTN7 = (Button) findViewById(R.id.button7);
        Button BTN6 = (Button) findViewById(R.id.button6);
        Button BTN5 = (Button) findViewById(R.id.button5);
        Button BTN4 = (Button) findViewById(R.id.button4);
        Button BTN3 = (Button) findViewById(R.id.button3);
        Button BTN2 = (Button) findViewById(R.id.button2);
        Button BTN1 = (Button) findViewById(R.id.button1);
        Button BTN0 = (Button) findViewById(R.id.button0);
        Button BTNADD = (Button) findViewById(R.id.buttonadd2);
        Button BTNMINUS = (Button) findViewById(R.id.buttonminus);
        Button BTNMULTIPLY = (Button) findViewById(R.id.buttonmultiply);
        Button BTNDIVISION = (Button) findViewById(R.id.buttondivision);
        Button BTNCLEAR = (Button) findViewById(R.id.buttonclear);
        Button BTNEQUALS = (Button) findViewById(R.id.buttonequals);
        Button BTNSWITCH = (Button) findViewById (R.id.tempswitch);

//INITIALIZATION OF THE TEXTVIEWS
        resultTextView = (TextView) findViewById(R.id.mainCalc);
        romanTextView = (TextView) findViewById(R.id.sideCalc);

//INITIALIZATION OF FLOATS/INTS
        num1 = 0;
        numstore = 0;
        operationcounter = 0;

        BTNSWITCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when BTNSWITCH is clicked...
                Intent startIntent = new Intent(getApplicationContext(), Roman2Decimal.class); //initializes startIntent to open Roman2Decimal screen
                startActivity(startIntent); //runs the startIntent and opens the Roman2Decimal screen
            }
        });

        BTN0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){ //when BTN0 is clicked...
                resultTextView.setText(resultTextView.getText() + "0"); //a 0 (or the respective BTN #) is added to the resultTextView
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + "")); //takes the String value of the decimal from resultTextView and runs it as the parameter for convertToRoman method to convert the inputted decimal to roman numerals. Outputs the roman numeral equivalent to the romanTextView
            }
        });

        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){ //BTN1 to BTN 9 are all explained under BTN0
                resultTextView.setText(resultTextView.getText() + "1");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "2");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "3");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "4");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "5");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "6");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "7");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "8");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTN9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                resultTextView.setText(resultTextView.getText() + "9");
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + ""));
            }
        });

        BTNCLEAR.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) { //if BTNCLEAR is held...
                resultTextView.setText(""); //resultTextView is cleared
                romanTextView.setText(""); //romanTextView is cleared
                num1 = 0; //num1 is cleared
                numstore = 0; //numstore is cleared
                operationcounter = 0; //operationcounter is cleared

                return false;
            }
        });

        BTNCLEAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) { //if BTNCLEAR is clicked...

                if (resultTextView.getText().toString() != null && resultTextView.getText().length() > 0) { //if resultTextView is not empty / length is greater than 0 / not "" then...
                    resultTextView.setText(resultTextView.getText().toString().substring(0, resultTextView.getText().length()-1)); //the character/substring at the end of the string of resultTextView is deleted
                }
                romanTextView.setText(convertToRoman(resultTextView.getText().toString() + "")); //the romanTextView is updated with the roman equivalent of the new decimal input / string at resultTextView after the end substring was deleted
            }
        });

        BTNADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when BTNADD is clicked...

                if (resultTextView.getText().length() > 0) { //if resultTextView length is greater than 0 (or not "")
                    num1 = numberCreator(num1, resultTextView.getText().toString()); //runs numberCreator with the decimal on resultTextView and sets that value to num1

                    numstore = applyOperation(numstore, num1, add, minus, multiply, division, operationcounter); //numstore is set to the float returned from the applyOperation method (this applies the operation before BTNADD/the respective button was clicked)

                    minus = false; //sets all operation booleans to false except for add (or the respective operation's boolean)
                    multiply = false;
                    division = false;
                    add = true;
                    resultTextView.setText(""); //clears resultTextView (when an operation is clicked resultTextView should be cleared for user friendliness -- or else 52 + 34 will appear like 5234)
                    operationcounter++; //adds 1 to the operationcounter
                }
            }
        });

        BTNMINUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //all operation buttons are explained under BTNADD
                if (resultTextView.getText().length() > 0){
                    num1 = numberCreator(num1, resultTextView.getText().toString());

                    numstore = applyOperation(numstore, num1, add, minus, multiply, division, operationcounter);

                    add = false;
                    multiply = false;
                    division = false;
                    minus = true;
                    resultTextView.setText("");
                    operationcounter++;
                }
            }
        });

        BTNMULTIPLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultTextView.getText().length() > 0) {
                    num1 = numberCreator(num1, resultTextView.getText().toString());

                    numstore = applyOperation(numstore, num1, add, minus, multiply, division, operationcounter);

                    minus = false;
                    add = false;
                    division = false;
                    multiply = true;
                    resultTextView.setText("");
                    operationcounter++;
                }
            }
        });

        BTNDIVISION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultTextView.getText().length() > 0) {
                    num1 = numberCreator(num1, resultTextView.getText().toString());

                    numstore = applyOperation(numstore, num1, add, minus, multiply, division, operationcounter);

                    minus = false;
                    add = false;
                    multiply = false;
                    division = true;
                    resultTextView.setText("");
                    operationcounter++;
                }
            }
        });

        BTNEQUALS.setOnClickListener(new View.OnClickListener() { //when BTNEQUALS is clicked...
            @Override
            public void onClick (View v){

                if (resultTextView.getText().length() > 0) { //if resultTextView length is greater than 0/is not ""

                    num1 = numberCreator(num1, resultTextView.getText().toString());

                    numstore = applyOperation(numstore, num1, add, minus, multiply, division, operationcounter); //this applies the last operation inputted before hitting BTNEQUALS

                    //if statement for all unwanted scenarios that will result in an error screen
                    if (Float.isInfinite(numstore) == true || Float.isNaN(numstore) == true || numstore > 9999999){ //try and catch does not work as in the applyOperation method where the math is done, there is no way to communicate that 1. a number has been divided by 0 and 2. how to change the resultTextView if a number was divided by 0. So checking if float was infinite (x/0) or NaN (0/0) was used to see if a number was divided by 0
                        //Also checks if numstore is over 9999999 to see if number is over 7 digits as this is when scientific notation comes into play. Scientific notation converted into String becomes for ex "9.9E7" which isn't easily converted back into a float due to the E; so max number length was set to 7, 8 would result in S.N. Also checking if the length of numstore is greater than 7 doesn't work as "9.9E7" is not greater than 7 characters

                        numstore = 0; //sets numstore to 0
                        resultTextView.setText("\\_(ツ)_/"); //error screen

                    } else if (numstore%1 == 0){ //if numstore is not a decimal number...

                        resultTextView.setText(((int)numstore) + ""); //its int value is printed to resultTextView
                        romanTextView.setText(convertToRoman(numstore + "")); //romanTextView is set to roman numeral / string returned from the convertToRoman method which takes numstore (or resultTextView) as the parameter

                    } else { //else or when numstore is a decimal number...

                        resultTextView.setText(numstore + ""); //its float value is printed to resultTextView
                        romanTextView.setText(""); //romanTextView is set to "" as decimal numbers do not have a roman numeral equivalent

                        if (resultTextView.getText().charAt(resultTextView.getText().length()-1) == '.'){ //if the resultTextView / decimal outputted has a period as the last character, then it is deleted (e.x 650 000. turns to 650 000 *this happens because there is a max of 7 characters*)
                            resultTextView.setText(resultTextView.getText().toString().replace(".", ""));
                        }
                    }

                    //VARIABLES RESET
                    numstore = 0;
                    operationcounter = 0;
                    add = false;
                    minus = false;
                    multiply = false;
                    division = false;

                }
            }
        });
    }
}
