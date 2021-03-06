package com.unava.dia.dotapedia.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unava.dia.dotapedia.utils.ProjectConstants;
import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.ui.adapters.ChooseHeroAdapter;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.utils.Utils;

import io.realm.RealmResults;

public class ChooseHeroActivity extends AppCompatActivity {
    private RealmResults<DotaHero> heroesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hero);

        heroesList = Utils.getHeroPediaList(this);

        int columns = Utils.calculateColumns(getApplicationContext(), ProjectConstants.IMAGE_HERO_SMALL_WIDTH);

        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) findViewById(R.id.chooseHero);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), columns));

        //ADAPTER
        ChooseHeroAdapter adapterSt = new ChooseHeroAdapter(getApplicationContext(), heroesList, this);
        rv.setAdapter(adapterSt);
    }
}
