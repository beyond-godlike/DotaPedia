package com.unava.dia.dotapedia.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;
import com.unava.dia.dotapedia.adapters.MyAdapter;
import com.unava.dia.dotapedia.data.Hero;
import com.unava.dia.dotapedia.fragment.FragmentHeroes;
import com.unava.dia.dotapedia.fragment.FragmentInformation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import butterknife.ButterKnife;

public class Pedia extends AppCompatActivity {
    private static List<Hero> heroList;
    private Hero temp;

    /*
    final String[] heroes = new String[] {
            "Abbaddon", "Alchemist", "Axe", "", "",
            "", ""
    };
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedia);
        ButterKnife.bind(this);

        // TODO заполнить из файла
        initHeroList();

    }

    public void onHeroSelected(int i) {
        // получаем ссылку на 2й фрагмент
        FragmentManager fm = getSupportFragmentManager();
        FragmentInformation fragmentInformation = (FragmentInformation)fm.findFragmentById(R.id.fragment2);

        if (fragmentInformation != null) {
            fragmentInformation.onHeroClicked(i);
        }
            Toast toast = Toast.makeText(getApplicationContext(),
                    i, Toast.LENGTH_SHORT);

            toast.show();
    }

    public static List<Hero> getHeroList(){
        return heroList;
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
                        heroList = new ArrayList<>();
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
