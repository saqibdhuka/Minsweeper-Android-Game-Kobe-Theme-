package app.kobetribute;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //From https://www.youtube.com/watch?v=urQp7KsQhW8
        Spinner dropDpown = (Spinner) findViewById(R.id.layoutDropDown);

        ArrayAdapter<String> ddAdapter =  new ArrayAdapter<String>(Options.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.layout));

        ddAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropDpown.setAdapter(ddAdapter);

    }

}
