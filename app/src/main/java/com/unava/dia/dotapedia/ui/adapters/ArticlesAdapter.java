package com.unava.dia.dotapedia.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.Html;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.model.UpdateArticle;
import com.unava.dia.dotapedia.ui.activity.Article;
import com.unava.dia.dotapedia.utils.RecyclerViewClickListener;

import java.util.ArrayList;

/**
 * Created by Dia on 25.09.2018.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {
    ArrayList<UpdateArticle> articlesList;
    Context c;
    Activity activity;

    public ArticlesAdapter(Context c, ArrayList<UpdateArticle> l, Activity activity) {
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
        holder.title.setText(articlesList.get(position).getTitleStr());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.description.setText(Html.fromHtml(articlesList.get(position).getDescriptionStr(),
                    Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.description.setText(Html.fromHtml(articlesList.get(position).getDescriptionStr()));
        }
        holder.date.setText(articlesList.get(position).getDateStr());

        // ЛИСНЕР ПО КНОПКАМ КАРТОЧКИ
        holder.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                int buttonIndex = holder.getAdapterPosition();

                Intent intent = new Intent(activity, Article.class);
                intent.setFlags(buttonIndex);
                intent.putExtra("URL_TO_FULL_ARTICLE", articlesList.get(buttonIndex).getUrlToFullStr());
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
