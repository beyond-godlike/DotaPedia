package com.unava.dia.dotapedia.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;
import com.unava.dia.dotapedia.ui.activity.HeroConstructor;
import com.unava.dia.dotapedia.data.model.DotaHero;
import io.realm.RealmResults;

/**
 * Created by Dia on 14.10.2018.
 */

public class ChooseHeroAdapter extends RecyclerView.Adapter<ChooseHeroViewHolder>{
    RealmResults<DotaHero> heroesList;
    Context c;
    Activity activity;

    public ChooseHeroAdapter(Context c, RealmResults<DotaHero> l, Activity activity) {
        this.c = c;
        this.heroesList = l;
        this.activity = activity;
    }

    @Override
    public ChooseHeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);

        return new ChooseHeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ChooseHeroViewHolder holder, int position) {
        holder.heroName.setText(heroesList.get(position).getName());

        // ЛИСНЕР ПО КНОПКАМ КАРТОЧКИ
        holder.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                int buttonIndex = holder.getAdapterPosition();

                Intent intent = new Intent(activity, HeroConstructor.class);
                intent.setFlags(buttonIndex);
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return heroesList.size();
    }
}
