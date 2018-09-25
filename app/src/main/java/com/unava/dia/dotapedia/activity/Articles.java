package com.unava.dia.dotapedia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.adapters.ArticlesAdapter;
import com.unava.dia.dotapedia.adapters.MyAdapter;
import com.unava.dia.dotapedia.data.Hero;

import java.util.ArrayList;

public class Articles extends AppCompatActivity {

    ArrayList<String> articlesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        articlesList.add("Вардинг");
        articlesList.add("Неуязвимость");
        articlesList.add("Скачать читы на инвокера");
        articlesList.add("Гайд по удалению дота 2");
        articlesList.add("Звонок черному властелину");
        articlesList.add("Сапортинг для детей шлюх");
        articlesList.add("Анальный секс - разъебываем за квопу");

        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_articles);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //ADAPTER
        ArticlesAdapter adapter = new ArticlesAdapter(getApplicationContext(), articlesList, this);
        rv.setAdapter(adapter);
    }
}
