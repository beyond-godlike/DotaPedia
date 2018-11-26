package com.unava.dia.dotapedia.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.presenter.HeroConstructorPresenter;
import com.unava.dia.dotapedia.utils.GlideUtil;
import com.unava.dia.dotapedia.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class HeroConstructor extends AppCompatActivity {
    HeroConstructorPresenter presenter;
    private RealmResults<DotaHero> heroesList;
    int heroId = 0;
    int level = 1;

    DotaHero tempHero;

    @BindView(R.id.textViewLVL) TextView textViewLVL;
    @BindView(R.id.textViewHP) TextView textViewHP;
    @BindView(R.id.textViewMP) TextView textViewMP;
    @BindView(R.id.imageViewHero) ImageView imageViewHero;

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
    @BindView(R.id.skill_five) ImageButton skill_five;
    @BindView(R.id.skill_six) ImageButton skill_six;

    @BindView(R.id.skillDescription) TextView skillDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_constructor);
        ButterKnife.bind(this);

        presenter = new HeroConstructorPresenter();

        heroId = this.getIntent().getFlags();

        if(savedInstanceState != null) {
            heroesList = Utils.getHeroPediaList(this);
        }
        else {
            heroesList = Utils.getHeroPediaList(this);
        }

        updateActivity(heroId, level);

    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        super.onSaveInstanceState(ourState);
    }

    public void updateActivity(int heroId, int lvl) {
        tempHero = heroesList.get(heroId);

        tempHero.currentLvl = lvl - 1; // за первый лвл мы не прибавляем статы
        int type = tempHero.getType();

        switch (type) {
            case 1: tempHero = presenter.changeStatsStrength(tempHero);
                break;
            case 2: tempHero = presenter.changeStatsAgility(tempHero);
                break;
            case 3: tempHero = presenter.changeStatsIntelligence(tempHero);
                break;
        }

        //TODO заполнить весь UI
        //imageViewHero.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getIcon(), getApplicationContext()), null));
        // Try Glide
        GlideUtil.setImageHeroSmall(imageViewHero, heroId);


        textViewLVL.setText(presenter.formatter(tempHero.currentLvl + 1));

        // EHP
        textViewHP.setText(presenter.formatter(tempHero.currentHp) + " ("
        + presenter.formatter(tempHero.currentEHP) + "), ("
        + presenter.formatter(tempHero.currentEHPm) + ") ");

        textViewMP.setText(presenter.formatter(tempHero.currentMp));

        // Stats
        textViewStrength.setText(presenter.formatter(tempHero.currentStrength) + " + "
                + presenter.formatterD(tempHero.getAddSt()));

        textViewAgility.setText(presenter.formatter(tempHero.currentAgility) + " + "
                + presenter.formatterD(tempHero.getAddAg()));

        textViewIntelligence.setText(presenter.formatter(tempHero.currentIntelligence) + " + "
                + presenter.formatterD(tempHero.getAddInt()));

        //ATTACK
        textViewAttack.setText(presenter.formatter(tempHero.currentDmg1) + " + "
        + presenter.formatter(tempHero.currentDmg2));

        // TODO recount
        // Armor
        textViewArmor.setText(presenter.formatter(tempHero.currentArmor));

        //Speed
        textViewSpeed.setText(new Integer(tempHero.currentSpeed).toString());

        // Skills
        skill_one.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getSkill1(), getApplicationContext()), null));
        skill_two.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getSkill2(), getApplicationContext()), null));
        skill_three.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getSkill3(), getApplicationContext()), null));
        skill_six.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getSkill6(), getApplicationContext()), null));

        // check for another skills
        if(!tempHero.getSkill4().isEmpty()) {
            skill_four.setVisibility(View.VISIBLE);
            skill_four.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getSkill4(), getApplicationContext()), null));
        }

        if (!tempHero.getSkill5().isEmpty()) {
            skill_five.setVisibility(View.VISIBLE);
            skill_five.setImageDrawable(Drawable.createFromStream(Utils.openImage(tempHero.getSkill5(), getApplicationContext()), null));
        }
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

    public void onSkillOneClocked(View view) {
        //tempHero.getSkill1Description();
        skillDescription.setText(tempHero.getSkill1());
    }
}
