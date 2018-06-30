package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.hereticpurge.jokedisplaylib.JokeActivity;
import com.udacity.gradle.builditbigger.IdlingResource.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity {

    private static final String JOKE_ID = "jokeIntentId";

    private ProgressBar mProgressBar;

    @Nullable
    private SimpleIdlingResource mSimpleIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if (mSimpleIdlingResource != null) {
            mSimpleIdlingResource.setIdleState(!mSimpleIdlingResource.isIdleNow());
        }

        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    private void dispatchJokeIntent(String string) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JOKE_ID, string);
        startActivity(intent);
    }

    public void endpointsCallback(String string) {
        mProgressBar.setVisibility(View.GONE);
        if (mSimpleIdlingResource != null) {
            mSimpleIdlingResource.setIdleState(!mSimpleIdlingResource.isIdleNow());
        }
        dispatchJokeIntent(string);
    }

    @VisibleForTesting
    @NonNull
    public synchronized IdlingResource getIdlingResource() {
        if (mSimpleIdlingResource == null) {
            mSimpleIdlingResource = new SimpleIdlingResource();
        }
        return mSimpleIdlingResource;
    }
}


