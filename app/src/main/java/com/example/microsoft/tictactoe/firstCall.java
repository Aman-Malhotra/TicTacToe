package com.example.microsoft.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Microsoft on 2/10/2018.
 */

public class firstCall extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String audio = "unmute";
        Intent intent = new Intent(this, mainScreen.class);
        intent.putExtra("audio",audio);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, firstCall.class);
        startActivity(intent);
    }
}
