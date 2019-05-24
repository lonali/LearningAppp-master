package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import com.example.myapplication.MenuActivities.ActivityOne_foodEnglish;
import com.example.myapplication.UserModel.DatabaseAccess;


public class MatchGameEnglish extends AppCompatActivity {

    public Button btn[];
    public ImageView iv[];
    public TextView voca;
    int filped[];
    int count;
    int num;
    int select;
    int t[];
    int tt[];
    int r[];
    String picture[];
    String sound[];
    String spelling[];
    MediaPlayer mediaPlayer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_game_english);
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        int word = 8;
        Intent intent =getIntent();
        String language = intent.getStringExtra("language");
        String category = intent.getStringExtra("category");
       // word=databaseAccess.Count(category);
        num = 8;// the number of card
        btn=new Button[num];
        iv=new ImageView[num];
        picture = new String[num];
        sound = new String[num];
        spelling = new String[num];
        filped=new int[num]; //if filped[i] 0 then button[i] not reversed
        count = 0;
        int img;
        //int ran[] = new int[num];
        t = new int[num];
        tt = new int[num];
        r = new int[num/2];
        //int temp;
        select = -1;
        voca=findViewById(R.id.tt);



        LinearLayout mLayout;
        mLayout = (LinearLayout) findViewById(R.id.mLayout);
        mLayout.setBackgroundColor(Color.rgb(163,204,163));

        int k,m,j;
        for(int i = 0;i<num/2;++i) //사진 4장을 고른다
            while(true){
                int random = (int)(Math.random()*word)+1; //word는 전채 사진수
                for(j=0;j<i && r[j]!= random;++j)
                    ;
                if(j==i){
                    r[i] = random;
                    break;
                }
            }

        for(int i=0;i<num/2;++i) {
            t[i] = r[i];
            t[i+num/2] = r[i];
        }


        for(int i=0;i<num;++i) {
            int rr = (int)(Math.random()*(num-i));
            tt[i]=t[rr];
            t[rr] = t[num-1-i];
        }


        for(int i=0;i<num;++i) {
            if(i==0)
                voca.setText(language);
            k = getResources().getIdentifier("btn" + (i + 1),
                    "id", getPackageName());
            m = getResources().getIdentifier("iv" + (i + 1), "id", getPackageName());
            btn[i] = (Button)findViewById(k);
            iv[i] = (ImageView)findViewById(m);
            filped[i] = 0;
            picture[i]=databaseAccess.getWord(Integer.toString(tt[i]),"english","food")[2];
            sound[i]=picture[i];
            spelling[i]=databaseAccess.getWord(Integer.toString(tt[i]),"english","food")[0];

             img = getApplicationContext().getResources().getIdentifier(picture[i],"drawable",getPackageName());
           iv[i].setImageResource(img);

        }


        View.OnClickListener btnListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count < 2)
                    for(int i=0;i<num;++i)
                        if(v.getId()==btn[i].getId() && filped[i] ==0) {
                            filp(i);
                            count += 1;
                            if(count ==2 && tt[i] != select){
                                for(int j=0;j<num;++j)
                                    if(filped[j]==1)
                                        unfilp(j);
                                count = 3;
                                select = -1;
                                TimerTask task = new TimerTask(){
                                    public void run(){
                                        try{
                                            count = 0;
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                Timer timer = new Timer();
                                timer.schedule(task,1500);
                            }
                            else if(count == 1)
                                select =tt[i];
                            else {

                                for(int k=0;k<num;++k)
                                    if(tt[k] == select) {
                                        filped[k] = 2;
                                        voca.setText(spelling[k]);
                                        int soundfile =getApplicationContext().getResources().getIdentifier(sound[k],"raw",getPackageName());
                                        mediaPlayer = MediaPlayer.create(MatchGameEnglish.this, soundfile);
                                        mediaPlayer.start();
                                    }
                                select = -1;
                                count = 0;
                            }
                        }
            }
        };
        for(int i=0;i<num;++i)
            btn[i].setOnClickListener(btnListener);

        databaseAccess.close();
    }
    public void filp(int i){
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn[i],"rotationY",0,180);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn[i],"alpha",1,0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv[i],"rotationY",-180,0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv[i],"alpha",0,1);

        animator.setDuration(1000);
        animator1.setDuration(1);
        animator1.setStartDelay(500);
        animator2.setDuration(1000);
        animator3.setDuration(1);
        animator3.setStartDelay(500);
        animator.start();
        animator1.start();
        animator2.start();
        animator3.start();
        filped[i] = 1;
    }
    public void unfilp(int i){
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn[i],"rotationY",180,0);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn[i],"alpha",0,1);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv[i],"rotationY",0,-180);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv[i],"alpha",1,0);

        animator.setStartDelay(1000);
        animator.setDuration(1000);
        animator1.setDuration(1);
        animator1.setStartDelay(1500);
        animator2.setStartDelay(1000);
        animator2.setDuration(1000);
        animator3.setDuration(1);
        animator3.setStartDelay(1500);
        animator.start();
        animator1.start();
        animator2.start();
        animator3.start();
        filped[i] = 0;
    }

    public void back(View view){
        startActivity(new Intent(MatchGameEnglish.this, ActivityOne_foodEnglish.class));
    }

}