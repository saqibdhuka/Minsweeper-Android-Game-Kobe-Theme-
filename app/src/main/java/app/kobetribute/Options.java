package app.kobetribute;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Options extends AppCompatActivity {

    Button save;
    Spinner mineSpinner, boardSizeSpinner;
    String board_row, board_col, mine_num, board;
    ArrayAdapter<String> boradSizedapter, mineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //From https://www.youtube.com/watch?v=urQp7KsQhW8
        boardSize();
        mineNum();

        saveBtn();
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

    private void saveBtn() {
        save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board = boardSizeSpinner.getSelectedItem().toString();
                Log.d("OPTIONS_SPINNER", board);
                getRowString();
                Log.d("ROW_GOT", board_row);

                getColString();
                Log.d("COL_GOT", board_col);

                mine_num = mineSpinner.getSelectedItem().toString();
                Log.d("MINE_GOT", mine_num);


                Intent saveIntent = new Intent(Options.this, MainActivity.class);
                Bundle saveBundle = new Bundle();
                saveBundle.putString("BOARD_ROW", board_row);
                saveBundle.putString("BOARD_COL", board_col);
                saveBundle.putString("MINE_NUM", mine_num);
                saveIntent.putExtras(saveBundle);
                startActivity(saveIntent);
            }
        });
    }

    private void getRowString(){
        int xPos = board.indexOf('x');
        board_row = board.substring(0, xPos -1);



//        for(int i =0; i < board.length(); i++){
//            if(board.substring(i) == " ") {
//                break;
//            }else {
//                board_row += board.substring(i);
//            }
//3
//        }
    }

    private void getColString(){

        int xPos = board.indexOf('x');
        board_col = board.substring(xPos +2);

//        board_col="";
//        for(int i =4; i < board.length(); i++){
//            if(board.substring(i) == " ")
//                break;
//            board_col += board.substring(i);
//        }
    }

    private void boardSize() {
        boardSizeSpinner = (Spinner) findViewById(R.id.layoutDropDown);

        boradSizedapter =  new ArrayAdapter<String>(Options.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.layout));

        boradSizedapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        boardSizeSpinner.setAdapter(boradSizedapter);
    }

    private void mineNum() {
        mineSpinner = (Spinner) findViewById(R.id.number_of_mines_dd);

        mineAdapter =  new ArrayAdapter<String>(Options.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.number_of_mines));

        mineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mineSpinner.setAdapter(mineAdapter);
    }

}
