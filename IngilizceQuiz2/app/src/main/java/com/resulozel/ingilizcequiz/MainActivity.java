package com.resulozel.ingilizcequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String QUIZ_KEY="key";
  //  public static  final int ALET =1;
    public static  final int MALZEME=2;
    public static  final int FİİL =3;
    public static  final int SEBZE_MEYVE=4;
    public static  final int MESLEKLER=5;
    public static  final int ZARFLAR=6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void testClick(View v)
    {
        Intent intent= new Intent(getApplicationContext(),Quiz.class);

        switch (v.getId()){
          /* case R.id.fab1:
                intent.putExtra(QUIZ_KEY,ALET);
                startActivity(intent);
                break;*/
            case R.id.fab2:
                intent.putExtra(QUIZ_KEY,MALZEME);
                startActivity(intent);
                break;
            case R.id.fab3:
                intent.putExtra(QUIZ_KEY,FİİL);
                startActivity(intent);
                break;
            case R.id.fab4:
                intent.putExtra(QUIZ_KEY,SEBZE_MEYVE);
                startActivity(intent);
                break;
            case R.id.fab5:
                intent.putExtra(QUIZ_KEY,MESLEKLER);
                startActivity(intent);
                break;
            case R.id.fab6:
                intent.putExtra(QUIZ_KEY,ZARFLAR);
                startActivity(intent);
                break;



        }

    }
}
