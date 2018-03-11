package com.example.microsoft.tictactoe;


public class myWinMove {

    private char ch [] = new char[9];
    private char c;
    public int getMyWinMove(String str, char c1){
        c = c1;
        for(int i=0;i<str.length();i++){
            ch[i]= str.charAt(i);
        }
        if( ifs(1,2,3) ){
            return getMove( 1, 2, 3);
        }else if( ifs(4,5,6) ){
            return getMove( 4, 5, 6);
        }else if( ifs(7,8,9) ){
            return getMove( 7, 8, 9);
        }else if( ifs(1,4,7) ){
            return  getMove( 1, 4, 7);
        }else if( ifs(2,5,8) ){
            return getMove( 2, 5, 8);
        }else if( ifs(3,6,9) ){
            return  getMove( 3, 6, 9);
        }else if( ifs(1,5,9) ){
            return getMove( 1, 5, 9);
        }else if( ifs(3,5,7) ){
            return  getMove( 3, 5, 7);
        }
        return -1;
    }

    public boolean ifs(int i, int j, int k){ // check whether at two positions there is an x already and one space is empty or not
        if( (ch[i-1]=='O' && ch[j-1]=='O' && ch[k-1]=='A')
                || (ch[j-1]=='O' && ch[k-1]=='O' && ch[i-1]=='A')
                || (ch[k-1]=='O' && ch[i-1]=='O' && ch[j-1]=='A') ){
            return true ;
        }
        else{
            return false;
        }
    }

    public int getMove( int i, int j, int k){
        if( (ch[i-1]=='O' && ch[j-1]=='O') ){
            return k-1;
        }
        else if( (ch[j-1]=='O' && ch[k-1]=='O') ){
            return i-1;
        }
        else  if( (ch[k-1]=='O' && ch[i-1]=='O') ){
            return j-1;
        }
        return -1;
    }

}
