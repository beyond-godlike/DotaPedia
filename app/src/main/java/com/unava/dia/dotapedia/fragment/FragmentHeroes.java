package com.unava.dia.dotapedia.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.adapters.MyAdapter;
import com.unava.dia.dotapedia.data.Hero;

import java.util.ArrayList;

public class FragmentHeroes extends Fragment {
    ArrayList<Hero> heroes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // достаем список из bundle
        // почему он тут null
        heroes = getArguments().getParcelableArrayList("HEROES_LIST");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heroes, container, false);

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
