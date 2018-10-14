package com.unava.dia.dotapedia.data;

import android.content.Context;

import com.unava.dia.dotapedia.data.model.DotaHero;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dia on 10.10.2018.
 */

public class DbHelper {
    private Context context;
    private Realm realm;

    public DbHelper(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
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
        RealmResults<DotaHero> heroesList = realm.where(DotaHero.class).findAll();
        return heroesList;
    }
}
