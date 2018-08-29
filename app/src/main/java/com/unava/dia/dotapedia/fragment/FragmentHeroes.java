package com.unava.dia.dotapedia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.activity.Pedia;
import com.unava.dia.dotapedia.adapters.MyAdapter;
import com.unava.dia.dotapedia.data.Hero;

import java.util.ArrayList;

public class FragmentHeroes extends Fragment {
    ArrayList<Hero> heroes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heroes, container, false);

        // достаем список из bundle
        heroes = (ArrayList<Hero>) savedInstanceState.getSerializable("HEROES_LIST");

        // он уже тут NULL
        Log.d("sss", new Integer(heroes.size()).toString());

        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        //ADAPTER
        MyAdapter adapter = new MyAdapter(rootView.getContext(), heroes);
        rv.setAdapter(adapter);

        return rootView;
    }

    public ArrayList<Hero> setHeroes(ArrayList<Hero> h) {
        heroes = h;
        return  heroes;
    }
}
