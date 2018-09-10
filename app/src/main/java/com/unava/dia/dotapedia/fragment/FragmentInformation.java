package com.unava.dia.dotapedia.fragment;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.Hero;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentInformation extends Fragment {

    View rootView;
    ArrayList<Hero> heroes = new ArrayList<>();

    @BindView(R.id.hero_strength) TextView strength;
    @BindView(R.id.hero_agility) TextView agility;
    @BindView(R.id.hero_intelligence) TextView intelligence;

    @BindView(R.id.hero_damage) TextView damage;
    @BindView(R.id.hero_speed) TextView speed;
    @BindView(R.id.hero_armor) TextView armor;

    @BindView(R.id.hero_history) TextView history;

    @BindView(R.id.iw_agility) ImageView iw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // достаем список из bundle
        heroes = getArguments().getParcelableArrayList("HEROES_LIST");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_information, container, false);
        ButterKnife.bind(this, rootView);

        setDefaultHero();

        return rootView;
    }

    public void initView() {
        iw.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.agility));
    }

    public void setHero(int i) {
        strength.setText(heroes.get(i).strength);
        agility.setText(heroes.get(i).strength);
        intelligence.setText(heroes.get(i).name);

        damage.setText(heroes.get(i).baseDamage);
        speed.setText(heroes.get(i).speed);
        armor.setText(heroes.get(i).armor);

        history.setText(heroes.get(i).history);
    }

    public void setDefaultHero() {
        strength.setText(heroes.get(1).strength);
        agility.setText(heroes.get(1).agility);
        intelligence.setText(heroes.get(1).intelligence);

        damage.setText(heroes.get(1).baseDamage);
        speed.setText(heroes.get(1).speed);
        armor.setText(heroes.get(1).armor);

        history.setText(heroes.get(1).history);

    }

}
