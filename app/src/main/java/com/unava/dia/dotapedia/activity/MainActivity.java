package com.unava.dia.dotapedia.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.data.model.DotaHero;

import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.buttonPedia) Button pediaButton;
    private Realm realm;
    private DbHelper dbHelper;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = getApplicationContext();
        realm = Realm.getInstance(getApplicationContext());

        if(savedInstanceState != null) {
            // we need a realm db
        }
        else {
            dbHelper = new DbHelper(this, realm);
            dbHelper.initDb();
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        realm.close();
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

    public void onButtonHeroConstructorClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ChooseHeroActivity.class);
        startActivity(intent);
    }
}
