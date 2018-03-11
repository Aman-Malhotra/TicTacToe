package com.example.microsoft.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class playWithPc extends AppCompatActivity  {

    Button buttons[];
    String str = "AAAAAAAAA", tossR="", audio="";
    int moves =0, possibleMoves = 9;
    private AdView mAdView;
    MediaPlayer mp;

    TextView playResult;
    private  boolean finish = false ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        // hiding the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Intent intent = this.getIntent();
        if(intent!=null){
            tossR = intent.getExtras().getString("tossResult");
            audio = intent.getExtras().getString("audio");
//            Toast.makeText(this, "audio "+audio, Toast.LENGTH_SHORT).show();

//            Toast.makeText(this, "User "+ tossR, Toast.LENGTH_SHORT).show();

        }
        buttons = new Button[9];
        buttons[0] = findViewById(R.id.a1);
        buttons[1] = findViewById(R.id.a2);
        buttons[2] = findViewById(R.id.a3);
        buttons[3] = findViewById(R.id.a4);
        buttons[4] = findViewById(R.id.a5);
        buttons[5] = findViewById(R.id.a6);
        buttons[6] = findViewById(R.id.a7);
        buttons[7] = findViewById(R.id.a8);
        buttons[8] = findViewById(R.id.a9);
        playResult = findViewById(R.id.playResult);
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mp = MediaPlayer.create(this, R.raw.click);
        if(tossR.equals("lost")){
            Random rand = new Random();
            int random = rand.nextInt(9);
//            Toast.makeText(this, " You lost the toss " , Toast.LENGTH_SHORT).show();
            turns(buttons[random], buttons);
        }

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

    public void a1(View view) {
        turns(buttons[0], buttons);
    }
    public void a2(View view) {
        turns(buttons[1], buttons);
    }
    public void a3(View view) {
        turns(buttons[2], buttons);
    }
    public void a4(View view) {
        turns(buttons[3], buttons);
    }
    public void a5(View view) {
        turns(buttons[4], buttons);
    }
    public void a6(View view) {
        turns(buttons[5], buttons);
    }
    public void a7(View view) {
        turns(buttons[6], buttons);
    }
    public void a8(View view) {
        turns(buttons[7], buttons);
    }
    public void a9(View view) {
        turns(buttons[8], buttons);
    }
    public void turns(Button b, Button tempButtons[]) {
        if(audio.equals("unmute"))
            mp.start();
        moves++;
        possibleMoves = 9 - moves;
        // game over function call
        gameOverFuncMain(playResult, tempButtons);

        switch(tossR){
            case "lost":{

                if (possibleMoves % 2 == 0) {
                    // game over check function call
                    gameOverFuncMain(playResult, tempButtons);
                    if (finish != true && str.contains("A")) {
                        b.setText("O");
                        b.setClickable(false);
                    }

                    str = "";
                    for (int i = 0; i < 9; i++) {
                        String text = (String) tempButtons[i].getText();
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
//                    Toast.makeText(this, "Space = "+countSpace, Toast.LENGTH_SHORT).show();
                    if(countSpace==0){
//                        Toast.makeText(this, "Entering this fucking shit", Toast.LENGTH_SHORT).show();
                        playResult.setText("Play Again Or Toss Again!!");
                    }
                }

                ///////////////////////////////////Using 'X' for User and 'O' for PC/////////////////////////////////////
                else {
                    // game over check function call
                    gameOverFuncMain(playResult, tempButtons);
                    b.setText("X");
                    b.setClickable(false);
                    str = "";
                    for (int i = 0; i < 9; i++) {
                        String text = (String) tempButtons[i].getText();
                        if (text.equals(" ")) {
                            str += "A";
                        } else {
                            str += text;
                        }
                    }
                    ////////////////////////checking and getting the button number to stop opponents winning move //////////////////////
                    int myWinMove = (new myWinMove()).getMyWinMove(str, 'O');
                    ////////////////////////checking and getting the button number to stop opponents winning move //////////////////////
                    int opponentBlock1 = (new oppMoveStop()).getNextStep(str, 'X');

//                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    int tryToWinMove = (new smartAi()).tryToWinMove(str);

                    int countA = 0;
                    for (int i = 0; i < 9; i++) {
                        if (str.charAt(i) == 'A') {
                            countA++;
                        }
                    }
                    // game over check function call
                    gameOverFuncMain(playResult, tempButtons);
                    if (countA > 0) {

                        if (myWinMove >= 0) {
                            buttons[myWinMove].performClick();
                        } else if (opponentBlock1 >= 0) {
                            buttons[opponentBlock1].performClick();
                        } else if (tryToWinMove >= 0 && tryToWinMove <= 8) {
//                            Toast.makeText(this, "trying to win "+tryToWinMove, Toast.LENGTH_SHORT).show();
                            buttons[tryToWinMove].performClick();
                        } else {
                            int getNextMove = (new getMoveClass()).getMove(tempButtons);
                            buttons[getNextMove].performClick();
                        }

                    }
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    // game over function call
                    gameOverFuncMain(playResult, tempButtons);
                }
            }
            break;
            case "win":{
                if (possibleMoves % 2 == 0) {

                    // game over check function call
                    gameOverFuncMain(playResult, tempButtons);

                    b.setText("X");
                    b.setClickable(false);
                    str = "";
                    for (int i = 0; i < 9; i++) {
                        String text = (String) tempButtons[i].getText();
                        if (text.equals(" ")) {
                            str += "A";
                        } else {
                            str += text;
                        }
                    }
                    ////////////////////////checking and getting the button number to stop opponents winning move //////////////////////
                    int myWinMove = (new myWinMove()).getMyWinMove(str, 'O');
                    ////////////////////////checking and getting the button number to stop opponents winning move //////////////////////
                    int opponentBlock1 = (new oppMoveStop()).getNextStep(str, 'X');

                    int tryToWinMove = (new smartAi()).tryToWinMove(str);

                    int countA = 0;
                    for (int i = 0; i < 9; i++) {
                        if (str.charAt(i) == 'A') {
                            countA++;
                        }
                    }
                    // game over check function call
                    gameOverFuncMain(playResult, tempButtons);
                    if (countA > 1) {

                        if (myWinMove >= 0) {
//                        Toast.makeText(this, " Winning move " + myWinMove, Toast.LENGTH_SHORT).show();
                            buttons[myWinMove].performClick();
                        } else if (opponentBlock1 >= 0) {
//                        Toast.makeText(this, " Opponent Block Move " + opponentBlock1, Toast.LENGTH_SHORT).show();
                            buttons[opponentBlock1].performClick();
                        } else if (tryToWinMove >= 0 && tryToWinMove <= 8) {
//                            Toast.makeText(this, "trying to win "+tryToWinMove, Toast.LENGTH_SHORT).show();
                            buttons[tryToWinMove].performClick();
                        } else {
//                        int getNextMove = getMove(tempButtons);
                            int getNextMove = (new getMoveClass()).getMove(tempButtons);
//                        Toast.makeText(this, " No imp move " + getNextMove, Toast.LENGTH_SHORT).show();

                            buttons[getNextMove].performClick();
                        }
                    } else {
                        playResult.setText("Toss Again To Play!!");
                    }
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    // game over function call
                    gameOverFuncMain(playResult, tempButtons);
                }

                ///////////////////////////////////Using 'X' for User and 'O' for PC/////////////////////////////////////
                else {

                    if (finish != true && str.contains("A")) {


                        b.setText("O");

                        b.setClickable(false);
                    }
                }
            }
            break;
            default:{
                Toast.makeText(this, "Can't find any step", Toast.LENGTH_SHORT).show();
            }
        }




    }
    public void gameOverFuncMain(TextView playResult, Button buttons[]) {
        int possibleWays[][]= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}} ;
            for(int i =0;i<8;i++){
                if ((new gameOverCheckClass()).gameOverCheck(buttons, possibleWays[i][0],possibleWays[i][1],possibleWays[i][2])) {
                    playResult.setText("Player " + buttons[possibleWays[i][0]].getText() + " WIN$");
                    finishGame(possibleWays[i][0], possibleWays[i][1], possibleWays[i][2]);
                }
            }
    }
    public void finishGame(int i1, int i2, int i3) {
        finish = true;
        for (Button button : buttons) {
            button.setClickable(false);
        }
        buttons[i1].setBackgroundColor(Color.GRAY);
        buttons[i1].setTextColor(Color.WHITE);
        buttons[i2].setBackgroundColor(Color.GRAY);
        buttons[i2].setTextColor(Color.WHITE);
        buttons[i3].setBackgroundColor(Color.GRAY);
        buttons[i3].setTextColor(Color.WHITE);
//        finish();
    }
    public void mainScreen(View view) {
        if(audio.equals("unmute"))
            mp.start();
        Intent intent = new Intent(this, mainScreen.class);
        intent.putExtra("audio", audio);
        startActivity(intent);
        finish();
    }
    public void tossScreen(View view) {
        if(audio.equals("unmute"))
            mp.start();
        Intent intent = new Intent();
        intent.setClass(this, tossScreen.class);
        intent.putExtra("playmode", "pc");
        intent.putExtra("audio", audio);
        startActivity(intent);
        finish();
    }
    public void refreshPage(View view){
        if(audio.equals("unmute"))
            mp.start();
        Intent intent = new Intent();
        intent.setClass(this, playWithPc.class);
        intent.putExtra("tossResult", tossR);
        intent.putExtra("audio", audio);
        startActivity(intent);
        finish();
    }

}