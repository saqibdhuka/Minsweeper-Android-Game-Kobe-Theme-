package app.kobetribute;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.model.Board;

public class Game extends AppCompatActivity {

    int row, col, mineNo, mineFound, scanUsed;
    int TIME = 1500;
    TextView totalMine, foundMine, scans;
    Board newBoard;
    Handler handler;
    int boardButton[][];
    Button buttonArr[][];
    MediaPlayer mineSound, scanSound;
    Button newBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mineSound = MediaPlayer.create(this, R.raw.chaChing);
        scanSound = MediaPlayer.create(this, R.raw.scan);
        totalMine = (TextView) findViewById(R.id.setMineTotal);
        foundMine = (TextView) findViewById(R.id.setMineFound);
        scans = (TextView) findViewById(R.id.scansUsed);

        setupButtonGrid();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public void setupBoardButtonArr(int r, int c){
        for (int i =0 ; i< r; i++){
            for(int j =0; j< c; j++){
                boardButton[i][j] = 0;
            }
        }
    }
    private void setupButtonGrid() {

        setup();
        buttonArr = new Button[row][col];
        boardButton = new int[row][col];
        setupBoardButtonArr(row, col);
        mineFound =0;
        scanUsed = 0;

        setupText(totalMine, mineNo);
        setupText(foundMine, mineFound);
        setupText(scans, scanUsed);


        //Setup table
        TableLayout btnTable = (TableLayout) findViewById(R.id.buttonTable);
        for(int r = 0; r < row; r++){
            //Setup new row inside table
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams( // Streching row
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));// TableLayout.LayoutParams(width attr, height attr, scaling);
            btnTable.addView(tableRow);

            for(int c = 0; c < col; c++){
                //Add button and add that button to tabelRow
                Button button = new Button(this);
                final int FINAL_ROW = r;
                final int FINAL_COL = c;
                button.setLayoutParams(new TableRow.LayoutParams(//Stretching buttons
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonClick(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttonArr[r][c] = button;
            }
        }
    }

    private void buttonClick(int rowNum, int colNum){
        Button btn = buttonArr[rowNum][colNum];
        if(boardButton[rowNum][colNum] == 2){
            btn.setText(Integer.toString(newBoard.getBoardArrayVal(rowNum,colNum)));
            btn.setTextColor(getApplication().getResources().getColor(R.color.white));
            btn.setTextSize(20);
            scanUsed++;
            scan(rowNum, colNum);
            setupText(scans, scanUsed);
            boardButton[rowNum][colNum]++;
            return;
        }else if(boardButton[rowNum][colNum] == 1){
            return;
        }
        if(boardButton[rowNum][colNum] == 0) {
            if (newBoard.getBoardArrayVal(rowNum, colNum) > -1) {
                btn.setText(Integer.toString(newBoard.getBoardArrayVal(rowNum, colNum)));
                btn.setTextColor(getApplication().getResources().getColor(R.color.black));
                btn.setTextSize(20);
                scanUsed++;
                boardButton[rowNum][colNum] = 1;
                scan(rowNum, colNum);
                setupText(scans, scanUsed);
            } else {
                //Lock Button
                lockBtn();

                //scale image to button
                int newWidth = btn.getWidth();
                int newHeight = btn.getHeight();
                Bitmap originalBM = BitmapFactory.decodeResource(getResources(), R.drawable.revealed_image_png);
                Bitmap scaledBM = Bitmap.createScaledBitmap(originalBM, newWidth, newHeight, true);
                Resources newResource = getResources();
                mineSound.start();
                btn.setBackground(new BitmapDrawable(newResource, scaledBM));

                boardButton[rowNum][colNum] = 2;
                newBoard.setBoardArrayVal(rowNum, colNum, -5);
                newBoard.assignBoard();

                updateValues();

                mineFound++;
                if (mineFound == mineNo) {
                    gameEnd();
                }
                setupText(foundMine, mineFound);
            }
        }

    }

    private void scan(int r, int c) {
        scanSound.start();
        for(int i=0; i < row; i++){
            scanAnimation(i, c);
        }

        for(int j = 0; j < col; j++){
            scanAnimation(r,j);
        }
    }

    public void scanAnimation(int rNum, int cNum){
        handler = new Handler();

        TIME = 300;
        newBtn = buttonArr[rNum][cNum];
        Animation fade_btn = AnimationUtils.loadAnimation(this, R.anim.button_anim);
        newBtn.setAnimation(fade_btn);
        if(boardButton[rNum][cNum] == 1)
            newBtn.setBackgroundResource(android.R.drawable.btn_default);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                newBtn.setBackgroundResource(android.R.drawable.btn_default);
//
//            }
//        },0);
    }

    public void gameEnd(){
        openDialog();

    }

    private void openDialog() {
        DialogBox gameEnd = new DialogBox();
        gameEnd.show(getSupportFragmentManager(), "Game End");

    }

    private void updateValues() {
        for(int r =0; r < row; r++){
            for(int c =0; c< col; c++){
                if(boardButton[r][c] > 0 && newBoard.getBoardArrayVal(r,c) > -1){
                    buttonArr[r][c].setText(Integer.toString(newBoard.getBoardArrayVal(r,c)));
                }
            }
        }
    }

    private void lockBtn(){
        for (int lockRow = 0; lockRow < row; lockRow++){
            for (int lockCol = 0; lockCol < col; lockCol++){
                Button btnNew = buttonArr[lockRow][lockCol];

                int width = btnNew.getWidth();
                btnNew.setMinWidth(width);
                btnNew.setMaxWidth(width);

                int height = btnNew.getHeight();
                btnNew.setMinHeight(height);
                btnNew.setMaxHeight(height);

            }
        }
    }

    private void setupText(TextView txt, int val){
        txt.setText(Integer.toString(val));
    }
    private void setup() {
        recieveIntent();
        newBoard = new Board(row, col, mineNo);
        newBoard.setupboard();
        newBoard.assignBoard();
    }

    private void recieveIntent() {

        Intent newIntent = getIntent();
        Bundle newExtra = newIntent.getExtras();

        row = Integer.valueOf(newExtra.getString("ROW"));
        col = Integer.valueOf(newExtra.getString("COL"));
        mineNo = Integer.valueOf(newExtra.getString("MINE"));

    }

}
