package com.unava.dia.dotapedia.data;

import com.unava.dia.dotapedia.ui.activity.MainActivity;
import com.unava.dia.dotapedia.data.model.Hero;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Dia on 23.10.2018.
 */
public class XmlHelper {
    private static XmlHelper ourInstance = new XmlHelper();

    private ArrayList<Hero> heroList;

    public static XmlHelper getInstance() {
        return ourInstance;
    }

    private XmlHelper() {
        initHeroList();
    }


    public ArrayList<Hero> getHeroList() {
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

            InputStream in_s = MainActivity.context.getAssets().open(filename);
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
        Hero tempHero = null;
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
                            tempHero = new Hero("", "", "", "", "", "", "", "");
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
