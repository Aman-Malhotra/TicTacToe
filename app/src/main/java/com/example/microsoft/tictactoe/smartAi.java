package com.example.microsoft.tictactoe;


import android.widget.Toast;

class smartAi {

    int arr[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public int tryToWinMove(String str){

        //str is in the format AOAXAAOAX where a stands for a space and O and X are players
        int countA=0, countO=0, countX=0, smartPos=10;

        for(int i=0;i<8;i++){
            for(int j=0;j<3;j++){
                if(str.charAt(arr[i][j])=='A'){
                    countA++;// check how many empty spaces are there in a single line
                }
                if(str.charAt(arr[i][j])=='X'){
                    countX++;
                }
            }
            // get the positions of two empty spaces
            if(countA==2 && countX==0){
                for(int j=0;j<3;j++){
                    if(str.charAt(arr[i][j])=='A'){
                        smartPos = arr[i][j];
                        // sending an empty location to check if that could be a smart move or not
//                        int smartCheck = checkSmartMove(str, i, j);// and will be returned in the form of 1 or 0 if 0 then not a smart move if 1 then a smart move
//                        if(smartCheck==1){
//                            smartPos = arr[i][j];
//                        }
                    }
                }
            }
            countA=0;
            countO=0;
            countX=0;
        }
        return smartPos;
    }

    private int checkSmartMove(String str, int i1, int j1) {
        int countA=0, countO1=0, countO2=0, check=0;
        String newStr="" ;
        int pos = arr[i1][j1];
        // making the new string after writing 'O' on the position that we recieved from the above loops
        for(int s=0;s<str.length();s++){
            if(s==pos){
                newStr += 'O';
            }
            else{
                newStr += str.charAt(s);
            }
        }
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 3; j++) {
                if(str.charAt(arr[i][j])=='O'){
                   countO1++;// count of 'O' in old string
                }
                if(newStr.charAt(arr[i][j])=='O'){
                    countO2++;// count of 'O' in new string
                }
            }
            if(countO2>countO1){
                check++;
            }
            countO1=0;
            countO2=0;
        }
        if(check>1) {
            return 1;
        }
        else{
            return 0;
        }
    }

}

















