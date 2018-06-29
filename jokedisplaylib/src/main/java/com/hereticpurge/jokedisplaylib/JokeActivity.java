package com.hereticpurge.jokedisplaylib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE_ID = "jokeIntentId";
    private String mJoke = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView mTextView = findViewById(R.id.joke_text_view);

        try {
            if (getIntent().hasExtra(JOKE_ID)){
                mJoke = getIntent().getStringExtra(JOKE_ID);
            } else {
                mJoke = savedInstanceState.getString(JOKE_ID);
            }

        } catch (NullPointerException npe){
            Toast.makeText(this, R.string.joke_intent_load_failure, Toast.LENGTH_SHORT).show();
        }

        if (mJoke != null){
            mTextView.setText(mJoke);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (mJoke != null){
            outState.putString(JOKE_ID, mJoke);
        }

        super.onSaveInstanceState(outState);
    }
}
