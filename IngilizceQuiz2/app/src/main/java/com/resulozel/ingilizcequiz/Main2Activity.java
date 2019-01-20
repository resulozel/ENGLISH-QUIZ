package com.resulozel.ingilizcequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
        Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

        public void tanimla()
        {

            btn=(Button) findViewById(R.id.button5);

        }
        public void gecisyap(){

            Intent ıntent=new Intent(this,Quiz.class);
        }
        public void tiklama(){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        }

    }

