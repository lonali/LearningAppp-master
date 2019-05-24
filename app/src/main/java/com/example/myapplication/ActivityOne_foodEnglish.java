package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class ActivityOne_foodEnglish extends AppCompatActivity {

    GridLayout secondGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_food_english);

        secondGrid = (GridLayout)findViewById(R.id.secondGrid);

        setSingleEvent(secondGrid);
    }

    private void setSingleEvent(GridLayout secondGrid){
        //Loop all child item of Main Grid
        for(int i = 0; i<secondGrid.getChildCount(); i++)
        {
            CardView cardView = (CardView)secondGrid.getChildAt(i);
            final int finalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) //open activity one
                    {
                        Intent intent = new Intent(ActivityOne_foodEnglish.this, MatchGameEnglish.class);
                        intent.putExtra("language","english");
                        intent.putExtra("category","food");
                        startActivity(intent);
                    } else if (finalI == 1) //open activity two
                    {
                        //open activity
                    }
                    else if (finalI == 2) //open activity two
                    {
                        //open activity
                    }

                    else{
                        Toast.makeText(ActivityOne_foodEnglish.this, "Please set Activity for this item", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void back(View view){
        startActivity(new Intent(ActivityOne_foodEnglish.this,CategoryActivity_english.class));
    }
}