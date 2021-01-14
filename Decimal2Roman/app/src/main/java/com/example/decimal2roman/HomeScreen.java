package com.example.decimal2roman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button BTNDTR = (Button) findViewById(R.id.buttonDTR);
        Button BTNRTD = (Button) findViewById(R.id.buttonRTD);

        BTNDTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //below runs when BTNDTR is clicked
                Intent dtrIntent = new Intent (getApplicationContext(), MainActivity.class); //initializes intent to open MainActivity or Decimal to Roman screen
                startActivity(dtrIntent); //runs dtrIntent and opens MainActivity
            }
        });

        BTNRTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //below runs when BTNRTD is clicked
                Intent rtdIntent = new Intent (getApplicationContext(), Roman2Decimal.class); //initializes intent to open Roman to Decimal screen
                startActivity(rtdIntent); //runs rtdIntent and opens Roman2Decimal
            }
        });

    }
}
