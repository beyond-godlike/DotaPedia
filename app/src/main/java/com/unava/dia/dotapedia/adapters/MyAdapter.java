package com.unava.dia.dotapedia.adapters;

import android.app.Activity;
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
    Activity activity;

    public MyAdapter(Context c, ArrayList<Hero> h, Activity activity) {
        heroes = new ArrayList<>();

        this.c = c;
        this.heroes = h;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(c).inflate(R.layout.model, null);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);
        View v = LayoutInflater.from(c).inflate(R.layout.model, parent, false); // fill_parent works

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        //BIND DATA
        holder.nameTxt.setText(heroes.get(position).name);

        //ITEM CLICK
        // ЛИСНЕР ПО КНОПКАМ КАРТОЧКИ
        holder.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                //holder.nameTxt.setText(new Integer(holder.getAdapterPosition()).toString());
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
