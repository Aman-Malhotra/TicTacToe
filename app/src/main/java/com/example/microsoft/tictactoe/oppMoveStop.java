package com.example.microsoft.tictactoe;


public class oppMoveStop {

    private char ch [] = new char[9];
    private char c ;
    public int getNextStep(String str, char c1){
        c=c1;
        for(int i=0;i<str.length();i++){
            ch[i]= str.charAt(i);
        }
        if( ifs(1,2,3) ){
            return getMove(str , 1, 2, 3);
        }else if( ifs(4,5,6) ){
            return getMove(str , 4, 5, 6);
        }else if( ifs(7,8,9) ){
            return getMove(str , 7, 8, 9);
        }else if( ifs(1,4,7) ){
            return  getMove(str , 1, 4, 7);
        }else if( ifs(2,5,8) ){
            return getMove(str , 2, 5, 8);
        }else if( ifs(3,6,9) ){
            return  getMove(str , 3, 6, 9);
        }else if( ifs(1,5,9) ){
            return getMove(str , 1, 5, 9);
        }else if( ifs(3,5,7) ){
            return  getMove(str , 3, 5, 7);
        }
        return -1;
    }

    public boolean ifs(int i, int j, int k){ // check whether at two positions there is an x already and one space is empty or not
        if( (ch[i-1]==c && ch[j-1]==c && ch[k-1]=='A')
            || (ch[j-1]==c && ch[k-1]==c && ch[i-1]=='A')
            || (ch[k-1]==c && ch[i-1]==c && ch[j-1]=='A') ){
            return true ;
        }
        else{
            return false;
        }
    }
    
    public int getMove(String str , int i, int j, int k){
        if( (ch[i-1]==c && ch[j-1]==c) ){
            return k-1;
        }
        else if( (ch[j-1]==c && ch[k-1]==c) ){
            return i-1;
        }
        else  if( (ch[k-1]==c && ch[i-1]==c) ){
            return j-1;
        }
        return -1;
    }

}
