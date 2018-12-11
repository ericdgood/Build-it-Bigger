package com.pia.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdnroidMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_activity_main);

        Intent passedJoke = getIntent();

        String joke = passedJoke.getStringExtra("joke");

        TextView Textview_joke = findViewById(R.id.textview_joke);

        Textview_joke.setText(joke);
    }
}
