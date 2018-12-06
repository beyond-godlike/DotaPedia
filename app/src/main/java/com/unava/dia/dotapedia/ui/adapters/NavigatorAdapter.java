package com.unava.dia.dotapedia.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.utils.ProjectConstants;
import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.utils.RecyclerViewClickListener;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.data.model.Hero;
import com.unava.dia.dotapedia.utils.GlideUtil;
import com.unava.dia.dotapedia.utils.Utils;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Dia on 18.11.2018.
 */

public class NavigatorAdapter extends RecyclerView.Adapter<NavigatorViewHolder> {
    Context c;
    ArrayList<Hero> heroes;
    RealmResults<DotaHero> heroesList;
    Activity activity;

    public NavigatorAdapter(Context c, ArrayList<Hero> h, Activity activity) {
        heroes = new ArrayList<>();

        this.c = c;
        this.heroes = h;
        this.activity = activity;
        this.heroesList = Utils.getHeroPediaList(activity.getApplicationContext());
    }

    @Override
    public NavigatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(c).inflate(R.layout.model, null);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);
        View v = LayoutInflater.from(c).inflate(R.layout.model_navigator, parent, false); // fill_parent works

        return new NavigatorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NavigatorViewHolder holder, int position) {

        //BIND DATA
        holder.nameTxt.setText(heroes.get(position).name);
        //holder.heroImage.setImageDrawable(Drawable.createFromStream(Utils.openImage(heroesList.get(position).getIcon(), c), null));
        GlideUtil.setImageHero(holder.heroImage, position,
                ProjectConstants.IMAGE_HERO_SMALL_WIDTH, ProjectConstants.IMAGE_HERO_SMALL_HEIGHT);

        //ITEM CLICK
        // ЛИСНЕР ПО КНОПКАМ КАРТОЧКИ
        holder.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                int buttonIndex = holder.getAdapterPosition();
                // передать buttonIndex в Activity

                RecyclerViewClickListener listener = (RecyclerViewClickListener) activity;
                listener.onItemClick(buttonIndex);
            }
        });

    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }
}