package com.unava.dia.dotapedia.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;

/**
 * Created by Dia on 29.08.2018.
 */

public class InformationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nameTxt;
    RecyclerViewClickListener itemClickListener;

    public InformationViewHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.hero_name);

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
