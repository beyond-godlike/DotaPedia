package com.unava.dia.dotapedia.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.activity.Pedia;
import com.unava.dia.dotapedia.data.Hero;

import java.util.List;

public class FragmentInformation extends Fragment {

    View rootView;
    private List<Hero> heroList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_information, container, false);

        // загружаем массив из ресурсов
        heroList = Pedia.getHeroList();

        return rootView;
    }

    public void onHeroClicked(int i){
        TextView strength = (TextView)rootView.findViewById(R.id.hero_strength);
        TextView agility = (TextView)rootView.findViewById(R.id.hero_agility);
        TextView intelligence = (TextView)rootView.findViewById(R.id.hero_intelligence);

        TextView damage = (TextView)rootView.findViewById(R.id.hero_damage);
        TextView speed = (TextView)rootView.findViewById(R.id.hero_speed);
        TextView armor = (TextView)rootView.findViewById(R.id.hero_armor);

        TextView history = (TextView)rootView.findViewById(R.id.hero_history);

        strength.setText(heroList.get(i).strength);
        agility.setText(heroList.get(i).agility);
        intelligence.setText(heroList.get(i).intelligence);

        damage.setText(heroList.get(i).baseDamage);
        speed.setText(heroList.get(i).speed);
        armor.setText(heroList.get(i).armor);

        history.setText(heroList.get(i).history);
    }

}
