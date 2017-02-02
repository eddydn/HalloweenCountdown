package dev.edmt.halloweencountdown;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {

    public static final int[] IMAGES;
    static {
        IMAGES = new int[]{
                R.drawable.splash_1,
                R.drawable.splash_2,
                R.drawable.splash_3,
                R.drawable.splash_4,
                R.drawable.splash_5,
                R.drawable.splash_6,
                R.drawable.splash_7,
                R.drawable.splash_8,
                R.drawable.splash_9,
                R.drawable.splash_10,

                R.drawable.splash_11,
                R.drawable.splash_12,
                R.drawable.splash_13,
                R.drawable.splash_14,
                R.drawable.splash_15,
                R.drawable.splash_16,
                R.drawable.splash_17,
                R.drawable.splash_18,
                R.drawable.splash_19,
                R.drawable.splash_20,

        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Random random = new Random(System.currentTimeMillis());
        for(int image:IMAGES){
            imageView.setImageResource(IMAGES[random.nextInt(IMAGES.length-1)]);

        }
        new Handler().postDelayed(new MyHandler(),3000); // 3 seconds to start MainActivity

    }

    class MyHandler implements  Runnable{
        public MyHandler() {
        }
        public void run(){
            gotoTransactionActivity();
        }
    }

    private void gotoTransactionActivity() {
        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        super.finish();
    }


}
