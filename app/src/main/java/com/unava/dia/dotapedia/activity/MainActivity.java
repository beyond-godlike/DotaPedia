package com.unava.dia.dotapedia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.unava.dia.dotapedia.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.buttonPedia) Button pediaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onButtonPediaClicked(View view) {
        Intent intent = new Intent(MainActivity.this, Pedia.class);
        startActivity(intent);

        // create a realm db?
    }

    public void onButtonArticlesClicked(View view) {
        Intent intent = new Intent(MainActivity.this, Articles.class);
        startActivity(intent);
    }
}
