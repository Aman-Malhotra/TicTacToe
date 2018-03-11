package com.example.microsoft.tictactoe;

import android.widget.Button;

public class gameOverCheckClass {

    public boolean gameOverCheck( Button buttons[], int i1, int i2, int i3){
        if (buttons[i1].getText().equals(buttons[i2].getText())
                && buttons[i2].getText().equals(buttons[i3].getText())
                && !buttons[i1].getText().equals(" ")) {
            return true;
        }else{
            return false ;
        }
    }

}
