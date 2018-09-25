package com.unava.dia.dotapedia.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;
import com.unava.dia.dotapedia.activity.Article;

import java.util.ArrayList;

/**
 * Created by Dia on 25.09.2018.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {
    ArrayList<String> articlesList;
    Context c;
    Activity activity;

    public ArticlesAdapter(Context c, ArrayList<String> l, Activity activity) {
        articlesList = new ArrayList<>();

        this.c = c;
        this.articlesList = l;
        this.activity = activity;
    }

    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_model, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_model, parent, false);


        //TODO
        return new ArticlesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ArticlesViewHolder holder, int position) {
        holder.articleName.setText(articlesList.get(position).toString());

        // ЛИСНЕР ПО КНОПКАМ КАРТОЧКИ
        holder.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                int buttonIndex = holder.getAdapterPosition();

                Intent intent = new Intent(activity, Article.class);
                intent.setFlags(buttonIndex);
                activity.startActivity(intent);

                // открываем статью, которая соответствует индексу через
                // Intent и передаем туда индекс

            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

}
