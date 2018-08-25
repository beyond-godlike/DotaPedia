package com.unava.dia.dotapedia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.activity.Pedia;
import com.unava.dia.dotapedia.adapters.MyAdapter;
import com.unava.dia.dotapedia.data.Hero;

import java.util.List;

public class FragmentHeroes extends Fragment {
    List<Hero> heroes;
    RecyclerView rv;

    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heroes, container, false);

        // достаем ссылку на RecyclerView
        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        // Устанавливаем LayoutManager
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        // данные для RecyclerView
        heroes = Pedia.getHeroList();

        // создаем адаптер
        MyAdapter adapter = new MyAdapter(heroes);
        rv.setAdapter(adapter);

        return rootView;
    }

}
