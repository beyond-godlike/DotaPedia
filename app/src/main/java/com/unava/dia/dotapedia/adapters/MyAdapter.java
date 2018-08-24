package com.unava.dia.dotapedia.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.Hero;

import java.util.List;

/**
 * Created by Dia on 24.08.2018.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Hero> heroes;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public TextView heroName;


        public MyViewHolder(View v) {
            super(v);

            cv = v.findViewById(R.id.cv);

            heroName = v.findViewById(R.id.hero_name);
        }
    }


    public MyAdapter(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_layout, viewGroup, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        //holder.heroName.setText(heroes.get(i).name);

        holder.heroName.setText("ERROR");
    }

    @Override
    public int getItemCount() {
        if (heroes != null) return heroes.size();
        else
        {
            return -1;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv) {
        super.onAttachedToRecyclerView(rv);
    }
}