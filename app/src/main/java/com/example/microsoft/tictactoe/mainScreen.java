package com.example.microsoft.tictactoe;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class mainScreen extends AppCompatActivity {
    MediaPlayer mp;
    String audio="unmute" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, "ca-app-pub-9134419366970461~6669007835");
        setContentView(R.layout.activity_main_screen);
        Intent intent = this.getIntent();
        if(intent!=null){
            audio = intent.getExtras().getString("audio");
//            Toast.makeText(this, "audio "+audio, Toast.LENGTH_SHORT).show();
            if(audio.equals("unmute")){
                ((Button)(findViewById(R.id.mute))).setText("Mute");
            }else if(audio.equals("mute")){
                ((Button)(findViewById(R.id.mute))).setText("Unmute");
            }
        }
        // hiding the sttus bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        mp = MediaPlayer.create(this, R.raw.click);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    public void playWithPc(View view){
        if(audio.equals("unmute"))
            mp.start();
        Intent tossPc = new Intent();
        tossPc.setClass(this, tossScreen.class);
        tossPc.putExtra("playmode", "pc");
        tossPc.putExtra("audio", audio);
        startActivity(tossPc);
        finish();

    }
    public void playWithFriend(View view){
        if(audio.equals("unmute"))
            mp.start();
        Intent tossFriend = new Intent();
        tossFriend.setClass(this, tossScreen.class);
        tossFriend.putExtra("playmode", "friend");
        tossFriend.putExtra("audio", audio);
        startActivity(tossFriend);
        finish();
    }
    public void muteEvent(View view){
        if(audio.equals("unmute")){
            mp.start();
            audio = "mute";
//            Toast.makeText(this, "audio "+audio, Toast.LENGTH_SHORT).show();
            ((Button)(findViewById(R.id.mute))).setText("Unmute");
        }else if(audio.equals("mute")){
            audio = "unmute";
//            Toast.makeText(this, "audio "+audio, Toast.LENGTH_SHORT).show();
            ((Button)(findViewById(R.id.mute))).setText("Mute");
        }
    }
}
