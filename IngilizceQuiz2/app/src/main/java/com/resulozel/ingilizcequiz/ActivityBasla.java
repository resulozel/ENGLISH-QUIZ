package com.resulozel.ingilizcequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class ActivityBasla extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basla);


        Thread zamanlayici=new Thread()
        {
            public  void run()
            {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent mainActivityCagir=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mainActivityCagir);

                }
            }
        };
        zamanlayici.start();


    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }





}