package com.unava.dia.dotapedia.activity;

import android.app.Fragment;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.Hero;
import com.unava.dia.dotapedia.fragment.FragmentInformation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import butterknife.ButterKnife;

public class Pedia extends AppCompatActivity {
    private ArrayList<Hero> heroList;
    private Hero temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedia);
        ButterKnife.bind(this);

        if(savedInstanceState != null) {
            heroList = savedInstanceState.getParcelableArrayList("HEROES_LIST");
        }
        else {
            // load list first time
            initHeroList();
        }

        // pass into a fragment
        setData();

    }

    public void setData() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("HEROES_LIST", heroList);

        Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment1);

        //Passing heroes via a Bundle
        bundle.putParcelableArrayList("HEROES_LIST", (ArrayList<? extends Parcelable>) heroList);
        fragment1.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment1, fragment1)
                .commit();

        Log.d("111", new Integer(heroList.size()).toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        super.onSaveInstanceState(ourState);
        ourState.putParcelableArrayList("HEROES_LIST", heroList);
    }

    public void onHeroSelected(int i) {
        // получаем ссылку на 2й фрагмент
        FragmentManager fm = getSupportFragmentManager();
        FragmentInformation fragmentInformation = (FragmentInformation)fm.findFragmentById(R.id.fragment2);

        if (fragmentInformation != null) {
            fragmentInformation.onHeroClicked(i);
        }
    }

    public void initHeroList() {
        heroList = new ArrayList<>();
        heroList.clear();

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
                            temp = new Hero();
                        }
                        else if (temp != null)
                        {
                            if(name.equals("name"))
                            {
                                temp.setName(parser.nextText());
                            }
                            else if(name.equals("strength"))
                            {
                                temp.setStrength(parser.nextText());
                            }
                            else if (name.equals("agility"))
                            {
                                temp.setAgility(parser.nextText());
                            }
                            else if (name.equals("intelligence"))
                            {
                                temp.setIntelligence(parser.nextText());
                            }
                            else if (name.equals("damage"))
                            {
                                temp.setDamage(parser.nextText());
                            }
                            else if (name.equals("armor"))
                            {
                                temp.setArmor(parser.nextText());
                            }
                            else if (name.equals("speed"))
                            {
                                temp.setSpeed(parser.nextText());
                            }
                            else if (name.equals("history"))
                            {
                                temp.setHistory(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("hero") && temp != null)
                        {
                            heroList.add(temp);
                        }
                }
                eventType = parser.next();
            }
        }
    }

}
