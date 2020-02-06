package app.kobetribute;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String boardRow, boardCol, mineNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playGame();
        options();
        help();


    }

    private void getOptionIntent() {
        Intent optionsIntent = getIntent();
        Bundle extras = optionsIntent.getExtras();
        if(extras != null) {
            boardRow = extras.getString("BOARD_ROW");
            boardCol = extras.getString("BOARD_COL");
            mineNumber = extras.getString("MINE_NUM");
        }else{
            boardRow = "4";
            boardCol = "6";
            mineNumber = "6";
        }
    }

    private void help(){
        Button hlp = (Button) findViewById(R.id.helpBtn);
        hlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hlpIntent = new Intent(MainActivity.this, Help.class);
                startActivity(hlpIntent);
            }
        });

    }
    private  void options(){
        Button opt = (Button) findViewById(R.id.optionBtn);
        opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optIntent = new Intent(MainActivity.this, Options.class);
                startActivity(optIntent);
            }
        });

    }

    private void playGame() {
        Button plyBtn = (Button) findViewById(R.id.playBtn);
        plyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOptionIntent();
                Intent plyIntent = new Intent(MainActivity.this, Game.class);
                Bundle extrasGame = new Bundle();
                extrasGame.putString("ROW", boardRow);
                extrasGame.putString("COL", boardCol);
                extrasGame.putString("MINE", mineNumber);
                plyIntent.putExtras(extrasGame);
                startActivity(plyIntent);
            }
        });
    }


}
