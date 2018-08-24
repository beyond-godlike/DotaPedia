package com.unava.dia.dotapedia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.Hero;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class Pedia extends AppCompatActivity {
    private static List<Hero> heroList;

    /*
    final String[] heroes = new String[] {
            "Abbaddon", "Alchemist", "Axe", "", "",
            "", ""
    };
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedia);
        ButterKnife.bind(this);

        // TODO заполнить из файла
        initHeroList();

    }

    public static List<Hero> getHeroList(){
        return heroList;
    }

    public void initHeroList() {
        heroList = new ArrayList<>();

        Hero h1 = new Hero();
        h1.name = "Abaddon";

        Hero h2 = new Hero();
        h2.name = "Alchemist";

        Hero h3 = new Hero();
        h3.name = "Axe";


        heroList.add(h1);
        heroList.add(h2);
        heroList.add(h3);
    }
}
