package com.unava.dia.dotapedia.activity;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.utils.Utils;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class HeroConstructor extends AppCompatActivity {
    private RealmResults<DotaHero> heroesList;
    int heroId = 0;
    int level = 1;

    @BindView(R.id.textViewLVL) TextView textViewLVL;
    @BindView(R.id.textViewHP) TextView textViewHP;
    @BindView(R.id.textViewMP) TextView textViewMP;
    @BindView(R.id.imageButtonHero) ImageButton imageButtonHero;

    @BindView(R.id.textViewStrength) TextView textViewStrength;
    @BindView(R.id.textViewAgility) TextView textViewAgility;
    @BindView(R.id.textViewIntelligence) TextView textViewIntelligence;

    @BindView(R.id.textViewAttack) TextView textViewAttack;
    @BindView(R.id.textViewArmor) TextView textViewArmor;
    @BindView(R.id.textViewSpeed) TextView textViewSpeed;

    @BindView(R.id.skill_one) ImageButton skill_one;
    @BindView(R.id.skill_two) ImageButton skill_two;
    @BindView(R.id.skill_three) ImageButton skill_three;
    @BindView(R.id.skill_four) ImageButton skill_four;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_constructor);
        ButterKnife.bind(this);

        heroId = this.getIntent().getFlags();

        if(savedInstanceState != null) {
            heroesList = Utils.getHeroPediaList();
        }
        else {
            heroesList = Utils.getHeroPediaList();
        }

        updateActivity(heroId, level);

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
        textViewHP.setText(String.format("%.5g%n", tempHero.currentHp));
        textViewMP.setText(String.format("%.5g%n", tempHero.currentMp));

        textViewStrength.setText(String.format("%.2g%n", tempHero.currentStrength));
        textViewAgility.setText(String.format("%.2g%n", tempHero.currentAgility));
        textViewIntelligence.setText(String.format("%.2g%n", tempHero.currentIntelligence));

        //ATTACK
        textViewAttack.setText(String.format("%.3g%n", tempHero.currentDmg1) + " + "
        + String.format("%.2g%n", tempHero.currentDmg2));

        // TODO recount
        // Armor
        textViewArmor.setText(String.format("%.2g%n", tempHero.currentArmor));

        //Speed
        textViewSpeed.setText(new Integer(tempHero.getSpeed()).toString());

        // Skills
        skill_one.setImageDrawable(Drawable.createFromStream(openImage(tempHero.getSkill1()), null));
        skill_two.setImageDrawable(Drawable.createFromStream(openImage(tempHero.getSkill2()), null));
        skill_three.setImageDrawable(Drawable.createFromStream(openImage(tempHero.getSkill3()), null));
        skill_four.setImageDrawable(Drawable.createFromStream(openImage(tempHero.getUlt1()), null));


    }

    public DotaHero changeStats(DotaHero tempHero) {
        // пересчитываем статы
        tempHero.currentStrength = tempHero.getStrength() + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentAgility = tempHero.getAgility() + tempHero.currentLvl * tempHero.getAddAg();
        tempHero.currentIntelligence = tempHero.getIntelligence() + tempHero.currentLvl * tempHero.getAddInt();

        // пересчитываем скорость и броню
        tempHero.currentDmg1 = (tempHero.currentLvl * tempHero.getAddSt()) + tempHero.getBaseDamage1();
        tempHero.currentDmg2 = (tempHero.currentLvl * tempHero.getAddSt()) + tempHero.getBaseDamage2();

        tempHero.currentArmor = tempHero.getPhysarmor() + (tempHero.getAddAg() * (1 / 6.25));


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
    }

    public void onPlusLvlClicked(View view) {
        if (level >= 25) {level = 25;}
        else {level++;}
        updateActivity(heroId, level);
    }

    public void onMinusLvlClicked(View view) {
        if (level <= 1) {level = 1;}
        else {level--;}
        updateActivity(heroId, level);
    }

    public void onMaxLvlClicked(View view) {
        level = 25;
        updateActivity(heroId, level);
    }

    public void onMinLvlClicked(View view) {
        level = 1;
        updateActivity(heroId, level);
    }
}
