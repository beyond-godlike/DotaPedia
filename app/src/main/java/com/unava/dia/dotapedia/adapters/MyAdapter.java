package com.unava.dia.dotapedia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;
import com.unava.dia.dotapedia.data.Hero;

import java.util.ArrayList;

/**
 * Created by Dia on 29.08.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context c;
    ArrayList<Hero> heroes;

    public MyAdapter(Context c, ArrayList<Hero> h) {
        heroes = new ArrayList<>();

        this.c = c;
        this.heroes = h;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        View v = LayoutInflater.from(c).inflate(R.layout.model, null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        //BIND DATA
        holder.nameTxt.setText(heroes.get(position).name);

        //ITEM CLICK
        holder.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                //Toast.makeText(c.getApplicationContext(), languages.get(pos), Toast.LENGTH_SHORT).show();
                holder.nameTxt.setText("CLICKED");
            }
        });

    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }
}
