package app.kobetribute;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView link = (TextView) findViewById(R.id.courseLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        //From https://www.youtube.com/watch?v=UIIpCt2S5Ls To get reference from text file
        TextView refTxt = (TextView) findViewById(R.id.referenceTxt);

        refTxt.setMovementMethod(new ScrollingMovementMethod());

        String data;

        StringBuffer sbuff = new StringBuffer();

        InputStream is = this.getResources().openRawResource(R.raw.image_ref);

        BufferedReader read = new BufferedReader(new InputStreamReader(is));

        if(is != null){

            try{
                while((data=read.readLine()) != null){
                    sbuff.append(data + "\n");
                }
                refTxt.setText(sbuff);
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

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

}
