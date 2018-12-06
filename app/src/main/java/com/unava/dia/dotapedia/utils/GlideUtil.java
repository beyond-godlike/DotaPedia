package com.unava.dia.dotapedia.utils;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by Dia on 08.11.2018.
 */

public class GlideUtil {
    public static void setImageUrl(ImageView imageView, String imageUrl, int imageWidth, int imageHeight) {
        Context context = imageView.getContext();
        //Integer imageWidth = Utils.displayWidth(context) / 6;
        //Double imageHeight = imageWidth / 1.78;
        // imageHeight.intValue()

        Glide.with(context).load(imageUrl)
                // передать сюда метрику
                .apply(new RequestOptions().override(imageWidth, imageHeight))
                //.apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
    public static void setImageHero(ImageView imageView, int heroId, int imageWidth,  int imageHeight) {
        String imageUrl = new StringBuilder()
                .append("http://cdn.dota2.com/apps/dota2/images/heroes/")
                .append(HeroUtil.getHeroName(heroId))
                .append("_lg.png")
                .toString();

        setImageUrl(imageView, imageUrl, imageWidth, imageHeight);
    }

    public static void setPlayerIcon(ImageView imageView, String avatarUrl) {
        String imageUrl = new StringBuilder()
                .append(avatarUrl)
                .toString();

        Context context = imageView.getContext();

        Glide.with(context).load(imageUrl)
                // передать сюда метрику
                //.apply(new RequestOptions().override(imageWidth, imageHeight))
                //.apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
}
