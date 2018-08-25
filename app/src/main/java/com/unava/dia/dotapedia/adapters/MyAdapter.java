package com.unava.dia.dotapedia.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    //ctor
    public MyAdapter(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_layout, viewGroup, false);
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_layout,null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        holder.heroName.setText(heroes.get(i).name);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv) {
        super.onAttachedToRecyclerView(rv);
    }

    @Override
    public int getItemCount() {
        if (heroes != null) return heroes.size();
        else
        {
            return -1;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cv;
        public TextView heroName;


        public MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            cv = v.findViewById(R.id.cv);

            heroName = v.findViewById(R.id.hero_name);
        }

        @Override
        public void onClick(View v) {
            Log.d("LOG", "Был нажат " + v.getId());
        }
    }

}