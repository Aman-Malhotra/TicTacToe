package com.example.microsoft.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class playScreen extends AppCompatActivity  {
    private AdView mAdView;
    private RelativeLayout layout;
    public  int possibleMoves = 9, moves = 0;
    private Button buttons[];
    private String audio = "", str ="";
    private TextView  playResult;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        // hiding the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        layout = findViewById(R.id.playLayout);
        Intent intent = this.getIntent();
        if(intent!=null){
            audio = intent.getExtras().getString("audio");
//            Toast.makeText(this, "audio "+audio, Toast.LENGTH_SHORT).show();

        }
        buttons = new Button[9];
        buttons[0]= findViewById(R.id.a1);
        buttons[1]= findViewById(R.id.a2);
        buttons[2]= findViewById(R.id.a3);
        buttons[3]= findViewById(R.id.a4);
        buttons[4]= findViewById(R.id.a5);
        buttons[5]= findViewById(R.id.a6);
        buttons[6]= findViewById(R.id.a7);
        buttons[7]= findViewById(R.id.a8);
        buttons[8]= findViewById(R.id.a9);
        playResult = findViewById(R.id.playResult);
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mp = MediaPlayer.create(this, R.raw.click);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this, tossScreen.class);
        intent.putExtra("playmode", "friend");
        intent.putExtra("audio", audio);
        startActivity(intent);
        finish();
    }

    public void a1(View view){
        turns(buttons[0]);
    }
    public void a2(View view){
        turns(buttons[1]);
    }
    public void a3(View view){
        turns(buttons[2]);
    }
    public void a4(View view){
        turns(buttons[3]);
    }
    public void a5(View view){
        turns(buttons[4]);
    }
    public void a6(View view){
        turns(buttons[5]);
    }
    public void a7(View view){
        turns(buttons[6]);
    }
    public void a8(View view){
        turns(buttons[7]);
    }
    public void a9(View view){
        turns(buttons[8]);
    }
    public void turns(Button b){
        if(audio.equals("unmute"))
            mp.start();
        moves++;
        possibleMoves = 9 - moves;
            if (possibleMoves % 2 == 0) {
                b.setText("X");
                playResult.setText("Turn of O player");
                playResultText();
            } else {
                b.setText("O");
                playResult.setText("Turn of X player");
                playResultText();
            }
            b.setClickable(false);
//            for(int i =0; i<9; i++){
                if(buttons[0].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[2].getText())  && !buttons[0].getText().equals(" ")){
                    playResult.setText("Player "+buttons[0].getText()+" WIN$");
                    finishGame(0,1,2);
                }
                else if(buttons[3].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[5].getText())  && !buttons[3].getText().equals(" ")){
                    playResult.setText("Player "+buttons[3].getText()+" WIN$");
                    finishGame(3,4,5);
                }
                else if(buttons[6].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[8].getText())  && !buttons[6].getText().equals(" ")){
                    playResult.setText("Player "+buttons[6].getText()+" WIN$");
                    finishGame(6,7,8);
                }
                else if(buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText())  && !buttons[0].getText().equals(" ")){
                    playResult.setText("Player "+buttons[0].getText()+" WIN$");
                    finishGame(0,3,6);
                }
                else if(buttons[1].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[7].getText())  && !buttons[1].getText().equals(" ")){
                    playResult.setText("Player "+buttons[1].getText()+" WIN$");
                    finishGame(1,4,7);
                }
                else if(buttons[2].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[8].getText())  && !buttons[2].getText().equals(" ")){
                    playResult.setText("Player "+buttons[2].getText()+" WIN$");
                    finishGame(2,5,8);
                }
                else if(buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())  && !buttons[0].getText().equals(" ")){
                    playResult.setText("Player "+buttons[0].getText()+" WIN$");
                    finishGame(0,4,8);
                }
                else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText()) && !buttons[2].getText().equals(" ")){
                    playResult.setText("Player "+buttons[2].getText()+" WIN$");
                    finishGame(2,4,6);
                }
//            }
    }
    public void playResultText(){
        str = "";
        for (int i = 0; i < 9; i++) {
            String text = (String) buttons[i].getText();
            if (text.equals(" ")) {
                str += "A";
            } else {
                str += text;
            }
        }
        int countSpace = 0;
        for (int i = 0; i < 9; i++) {
            if (str.charAt(i) == 'A') {
                countSpace++;
            }
        }
        if(countSpace==0){
            playResult.setText("Play Again Or Toss Again!!");
        }
    }
    public void finishGame(int i1, int i2, int i3){
        for(Button button : buttons) {
            button.setClickable(false);
        }
        buttons[i1].setBackgroundColor(Color.GRAY);
        buttons[i1].setTextColor(Color.WHITE);
        buttons[i2].setBackgroundColor(Color.GRAY);
        buttons[i2].setTextColor(Color.WHITE);
        buttons[i3].setBackgroundColor(Color.GRAY);
        buttons[i3].setTextColor(Color.WHITE);
//        Toast.makeText(this, i1+" "+i2+" "+i3, Toast.LENGTH_SHORT).show();
    }
    public void mainScreen(View view){
        if(audio.equals("unmute"))
            mp.start();

        Intent intent = new Intent(this, mainScreen.class);
        intent.putExtra("audio",audio);
        startActivity(intent);
        finish();
    }
    public void tossScreen(View view){
        if(audio.equals("unmute"))
            mp.start();
        Intent intent = new Intent();
        intent.setClass(this, tossScreen.class);
        intent.putExtra("playmode", "friend");
        intent.putExtra("audio", audio);
        startActivity(intent);
        finish();
    }
    public void refreshPage(View view){
        if(audio.equals("unmute"))
            mp.start();
        Intent intent = new Intent();
        intent.setClass(this, playScreen.class);
        intent.putExtra("playmode", "friend");
        intent.putExtra("audio", audio);
        startActivity(intent);
        finish();


    }
}
