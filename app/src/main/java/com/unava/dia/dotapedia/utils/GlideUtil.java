package com.unava.dia.dotapedia.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Dia on 08.11.2018.
 */

public class GlideUtil {
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        Glide.with(context).load(imageUrl)
                .apply(new RequestOptions().override(128, 72))
                //.apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
    public static void setImageHeroSmall(ImageView imageView, int heroId) {
        String imageUrl = new StringBuilder()
                .append("http://cdn.dota2.com/apps/dota2/images/heroes/")
                .append(HeroUtil.getHeroName(heroId))
                .append("_lg.png")
                .toString();

        setImageUrl(imageView, imageUrl);
    }
}
