package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MatchGameEnglish extends AppCompatActivity {

    final int word = 8;
    final int num = 8;// the number of card
    public Button btn[]=new Button[num];
    public ImageView iv[]=new ImageView[num];


    int filped[]=new int[num]; //if filped[i] 0 then button[i] not reversed
    int count = 0;
    int img;
    //int ran[] = new int[num];
    int t[] = new int[num];
    int tt[] = new int[num];
    int r[] = new int[num/2];
    int temp;
    int select = -1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_game_english);




        LinearLayout mLayout;
        mLayout = (LinearLayout) findViewById(R.id.mLayout);
        mLayout.setBackgroundColor(Color.rgb(163,204,163));

        int k,m,j;
        for(int i = 0;i<num/2;++i) //사진 4장을 고른다
            while(true){
                int random = (int)(Math.random()*word); //word는 전채 사진수
                for(j=0;j<i && r[j]!= random;++j)
                    ;
                if(j==i){
                    r[i] = random;
                    break;
                }
            }

        for(int i=0;i<num;++i)
            t[i] =i;

        int rr = (int)(1680*Math.random());

        for(int i=0;i<num;++i) {
            tt[i] = r[t[rr % (num - i)]/2];
            //tt[i] = rr%8;
            temp =t[num-1-i];
            t[num-1-i] = t[rr % (num - i)];
            t[rr % (num - i)] = temp;
        }


        for(int i=0;i<num;++i) {
            k = getResources().getIdentifier("btn" + (i + 1),
                    "id", getPackageName());
            m = getResources().getIdentifier("iv" + (i + 1), "id", getPackageName());
            btn[i] = (Button)findViewById(k);
            iv[i] = (ImageView)findViewById(m);
            filped[i] = 0;
            img = getApplicationContext().getResources().getIdentifier("img"+(tt[i] + 1),"drawable",getPackageName());
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
                                    if(tt[k] == select)
                                        filped[k] = 2;
                                select = -1;
                                count = 0;
                            }
                        }
            }
        };
        for(int i=0;i<num;++i)
            btn[i].setOnClickListener(btnListener);


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
        startActivity(new Intent(MatchGameEnglish.this,ActivityOne_foodEnglish.class));
    }

}