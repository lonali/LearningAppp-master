package com.example.myapplication.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.myapplication.Commons.Commons;
import com.example.myapplication.WordGame;

import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {
    private List<String> suggestSource;
    private Context context;
    private WordGame wordGame;

    public GridViewSuggestAdapter(List<String> suggestSource, Context context, WordGame wordGame) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.wordGame = wordGame;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView==null)
        {
            if(suggestSource.get(position).equals("null"))
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(String.valueOf(wordGame.answer).contains(suggestSource.get(position))) {
                            char compare = suggestSource.get(position).charAt(0);

                            for (int i = 0; i < wordGame.answer.length; i++) {
                                if (compare == wordGame.answer[i])
                                    Commons.user_submit_answer[i] = compare;
                            }

                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Commons.user_submit_answer, context);
                            wordGame.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            wordGame.suggestSource.set(position, "null");
                            wordGame.suggestAdapter = new GridViewSuggestAdapter(wordGame.suggestSource, context,wordGame);
                            wordGame.gridViewSuggest.setAdapter(wordGame.suggestAdapter);
                            wordGame.suggestAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            wordGame.suggestSource.set(position, "null");
                            wordGame.suggestAdapter = new GridViewSuggestAdapter(wordGame.suggestSource, context,wordGame);
                            wordGame.gridViewSuggest.setAdapter(wordGame.suggestAdapter);
                            wordGame.suggestAdapter.notifyDataSetChanged();

                        }


                    }
                });
            }
        }
        else
            button = (Button)convertView;
        return button;
    }
}
