package app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void getMineCount() {
        Board b = new Board(4,6,6);
        assertEquals(6,b.getMineCount());
    }

    @Test
    void setMineCount() {
        Board b = new Board(4,6,6);
        b.setMineCount(10);
        assertEquals(10,b.getMineCount());
    }

    @Test
    void getRow() {
        Board b = new Board(4,6,6);
        assertEquals(4,b.getRow());
    }

    @Test
    void setRow() {
        Board b = new Board(4,6,6);
        b.setRow(10);
        assertEquals(10,b.getRow());
    }

    @Test
    void getColumn() {
        Board b = new Board(4,6,6);
        assertEquals(6,b.getColumn());
    }

    @Test
    void setColumn() {
        Board b = new Board();
        b.setColumn(10);
        assertEquals(10,b.getColumn());
    }

    @Test
    void getBoardArr() {
    }

    @Test
    void setupboard() {
        Board b = new Board(5,5,15);
        b.setupboard();
        int countMines=0;
        for(int r=0; r< b.getRow(); r++){
            for(int c=0; c<b.getColumn();c++){
                if(b.getBoardArrayVal(r,c) == -1)
                    countMines++;
            }
        }

        assertEquals(15, countMines);
    }

    @Test
    void assignBoard() {
        Board b = new Board(4,6,6);
        b.setBoardArrayVal(0,0,-1);
        b.setBoardArrayVal(0,1,-1);
        b.setBoardArrayVal(0,2,-1);
        b.setBoardArrayVal(0,3,-1);
        b.setBoardArrayVal(0,4,-1);
        b.setBoardArrayVal(0,5,-1);

        b.assignBoard();
        int allValueCorrect = 1;//true
        for(int r=1; r< b.getRow() && allValueCorrect == 1; r++) {
            for (int c = 0; c < b.getColumn() && allValueCorrect == 1; c++) {
                if(b.getBoardArrayVal(r,c) != 1){
                    allValueCorrect = -1;//false
                }
            }
        }

        assertEquals(1,allValueCorrect);

    }
}