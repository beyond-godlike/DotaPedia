package com.unava.dia.dotapedia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;

public class CardViewActivity extends AppCompatActivity {
    ImageView heroImage;
    TextView heroName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_layout);

        heroName = (TextView)findViewById(R.id.hero_name);
        heroName.setText("Pudge");
    }
}
