package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class CategoryActivity_english extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_english);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }


    private void setSingleEvent(GridLayout mainGrid){
        //Loop all child item of Main Grid
        for(int i = 0; i<mainGrid.getChildCount(); i++)
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) //open activity one
                    {
                        Intent intent = new Intent(CategoryActivity_english.this, ActivityOne_foodEnglish.class);
                        startActivity(intent);
                    } else if (finalI == 1) //open activity two
                    {
                        //open activity
                        Intent intent2 = new Intent(CategoryActivity_english.this, WordGame.class);
                        startActivity(intent2);
                    }
                    else if (finalI == 2) //open activity two
                    {
                        //open activity
                    }

                    else{
                        Toast.makeText(CategoryActivity_english.this, "Please set Activity for this item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

    public void back(View view){
        startActivity(new Intent(CategoryActivity_english.this,LanguageActivity.class));
    }
}
