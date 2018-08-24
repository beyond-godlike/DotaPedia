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
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        heroes = Pedia.getHeroList();

        View rootView = inflater.inflate(R.layout.fragment_heroes, container, false);

        initRecyclerVew(rootView);
        return rootView;
    }

    private void initRecyclerVew(View rootView) {
        rv = rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        rv.setLayoutManager(mLayoutManager);

        adapter = new MyAdapter(heroes);
        rv.setAdapter(adapter);
    }


}
