package com.unava.dia.dotapedia.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.DbHelper;
import com.unava.dia.dotapedia.data.XmlHelper;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.data.model.Hero;
import com.unava.dia.dotapedia.network.service.ErrorResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.HttpException;

/**
 * Created by Dia on 22.10.2018.
 */

public class Utils {
    private static Realm realm;
    private static String[] tips;

    private Utils() {
        // низя делать паблик
    }

    public static RealmResults<DotaHero> getHeroPediaList(Context context) {
        realm = Realm.getInstance(context);
        RealmResults<DotaHero> heroesList = DbHelper.getInstance(context).getRealmList();

        return heroesList;
    }

    public static ArrayList<Hero> getHeroList(Context context) {
        ArrayList<Hero> heroes = XmlHelper.getInstance(context).getHeroList();

        return heroes;
    }

    public static String getRandomTip(Context context) {
        tips = context.getResources().getStringArray(R.array.tips);

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

    public static int calculateColumns(Context context, int imageWidth) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int columns = (int) (dpWidth /  (imageWidth + 20));

        return columns;
    }

    public static int displayWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;

        return width;
    }

    public static int displayHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;

        return height;
    }

    public static ErrorResponse parseError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;

            try {
                String jsonString = exception.response().errorBody().string();

                Gson gson = new Gson();
                return gson.fromJson(jsonString, ErrorResponse.class);

            } catch (IOException e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        return new ErrorResponse("Unknown Error");
    }
}
