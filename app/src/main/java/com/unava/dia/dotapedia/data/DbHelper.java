package com.unava.dia.dotapedia.data;

import android.content.Context;

import com.unava.dia.dotapedia.ui.activity.MainActivity;
import com.unava.dia.dotapedia.data.model.DotaHero;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dia on 10.10.2018.
 */

public class DbHelper {
    private static DbHelper ourInstance = new DbHelper();

    private Context context;
    private Realm realm;

    private RealmResults<DotaHero> heroesList;



    public static DbHelper getInstance() {
        return ourInstance;
    }

    private DbHelper() {
        this.context = MainActivity.context;
        this.realm = Realm.getInstance(MainActivity.context);

        heroesList = realm.where(DotaHero.class).findAll();
        //heroesList = realm.where(DotaHero.class).findAllAsync();
    }

    public void initDb() {
        if(realm.isEmpty()) {
            try {
                InputStream is =  context.getAssets().open("heroes.json");

                realm.beginTransaction();
                realm.createAllFromJson(DotaHero.class, is);
                realm.commitTransaction();
            } catch (IOException e) {
                if(realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                throw new RuntimeException(e);
            }
        }
    }

    public RealmResults<DotaHero> getRealmList()
    {
        return this.heroesList;
    }
}