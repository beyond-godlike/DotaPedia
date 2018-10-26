package com.unava.dia.dotapedia.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.ui.adapters.InformationAdapter;
import com.unava.dia.dotapedia.data.model.Hero;

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
        View rootView = inflater.inflate(R.layout.fragment_heroes, null);

        // он уже тут NULL
        Log.d("sss", new Integer(heroes.size()).toString());

        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        //ADAPTER
        InformationAdapter adapter = new InformationAdapter(rootView.getContext(), heroes, getActivity());
        rv.setAdapter(adapter);

        return rootView;
    }
}
