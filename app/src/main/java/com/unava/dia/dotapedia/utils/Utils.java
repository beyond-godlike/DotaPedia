package com.unava.dia.dotapedia.utils;

import com.unava.dia.dotapedia.ui.activity.MainActivity;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.data.XmlHelper;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.data.model.Hero;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dia on 22.10.2018.
 */

public class Utils {
    private static DbHelper dbHelper;
    private static Realm realm;

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
}
