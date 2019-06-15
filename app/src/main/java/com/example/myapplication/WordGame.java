package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.myapplication.Adapters.GridViewAnswerAdapter;
import com.example.myapplication.Adapters.GridViewSuggestAdapter;
import com.example.myapplication.Commons.Commons;
import com.example.myapplication.LanguageActivities.CategoryActivity_english;
import com.example.myapplication.MenuActivities.ActivityOne_foodEnglish;
import com.example.myapplication.UserModel.DatabaseAccess;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordGame extends AppCompatActivity {

    public List<String> suggestSource = new ArrayList<>();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer,gridViewSuggest;

    public ImageView imgViewQuestion;

    public String sound;

    MediaPlayer mediaPlayer;

    int[] image_list={
            R.drawable.lion,
            R.drawable.elephant,
            R.drawable.tiger,
            R.drawable.horse,
            R.drawable.monkey,
            R.drawable.snail,

    };
    int times = 0;



    public char[] answer;

    String correct_answer;
    String language;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordgame);




        //Initializing view
        initView();


    }

    private void initView() {
        gridViewAnswer = (GridView) findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView) findViewById(R.id.gridViewSuggest);


        imgViewQuestion = (ImageView) findViewById(R.id.imgLogo);



        //Сетап
        setupList();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                for (int i = 0; i < Commons.user_submit_answer.length; i++)
                    result += String.valueOf(Commons.user_submit_answer[i]);
                if (result.equals(correct_answer)) {
                    Toast.makeText(getApplicationContext(), "Correct! This is " + result, Toast.LENGTH_SHORT).show();
                    int soundfile =getApplicationContext().getResources().getIdentifier(sound,"raw",getPackageName());
                    mediaPlayer = MediaPlayer.create(WordGame.this, soundfile);
                    mediaPlayer.start();                   //Reset
                    Commons.count = 0;
                    Commons.user_submit_answer = new char[correct_answer.length()];

                    //Set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(), getApplicationContext());
                    gridViewSuggest.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(), WordGame.this);
                    gridViewAnswer.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();
                    if(times == 5)
                        finish();
                    else
                        setupList();


                } else {
                    Toast.makeText(WordGame.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setupList() {
        times++;
        Intent intent =getIntent();
        language = intent.getStringExtra("language");
        category = intent.getStringExtra("category");
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        //Random photo
        int totalword = databaseAccess.Count(category);
        Random random = new Random();
        int t = (int)(Math.random()*(totalword));
        String k= null;
        try {
            k = databaseAccess.getWord(Integer.toString(t),language,category)[2];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int imageSelected =  getApplicationContext().getResources().getIdentifier(k,"drawable",getPackageName());
        //image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);
        try {
            correct_answer = databaseAccess.getWord(Integer.toString(t),language,category)[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //getResources().getResourceName(imageSelected);//читает имя файла
        sound = k;

        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/") + 1);

        answer = correct_answer.toCharArray();

        Commons.user_submit_answer = new char[answer.length];

        //Answer in answ list
        suggestSource.clear();
        for (char item : answer) {
            //photolist
            suggestSource.add(String.valueOf(item));
        }
        for (int i = answer.length; i < answer.length * 2; i++)
            suggestSource.add(Commons.alphabet_character[random.nextInt(Commons.alphabet_character.length)]);

        //Рандом сортинг
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(), WordGame.this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource, WordGame.this, WordGame.this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);
    }
    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i]=' ';
        return result;
    }
}
