package com.example.myapplication.LanguageActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LanguageActivity;
import com.example.myapplication.MenuActivities.ActivityOne_foodEnglish;
import com.example.myapplication.R;
import com.example.myapplication.WordGame;

public class CategoryActivity_english extends AppCompatActivity {

    GridLayout mainGrid;
    TextView a;

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
                        intent.putExtra("language","english");
                        intent.putExtra("category","food");
                        startActivity(intent);
                    } else if (finalI == 1) //open activity two
                    {
                        //open activity
                        Intent intent = new Intent(CategoryActivity_english.this, ActivityOne_foodEnglish.class);
                        intent.putExtra("language","english");
                        intent.putExtra("category","animal");
                        startActivity(intent);
                    }
                    else if (finalI == 2) //open activity two
                    {
                        Intent intent = new Intent(CategoryActivity_english.this, ActivityOne_foodEnglish.class);
                        intent.putExtra("language","english");
                        intent.putExtra("category","color");
                        startActivity(intent);
                    }

                    else{
                        Toast.makeText(CategoryActivity_english.this, "Please set Activity for this item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

    public void back(View view){
        startActivity(new Intent(CategoryActivity_english.this, LanguageActivity.class));
    }
}
