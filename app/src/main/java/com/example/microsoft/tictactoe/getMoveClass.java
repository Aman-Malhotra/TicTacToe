package com.example.microsoft.tictactoe;


import android.widget.Button;

import java.util.Random;

public class getMoveClass {

    String str ="";

    public int getMove(Button tempButtons[]) {
        // getting present state of table in form of string/////////////////////////////////
        str="";
        for (int i = 0; i < 9; i++) {
            String text = (String) tempButtons[i].getText();
            if(text.equals(" ")){
                str += "A";
            }
            else {
                str += text;
            }
        }
        int arr[]={0,1,2,3,4,5,6,7,8};
        Random rand = new Random();
        int getValue;
        while(true) {
            getValue = rand.nextInt(9);
            if(str.charAt(getValue)=='A'){
                break;
            }
        }

        return arr[getValue];// returning garbage value no importance
    }

}
