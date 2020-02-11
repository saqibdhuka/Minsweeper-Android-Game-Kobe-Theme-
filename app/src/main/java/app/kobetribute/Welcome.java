package app.kobetribute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

    public class Welcome extends AppCompatActivity {

    private static int TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeScreen = new Intent(Welcome.this, MainActivity.class);
                startActivity(welcomeScreen);
                finish();
            }
        }, TIME_OUT);
    }
}
