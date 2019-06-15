package com.example.myapplication.LanguageActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.LanguageActivity;
import com.example.myapplication.R;

public class CategoryActivity_russian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_russian);
    }

    public void back(View view){
        startActivity(new Intent(CategoryActivity_russian.this, LanguageActivity.class));
    }
}
