package app.kobetribute;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.model.Board;

public class Game extends AppCompatActivity {

    int row, col, mineNo, mineFound, scanUsed;
    TextView totalMine, foundMine, scans;
    Board newBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalMine = (TextView) findViewById(R.id.setMineTotal);
        foundMine = (TextView) findViewById(R.id.setMineFound);
        scans = (TextView) findViewById(R.id.scansUsed);


        setupButtonGrid();

    }

    private void setupButtonGrid() {

        setup();
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
                final Button FINAL_BTN = button;
                button.setLayoutParams(new TableRow.LayoutParams(//Stretching buttons
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonClick(FINAL_BTN, FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
            }
        }
    }

    private void buttonClick(Button btn, int rowNum, int rowCol){
        if(newBoard.getBoardArrayVal(rowNum, rowCol) != -1){
            btn.setText(Integer.toString(newBoard.getBoardArrayVal(rowNum,rowCol)));
            scanUsed++;
            setupText(scans, scanUsed);
        }else{
//            btn.setBackgroundResource(R.drawable.kobe_mural); // Does not scale image so DONOT USE!!
            //scale image to button
            int newWidth = btn.getWidth();
            int newHeight = btn.getHeight();
            Bitmap originalBM = BitmapFactory.decodeResource(getResources(), R.drawable.revealed_image_png);
            Bitmap scaledBM = Bitmap.createScaledBitmap(originalBM, newWidth, newHeight, true);
            Resources newResource = getResources();
            btn.setBackground(new BitmapDrawable(newResource, scaledBM));

            mineFound++;
            setupText(foundMine, mineFound);
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
