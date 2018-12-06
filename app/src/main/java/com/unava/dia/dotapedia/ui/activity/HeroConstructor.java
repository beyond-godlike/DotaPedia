package com.unava.dia.dotapedia.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.utils.ProjectConstants;
import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.data.model.Invoker;
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
    Invoker invoker;
    DotaHero tempHero;
    String aboutSkillPath = "";

    static final String STATE_HERO_LEVEL = "heroLevel";
    static final String STATE_ABOUT_SKILL_PATH = "aboutSkillPath";

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
            level = savedInstanceState.getInt(STATE_HERO_LEVEL);
            aboutSkillPath = savedInstanceState.getString(STATE_ABOUT_SKILL_PATH);

        }
        else {
            heroesList = Utils.getHeroPediaList(this);
        }

        updateActivity(heroId, level);

    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        ourState.putInt(STATE_HERO_LEVEL, level);
        ourState.putString(STATE_ABOUT_SKILL_PATH, aboutSkillPath);
        super.onSaveInstanceState(ourState);
    }

    public void updateActivity(int heroId, int lvl) {
        tempHero = heroesList.get(heroId);

        // SET SKILL DESCRIPTION
        if(aboutSkillPath.equals("")) aboutSkillPath = tempHero.getAboutSkill1();
        skillDescription.setText(aboutSkillPath);

        if(tempHero.getName().equals("Invoker")) {
            invoker = new Invoker();
        }

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
        GlideUtil.setImageHero(imageViewHero, heroId,
                ProjectConstants.IMAGE_HERO_CONSTRUCTOR_WIDTH, ProjectConstants.IMAGE_HERO_CONSTRUCTOR_HEIGHT);


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

    public void onSkillOneClicked(View view) {
        if(tempHero.getName().equals("Invoker")) {
            invoker.pushEnd("q");
        }
        aboutSkillPath = tempHero.getAboutSkill1();
        skillDescription.setText(aboutSkillPath);
    }

    public void onSkillTwoClicked(View view) {
        if(tempHero.getName().equals("Invoker")) {
            invoker.pushEnd("w");
        }

        aboutSkillPath = tempHero.getAboutSkill2();
        skillDescription.setText(aboutSkillPath);
    }

    public void onSkillFourClicked(View view) {
        aboutSkillPath = tempHero.getAboutSkill4();
        skillDescription.setText(aboutSkillPath);
    }

    public void onSkillFiveClicked(View view) {
        aboutSkillPath = tempHero.getAboutSkill5();
        skillDescription.setText(aboutSkillPath);
    }

    public void onSkillThreeClicked(View view) {
        if(tempHero.getName().equals("Invoker")) {
            invoker.pushEnd("e");
        }

        aboutSkillPath = tempHero.getAboutSkill3();
        skillDescription.setText(aboutSkillPath);
    }


    public void onSkillSixClicked(View view) {
        // if invoker
        if(tempHero.getName().equals("Invoker")) {
            // ставим в картинки 4 или 5 скиллы в зависимости от combinatoin
            skillDescription.setText("inside invoker");
            // генерируем путь

            if(invoker.flag) {
                skillDescription.setText(invoker.combination);
                skill_four.setImageDrawable(Drawable.createFromStream(Utils.openImage(invoker.getFullPath(),
                        getApplicationContext()), null));
            }
            else {
                skillDescription.setText(invoker.combination);
                skill_five.setImageDrawable(Drawable.createFromStream(Utils.openImage(invoker.getFullPath(),
                        getApplicationContext()), null));

            }
            invoker.swapFlag();

        }
        // отображаем инфу о скилле
        aboutSkillPath = tempHero.getAboutSkill6();
        skillDescription.setText(aboutSkillPath);
    }
}
