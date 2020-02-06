package app.model;

import android.util.Log;

import java.lang.reflect.Array;

import java.util.Random;


public class Board {
    /*
    This class creates the board with default selected board size as 4 x 6 and number of mine as 6.
    It keep tracks of the board, where the images are placed and treats the board as a 2D array.
     */

    public Random randGen = new Random();
    private int mineCount;
    private int row;
    private int column;
    private int[][] boardArr;

    public Board(){
        row =4;
        column = 6;
        mineCount = 6;
        boardArr = new int[row][column];
    }

    public Board(int r, int c, int num) {
        row = r;
        column= c;
        mineCount = num;
        boardArr = new int[row][column];
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int[][] getBoardArr() {
        return boardArr;
    }

    public int getBoardArrayVal(int r, int c){return boardArr[r][c];}

    public void setBoardArr(int[][] boardArr) {
        this.boardArr = boardArr;
    }


    //From https://www.geeksforgeeks.org/generating-random-numbers-in-java/
    public void setupboard(){

        //setting up the hidden images places.
        int randRow, randCol;
        boolean flag;
        for (int count  = 0; count < mineCount; count++){
            flag = true;
            while(flag == true){

                randRow = randGen.nextInt(getRow());
                randCol = randGen.nextInt(getColumn());

                Log.d("BoardClass", "randRow: " + randRow);
                Log.d("BoardClass", "randCol: " + randCol);
                if(boardArr[randRow][randCol] != -1){
                    boardArr[randRow][randCol] = -1;
                    flag = false;
                }
            }

        }

    }

    public void assignBoard(){
        //Assign numbers to the array
        for(int r =0; r < row; r++){
            for(int c=0; c< column;  c++){
                if(boardArr[r][c] != -1){
                    int countNumber=0;
                    //check row
                    for(int i = 0; i < column; i++){
                        if(boardArr[r][i] == -1){
                            countNumber++;
                        }
                    }
                    //check column
                    for(int j = 0; j < row; j++){
                        if(boardArr[j][c] == -1){
                            countNumber++;
                        }
                    }
                    //assign count
                    boardArr[r][c] = countNumber;
                }
            }
        }
    }


}
