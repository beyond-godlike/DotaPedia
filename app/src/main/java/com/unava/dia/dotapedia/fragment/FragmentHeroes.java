package com.unava.dia.dotapedia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;
import com.unava.dia.dotapedia.activity.Pedia;
import com.unava.dia.dotapedia.adapters.MyAdapter;
import com.unava.dia.dotapedia.data.Hero;

import java.util.List;

public class FragmentHeroes extends Fragment {
    List<Hero> heroes;
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_heroes, container, false);

        // достаем ссылку на RecyclerView
        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        // Устанавливаем LayoutManager
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        // данные для RecyclerView
        heroes = Pedia.getHeroList();

        // как бы тут нужно повесить лиснер на ресайклер вью
        // это должно быть в фрагменте

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast toast =  Toast.makeText(getContext(),
                        "Position " + position, Toast.LENGTH_SHORT);
                toast.show();
                // вызываем onHeroSelected(position)
            }
        };

        // создаем адаптер
        MyAdapter adapter = new MyAdapter(heroes, listener);
        rv.setAdapter(adapter);

        return rootView;
    }

}
