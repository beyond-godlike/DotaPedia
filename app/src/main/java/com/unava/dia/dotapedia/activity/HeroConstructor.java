package com.unava.dia.dotapedia.activity;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.data.model.DotaHero;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class HeroConstructor extends AppCompatActivity {
    private RealmResults<DotaHero> heroesList;
    private Realm realm;
    private DbHelper dbHelper;
    int heroId = 0;

    @BindView(R.id.textViewLVL) TextView textViewLVL;
    @BindView(R.id.textViewHP) TextView textViewHP;
    @BindView(R.id.textViewMP) TextView textViewMP;
    @BindView(R.id.imageButtonHero) ImageButton imageButtonHero;

    @BindView(R.id.textViewStrength) TextView textViewStrength;
    @BindView(R.id.textViewAgility) TextView textViewAgility;
    @BindView(R.id.textViewIntelligence) TextView textViewIntelligence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_constructor);
        ButterKnife.bind(this);

        // переносим в async task
        realm = Realm.getInstance(getApplicationContext());
        dbHelper = new DbHelper(this, realm);

        heroId = this.getIntent().getFlags();

        if(savedInstanceState != null) {
            // we need a realm db
            heroesList = dbHelper.getRealmList();
        }
        else {
            heroesList = dbHelper.getRealmList();
        }

        updateActivity(heroId, 1);

    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        super.onSaveInstanceState(ourState);
    }

    public void updateActivity(int heroId, int lvl) {
        DotaHero tempHero = heroesList.get(heroId);

        tempHero.currentLvl = lvl;
        tempHero = changeStats(tempHero);

        //TODO заполнить весь UI
        imageButtonHero.setImageDrawable(Drawable.createFromStream(openImage(tempHero.getIcon()), null));
        textViewLVL.setText(new Integer(tempHero.currentLvl).toString());
        textViewHP.setText(new Double(tempHero.currentHp).toString());
        textViewMP.setText(new Double(tempHero.currentMp).toString());

        textViewStrength.setText(new Double(tempHero.currentStrength).toString());
        textViewAgility.setText(new Double(tempHero.currentAgility).toString());
        textViewIntelligence.setText(new Double(tempHero.currentIntelligence).toString());
    }

    public DotaHero changeStats(DotaHero tempHero) {
        // пересчитываем статы
        tempHero.currentStrength = tempHero.getStrength() + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentAgility = tempHero.getAgility() + tempHero.currentLvl * tempHero.getAddAg();
        tempHero.currentIntelligence = tempHero.getIntelligence() + tempHero.currentLvl * tempHero.getAddInt();

        // пересчитываем скорость и броню


        // высчитываем хп
        tempHero.currentStrength = tempHero.getStrength()
                + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentHp = tempHero.getBaseHP() + (tempHero.currentStrength * 22.5);


        // высчитываем мп
        tempHero.currentIntelligence = tempHero.getIntelligence()
                + tempHero.currentLvl * tempHero.getAddInt();
        tempHero.currentMp = tempHero.getBaseMP() + (tempHero.currentIntelligence * 12.0);

        return tempHero;
    }

    public InputStream openImage(String path) {
        AssetManager am = getApplicationContext().getResources().getAssets();
        InputStream  stream = null;

        try {
            stream = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        realm.close();
    }

}
