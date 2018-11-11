package com.unava.dia.dotapedia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.utils.ToastUtil;
import com.unava.dia.dotapedia.utils.Utils;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.buttonPedia) Button pediaButton;
    private DbHelper dbHelper;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = getApplicationContext();

        if(savedInstanceState != null) {
            // we need a realm db
        }
        else {
            dbHelper = DbHelper.getInstance(this);
            dbHelper.initDb();
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
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

    public void onFabClicked(View view) {
        ToastUtil.showToast(MainActivity.this, getApplicationContext(), Utils.getRandomTip(this));
    }
}
