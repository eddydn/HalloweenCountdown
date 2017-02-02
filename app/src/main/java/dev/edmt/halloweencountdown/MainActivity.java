package dev.edmt.halloweencountdown;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtDaysRemain,txtLongString;

    BackgroundSound bgSound = new BackgroundSound();


    @Override
    protected void onResume() {
        super.onResume();
        bgSound.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"blood-cyrillic.ttf",true);

        //Control
        imageView = (ImageView)findViewById(R.id.pumbkin);
        ((AnimationDrawable)imageView.getDrawable()).start();
        txtDaysRemain = (TextView)findViewById(R.id.txtDaysRemain);
        txtLongString = (TextView)findViewById(R.id.txtLongString);

        startTimer();


    }

    private void startTimer() {
        long differance = getRemainDays();
        new CountDownTimer(differance,1000) // 1 seconds
        {
            public void onTick(long millisUntilFinished){
                int days = (int)(millisUntilFinished/(1000*60*60*24));
                int hours = (int) ((millisUntilFinished/(1000*60*60))%24);
                int mins = (int)(millisUntilFinished/(1000*60)%60);
                int sec = (int)(millisUntilFinished/(1000)%60);

                txtDaysRemain.setText(String.format("%d",days));
                txtLongString.setText(String.format("%d DAYS %02d:%02d:%02d",days,hours,mins,sec));
            }
            public void onFinish(){
                //Done
            }
        }.start();
    }

    private long getRemainDays() {
        Date currentDate = new Date();
        Date endDate;
        if(currentDate.getMonth() <= 9) // because month in Date are 0-11 (instance of 1~12 in realistic)
        {
            endDate = new Date(currentDate.getYear(),9,31); // equals currentDate().getYear,10,31 in realistic
        }
        else
        {
            endDate = new Date(currentDate.getYear()+1,9,31); // next year
        }
        return endDate.getTime() - currentDate.getTime();
    }

    class BackgroundSound extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player;
            Random random = new Random(System.currentTimeMillis());
            int rd = random.nextInt(7-1)+1;
            if(rd == 1)
                player=MediaPlayer.create(MainActivity.this,R.raw.bg_sound);
            else if(rd == 2)
                player=MediaPlayer.create(MainActivity.this,R.raw.sound1);
            else if(rd == 3)
                player=MediaPlayer.create(MainActivity.this,R.raw.sound2);
            else if(rd == 4)
                player=MediaPlayer.create(MainActivity.this,R.raw.sound3);
            else if(rd == 5)
                player=MediaPlayer.create(MainActivity.this,R.raw.sound4);
            else if(rd == 6)
                player=MediaPlayer.create(MainActivity.this,R.raw.sound5);
            else
                player=MediaPlayer.create(MainActivity.this,R.raw.sound6);




            return null;


        }
    }
}
