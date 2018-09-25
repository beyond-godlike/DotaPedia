package com.unava.dia.dotapedia.fragment;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

    @BindView(R.id.hero_name) TextView hName;

    @BindView(R.id.hero_strength) TextView strength;
    @BindView(R.id.hero_agility) TextView agility;
    @BindView(R.id.hero_intelligence) TextView intelligence;

    @BindView(R.id.hero_damage) TextView damage;
    @BindView(R.id.hero_speed) TextView speed;
    @BindView(R.id.hero_armor) TextView armor;

    @BindView(R.id.hero_history) TextView history;

    @BindView(R.id.heroImage) ImageView heroImage;
    @BindView(R.id.skill1) ImageButton skillImage1;
    @BindView(R.id.skill2) ImageButton skillImage2;
    @BindView(R.id.skill3) ImageButton skillImage3;
    @BindView(R.id.skill4) ImageButton skillImage4;

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

        history.setMovementMethod(new ScrollingMovementMethod());

        setHero(0);

        return rootView;
    }


    public InputStream openImage(String path) {
        AssetManager am = getActivity().getApplicationContext().getResources().getAssets();
        InputStream  stream = null;

        try {
            stream = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
    public void setHero(int i) {
        //AssetManager am = getActivity().getApplicationContext().getResources().getAssets();
        //InputStream  stream = null;

        String path = listHeroImages.get(i).getHeroIcon();
        String pathSkill1 = listHeroImages.get(i).getSkillIcon1();
        String pathSkill2 = listHeroImages.get(i).getSkillIcon2();
        String pathSkill3 = listHeroImages.get(i).getSkillIcon3();
        String pathSkill4 = listHeroImages.get(i).getSkillIcon4();

        heroImage.setImageDrawable(Drawable.createFromStream(openImage(path), null));
        skillImage1.setImageDrawable(Drawable.createFromStream(openImage(pathSkill1), null));
        skillImage2.setImageDrawable(Drawable.createFromStream(openImage(pathSkill2), null));
        skillImage3.setImageDrawable(Drawable.createFromStream(openImage(pathSkill3), null));
        skillImage4.setImageDrawable(Drawable.createFromStream(openImage(pathSkill4), null));

        Log.d("imii", new Integer(listHeroImages.size()).toString()); // 21

        hName.setText(heroes.get(i).name);
        strength.setText(heroes.get(i).strength);
        agility.setText(heroes.get(i).agility);
        intelligence.setText(heroes.get(i).intelligence);

        damage.setText("base damage: " + heroes.get(i).baseDamage);
        speed.setText("speed: " + heroes.get(i).speed);
        armor.setText("base armor: " + heroes.get(i).armor);

        history.setText(heroes.get(i).history);
    }


    private void readRealmDb() {
        listHeroImages = realm.where(HeroImages.class).findAll();
    }

}
