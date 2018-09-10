package com.unava.dia.dotapedia.fragment;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.Hero;
import com.unava.dia.dotapedia.data.HeroImages;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class FragmentInformation extends Fragment {

    View rootView;
    ArrayList<Hero> heroes = new ArrayList<>();

    private Realm realm;
    private RealmResults<HeroImages> listHeroImages;

    @BindView(R.id.hero_strength) TextView strength;
    @BindView(R.id.hero_agility) TextView agility;
    @BindView(R.id.hero_intelligence) TextView intelligence;

    @BindView(R.id.hero_damage) TextView damage;
    @BindView(R.id.hero_speed) TextView speed;
    @BindView(R.id.hero_armor) TextView armor;

    @BindView(R.id.hero_history) TextView history;

    @BindView(R.id.heroImage) ImageView heroImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getInstance(getActivity().getApplicationContext());

        // достаем список из bundle
        heroes = getArguments().getParcelableArrayList("HEROES_LIST");
        readRealmDb();
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
        heroImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.axe));
    }

    public void setHero(int i) {
        AssetManager am = getActivity().getApplicationContext().getResources().getAssets();
        InputStream  stream = null;
        String path = listHeroImages.get(i).getHeroIcon();
        try {
            stream = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroImage.setImageDrawable(Drawable.createFromStream(stream, null));


        Log.d("imii", path);
        Log.d("imii", new Integer(listHeroImages.size()).toString()); // 21

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

    private void readRealmDb() {
        listHeroImages = realm.where(HeroImages.class).findAll();
    }

}
