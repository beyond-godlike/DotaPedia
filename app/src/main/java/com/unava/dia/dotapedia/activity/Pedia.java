package com.unava.dia.dotapedia.activity;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.Hero;
import com.unava.dia.dotapedia.data.HeroImages;
import com.unava.dia.dotapedia.fragment.FragmentHeroes;
import com.unava.dia.dotapedia.fragment.FragmentInformation;
import com.unava.dia.dotapedia.RecyclerViewClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class Pedia extends AppCompatActivity implements RecyclerViewClickListener {
    private ArrayList<Hero> heroList;
    private RealmResults<HeroImages> listHeroImages;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedia);
        ButterKnife.bind(this);
        realm = Realm.getInstance(getApplicationContext());

        if(savedInstanceState != null) {
            heroList = savedInstanceState.getParcelableArrayList("HEROES_LIST");
            // we need a realm db
        }
        else {
            // load list first time
            listHeroImages = realm.where(HeroImages.class).findAll();
            initHeroList();
        }

        // pass into a fragment
        setData();

    }

    public void setData() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("HEROES_LIST", heroList);

        FragmentHeroes fragment1 = new FragmentHeroes();
        fragment1.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment1, fragment1)
                .commit();

        Log.d("111", new Integer(heroList.size()).toString());


        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("HEROES_LIST", heroList);

        FragmentInformation fragment2 = new FragmentInformation();
        fragment2.setArguments(bundle1);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment2, fragment2)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        super.onSaveInstanceState(ourState);
        ourState.putParcelableArrayList("HEROES_LIST", heroList);
    }

    @Override
    public void onItemClick(int position) {
        // получаем ссылку на 2й фрагмент
        FragmentManager fm = getFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        FragmentInformation fragmentInformation = (FragmentInformation)fm.findFragmentById(R.id.fragment2);

        // Выводим нужную информацию
        if (fragmentInformation != null) {
            fragmentInformation.setHero(position);
        }
        else {

        }
    }


    public void initHeroList() {
        doParse("heroes.xml");
    }
    // инстанс парсера
    void doParse(String filename) {
        XmlPullParserFactory pullParserFactory;
        XmlPullParser parser;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            parser = pullParserFactory.newPullParser();

            InputStream in_s = this.getApplicationContext().getAssets().open(filename);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            // парсим xml
            parseNewFile(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseNewFile(XmlPullParser parser) throws IOException, XmlPullParserException {

        int counter = 0;
        Hero tempHero = null;
        // грузануть из бд элемент 0
        HeroImages tempImages;
        heroList = new ArrayList<>();

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT){
            {
                String name;
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        //heroList = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equals("hero")){
                            // достаем картинки по некст каунтеру из бд
                            tempImages = listHeroImages.get(counter);
                            tempHero = new Hero("", "", "", "", "", "", "", "", tempImages);
                        }
                        else if (tempHero != null)
                        {
                            if(name.equals("hname"))
                            {
                                tempHero.name = parser.nextText();
                            }
                            else if(name.equals("strength"))
                            {
                                tempHero.strength = parser.nextText();
                            }
                            else if (name.equals("agility"))
                            {
                                tempHero.agility = parser.nextText();
                            }
                            else if (name.equals("intelligence"))
                            {
                                tempHero.intelligence = parser.nextText();
                            }
                            else if (name.equals("damage"))
                            {
                                tempHero.baseDamage = parser.nextText();
                            }
                            else if (name.equals("armor"))
                            {
                                tempHero.armor = parser.nextText();
                            }
                            else if (name.equals("speed"))
                            {
                                tempHero.speed = parser.nextText();
                            }
                            else if (name.equals("history"))
                            {
                                tempHero.history = parser.nextText();
                            }
                            counter++;

                            //tempHero = new Hero(tempName, tempStrength, tempAgility, tempIntelligence, baseDamage
                            //, armor, speed, history);

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("hero") && tempHero != null)
                        {
                            heroList.add(tempHero);
                        }
                }
                eventType = parser.next();
            }
        }
    }

}
