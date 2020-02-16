package app.kobetribute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity {


    private static int TIME_OUT = 5000;
    Handler handler;
    Animation top, bottom, fade, slide;
    TextView title, author;
    ImageView background, logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        background = (ImageView) findViewById(R.id.mambaBckgrnd);
        logo = (ImageView) findViewById(R.id.kobeLogo);
        title = (TextView) findViewById(R.id.title);
        author = (TextView) findViewById(R.id.author);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom  = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        slide = AnimationUtils.loadAnimation(this, R.anim.slide_in_anim);

        background.startAnimation(fade);
        logo.startAnimation(top);
        title.startAnimation(slide);
        author.startAnimation(bottom);

        handle();

        skipBtn();
    }

    private void handle() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMain();
            }
        }, TIME_OUT);
    }

    private void startMain() {
        Intent welcomeScreen = new Intent(Welcome.this, MainActivity.class);
        startActivity(welcomeScreen);
        finish();
    }

    private void skipBtn() {
            FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.skip);
            myFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(handler != null)
                        handler.removeCallbacksAndMessages(null);
                    startMain();
                }
            });
        }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // clear handler on stop
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
    }

    }


