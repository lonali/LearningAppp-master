package com.example.myapplication;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguageActivity extends AppCompatActivity {

    Button GoToCategory_English;
    Button GoToCategory_Korean;
    Button GoToCategory_Russian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        GoToCategory_English = (Button) findViewById(R.id.english);
        GoToCategory_Korean = (Button) findViewById(R.id.korean);
        GoToCategory_Russian = (Button) findViewById(R.id.russian);

        GoToCategory_English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageActivity.this, CategoryActivity_english.class);
                startActivity(intent);
            }
        });

        GoToCategory_Korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageActivity.this, CategoryActivity_korean.class);
                startActivity(intent);
            }
        });

        GoToCategory_Russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageActivity.this, CategoryActivity_russian.class);
                startActivity(intent);
            }
        });
    }
}