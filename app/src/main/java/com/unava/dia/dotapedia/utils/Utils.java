package com.unava.dia.dotapedia.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.ui.activity.MainActivity;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.data.XmlHelper;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.data.model.Hero;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dia on 22.10.2018.
 */

public class Utils {
    private static DbHelper dbHelper;
    private static Realm realm;
    private static String[] tips;

    private Utils() {
        // низя делать паблик
    }

    public static RealmResults<DotaHero> getHeroPediaList() {
        realm = Realm.getInstance(MainActivity.context);
        dbHelper = DbHelper.getInstance();
        RealmResults<DotaHero> heroesList = DbHelper.getInstance().getRealmList();

        return heroesList;
    }

    public static ArrayList<Hero> getHeroList() {
        ArrayList<Hero> heroes = XmlHelper.getInstance().getHeroList();

        return heroes;
    }

    public static String getRandomTip() {
        tips = MainActivity.context.getResources().getStringArray(R.array.tips);

        int pos = 1 + (int) (Math.random() * 12);
        return tips[pos];
    }

    public static InputStream openImage(String path, Context c) {
        AssetManager am = c.getResources().getAssets();
        InputStream  stream = null;

        try {
            stream = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
}
