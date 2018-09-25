package com.unava.dia.dotapedia.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;

/**
 * Created by Dia on 25.09.2018.
 */

public class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    RecyclerViewClickListener itemClickListener;
    TextView articleName;

    public ArticlesViewHolder(View itemView) {
        super(itemView);

        articleName = (TextView) itemView.findViewById(R.id.article_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(RecyclerViewClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
