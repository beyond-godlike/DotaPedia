package com.unava.dia.dotapedia.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.utils.RecyclerViewClickListener;

/**
 * Created by Dia on 25.09.2018.
 */
public class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    RecyclerViewClickListener itemClickListener;
    TextView title;
    TextView description;
    TextView date;

    public ArticlesViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        date = (TextView) itemView.findViewById(R.id.date);

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
