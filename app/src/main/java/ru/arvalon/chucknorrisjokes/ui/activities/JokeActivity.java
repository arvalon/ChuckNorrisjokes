package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arvalon.chucknorrisjokes.R;

public class JokeActivity extends AppCompatActivity {

    @BindView(R.id.jokeText)TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        String joke=intent.getStringExtra("joke");

        textView.setText(joke);

    }
}
