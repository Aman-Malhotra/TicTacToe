package com.example.microsoft.tictactoe;

import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.Random;

public class tossScreen extends AppCompatActivity{
    private Button toss1, toss2, play, back;
    private TextView tossResult ;
    private int win, x;
    private AdView mAdView;
    private String result="", str ="", audio="";
    public RelativeLayout tossLayout ;
    MediaPlayer mp;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toss_screen);

        // hiding the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Intent intent = this.getIntent();
        if(intent!=null){
            str = intent.getExtras().getString("playmode");
            audio = intent.getExtras().getString("audio");
//            Toast.makeText(this, "audio "+audio, Toast.LENGTH_SHORT).show();

        }
        mp = MediaPlayer.create(this, R.raw.click);
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        toss1 = findViewById(R.id.toss1);
        toss2 = findViewById(R.id.toss2);
        play = findViewById(R.id.play);
        back = findViewById(R.id.back);
        tossLayout = findViewById(R.id.tossLayout);
        tossResult = findViewById(R.id.tossResult);
        Random rand = new Random();
        win = 1+rand.nextInt(3);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, mainScreen.class);
        intent.putExtra("audio",audio);
        startActivity(intent);
        finish();
    }
    public void toss1(View view){
        if(audio.equals("unmute"))
            mp.start();
        x=1;
//        Toast.makeText(this, "win= "+win+" x= "+ x, Toast.LENGTH_SHORT).show();
        setText(view);
    }
    public void toss2(View view){
        if(audio.equals("unmute"))
            mp.start();
        x=2;
//        Toast.makeText(this, "win= "+win+" x= "+ x, Toast.LENGTH_SHORT).show();
        setText(view);
    }
    public void setText(View view ){
        if(win == x){
            if(x==1){
            toss1.setText("WON");
            toss2.setText("LOST");
            }
            else {
                toss1.setText("LOST");
                toss2.setText("WON");
            }
            tossResult.setText("You WON the toss");
            tossButtonUnclickable(toss1, toss2);
            tossLayout.removeView(back);
            result="win";
            play.setClickable(true);
        }
        else if(win != x){
            if(x==1){
                toss1.setText("LOST");
                toss2.setText("WON");
            }
            else {
                toss2.setText("LOST");
                toss1.setText("WON");
            }
            tossResult.setText("You LOST the toss");
            tossButtonUnclickable(toss1, toss2);
            tossLayout.removeView(back);
            result="lost";
            play.setClickable(true);
        }
    }
    public void play(View view){

        if(audio.equals("unmute"))
            mp.start();
        if(result.equals("")){
            Toast.makeText(this, "Toss before you start !!!", Toast.LENGTH_SHORT).show();
        }
        else {
            if(str.equals("pc")) {
                Intent intent = new Intent();
                intent.setClass(this, playWithPc.class);
                intent.putExtra("tossResult", result);
                intent.putExtra("audio", audio);
                startActivity(intent);
                finish();
            }
            else if (str.equals("friend")){
                Intent intent = new Intent();
                intent.setClass(this, playScreen.class);
                intent.putExtra("tossResult", result);
                intent.putExtra("audio", audio);
                startActivity(intent);
                finish();
            }
        }
    }
    public void tossButtonUnclickable(Button toss1, Button toss2){
        toss1.setClickable(false);
        toss2.setClickable(false);
    }
    public void backToMainScreen(View view){
        if(audio.equals("unmute"))
            mp.start();
        Intent intent = new Intent(this, mainScreen.class);
        intent.putExtra("audio",audio);
        startActivity(intent);
        finish();
    }


}
