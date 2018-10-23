package com.unava.dia.dotapedia.activity;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.model.Hero;
import com.unava.dia.dotapedia.fragment.FragmentHeroes;
import com.unava.dia.dotapedia.fragment.FragmentInformation;
import com.unava.dia.dotapedia.RecyclerViewClickListener;
import com.unava.dia.dotapedia.utils.Utils;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class Pedia extends AppCompatActivity implements RecyclerViewClickListener {
    private ArrayList<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedia);
        ButterKnife.bind(this);

        if(savedInstanceState != null) {
            heroList = savedInstanceState.getParcelableArrayList("HEROES_LIST");
            // we need a realm db
        }
        else {
            // load list first time
            heroList = Utils.getHeroList();
        }

        // pass into a fragment
        setData();

    }

    public void setData() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("HEROES_LIST", heroList);

        FragmentHeroes fragment1 = new FragmentHeroes();
        fragment1.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment1, fragment1)
                .commit();

        Log.d("111", new Integer(heroList.size()).toString());


        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("HEROES_LIST", heroList);

        FragmentInformation fragment2 = new FragmentInformation();
        fragment2.setArguments(bundle1);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment2, fragment2)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        super.onSaveInstanceState(ourState);
        ourState.putParcelableArrayList("HEROES_LIST", heroList);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onItemClick(int position) {
        // получаем ссылку на 2й фрагмент
        FragmentManager fm = getFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        FragmentInformation fragmentInformation = (FragmentInformation)fm.findFragmentById(R.id.fragment2);

        // Выводим нужную информацию
        if (fragmentInformation != null) {
            fragmentInformation.setHero(position);
        }
        else {

        }
    }
}
