package com.resulozel.ingilizcequiz;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    private List<String> genelListe,soruListesi;
    private RelativeLayout soruContainer;
    private LinearLayout butonContainer;
    private TextView ilerleme_tv,soru_tv;
    private Random random;
    private String dogruCevap;
    private final int TOPLAM_SORU_SAYISI=10;
    private Handler handler;
    private  int quiz;
    private int toplamCevapSayisi,dogruCevapSayisi,kacinciDenemedeBildi;
    private View.OnClickListener butonDinleyecisi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            toplamCevapSayisi++;
            Button button=(Button)v;
            String verilenCevap=button.getText().toString();
            if (verilenCevap.equals(dogruCevap)){

            tebrikEt(kacinciDenemedeBildi);
            kacinciDenemedeBildi=1;
            butunButonlariEtkisizlestir();
            dogruCevapSayisi++;
            if (dogruCevapSayisi==TOPLAM_SORU_SAYISI){

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        dialog();
                    }
                },700);

            }
            else
            {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    sonrakiSoru();
                }
            },700);

            }


            }
            else
                {
                    kacinciDenemedeBildi++;
                    button.setEnabled(false);



            }

        }
    };

    private void dialog()
    {
        new AlertDialog.Builder(Quiz.this)
                .setTitle(" soruluk Testi Bitirdiniz")
                .setMessage(getResources().getString(R.string.skor,toplamCevapSayisi,(float)(1000/toplamCevapSayisi)))
                .setPositiveButton("Testi tekrar başlat", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetQuiz();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Şimdilik yeter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.cancel();
                    }
                }).show();


    }

    private void tebrikEt(int kacinciDenemedeBildi) {

        String tebrikMesaji="";
        switch (kacinciDenemedeBildi){
            case 1: tebrikMesaji="Mükemmelsin"; break;
            case 2: tebrikMesaji="İyisin";break;
            case 3: tebrikMesaji="Fena Değil";break;
            case 4: tebrikMesaji="Nihayet";break;
        }
        soru_tv.setText(tebrikMesaji);
        soru_tv.setTextColor(Color.BLUE);
        soru_tv.setTextSize(40);
    }

    private void butunButonlariEtkisizlestir() {
        for (int satir=0;satir<butonContainer.getChildCount();satir++)
        {
            Button button=(Button) butonContainer.getChildAt(satir);
            button.setEnabled(false);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

         quiz =getIntent().getIntExtra(MainActivity.QUIZ_KEY,MainActivity.MALZEME);
        genelListe=new ArrayList<String>();
        soruListesi=new ArrayList<String>();
        random= new Random();
        handler=new Handler();
        kacinciDenemedeBildi=1;

        soruContainer=(RelativeLayout) findViewById(R.id.rl);
        butonContainer=(LinearLayout) findViewById(R.id.buton_container);
        ilerleme_tv=(TextView) findViewById(R.id.ilerleme_tv);
        soru_tv=(TextView) findViewById(R.id.soru_tv);

        for (int satir=0;satir<butonContainer.getChildCount();satir++)
        {
            Button button=(Button) butonContainer.getChildAt(satir);
            button.setOnClickListener(butonDinleyecisi);
        }
        ilerleme_tv.setText("1 / "+TOPLAM_SORU_SAYISI);

        switch (quiz){
            /*case MainActivity.ALET:
                String[] dizi=getResources().getStringArray(R.array.ALET);
                for (String s:dizi){
                    genelListe.add(s);
                }
                break;*/
            case MainActivity.MALZEME:
                soru_tv.setTextSize(40);
                String[] dizi2=getResources().getStringArray(R.array.Malzemeler);
                for (String s:dizi2){
                    genelListe.add(s);
                }
                break;
            case MainActivity.FİİL:
                soru_tv.setTextSize(40);
                String[] dizi3=getResources().getStringArray(R.array.fiiller);
                for (String s:dizi3){
                    genelListe.add(s);
                }
                break;
            case MainActivity.SEBZE_MEYVE:
                soru_tv.setTextSize(40);
                String[] dizi4=getResources().getStringArray(R.array.sebze_meyve);
                for (String s:dizi4){
                    genelListe.add(s);
                }
                break;
            case MainActivity.MESLEKLER:
                soru_tv.setTextSize(40);
                String[] dizi5=getResources().getStringArray(R.array.meslekler);
                for (String s:dizi5){
                    genelListe.add(s);
                }
                break;
            case MainActivity.ZARFLAR:
                soru_tv.setTextSize(40);
                String[] dizi6=getResources().getStringArray(R.array.zarflar);
                for (String s:dizi6){
                    genelListe.add(s);
                }
                break;

        }

        resetQuiz();

    }

    private void resetQuiz() {

        toplamCevapSayisi=0;
        dogruCevapSayisi=0;
        soruListesi.clear();
        int soruSayisi=1;
        int listedekiElemanSayisi=genelListe.size();
        while (soruSayisi<=TOPLAM_SORU_SAYISI)
        {
            int index=random.nextInt(listedekiElemanSayisi);
            String soru=genelListe.get(index);
            if (!soruListesi.contains(soru))
            {
            soruListesi.add(soru);
            soruSayisi++;
            }

        }
        sonrakiSoru();

    }

    private void sonrakiSoru()
    {
        if (quiz==MainActivity.MALZEME)
        {
            soru_tv.setTextSize(64);
        }
        soru_tv.setTextColor(Color.parseColor("#444444"));
        ilerleme_tv.setText(dogruCevapSayisi+1+"/" +TOPLAM_SORU_SAYISI);
       String soru=soruListesi.remove(0);
       soru_tv.setText(getSoru(soru));
        dogruCevap=getCevap(soru);

        Collections.shuffle(genelListe);
        int dogruCevabinIndısi=genelListe.indexOf(soru);


        genelListe.add(genelListe.remove(dogruCevabinIndısi));
        for (int satir=0;satir<butonContainer.getChildCount();satir++)
        {
            Button button=(Button)butonContainer.getChildAt(satir);
            String secenek =genelListe.get(satir);
            button.setText(getCevap(secenek));
            button.setEnabled(true);
        }

            int rastgeleIndis=random.nextInt(4);
        ((Button)butonContainer.getChildAt(rastgeleIndis)).setText(dogruCevap);


    }



    private String getSoru(String soru) {
        soru=soru.substring(0,soru.indexOf("-"));
        return soru;
    }

    private String getCevap(String soru) {
        soru=soru.substring(soru.indexOf("-")+1,soru.length());
        return soru;
    }
}
