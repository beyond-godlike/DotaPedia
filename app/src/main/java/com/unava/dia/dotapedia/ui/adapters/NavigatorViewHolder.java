package com.unava.dia.dotapedia.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.utils.RecyclerViewClickListener;

/**
 * Created by Dia on 18.11.2018.
 */

public class NavigatorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nameTxt;
    ImageView heroImage;
    RecyclerViewClickListener itemClickListener;

    public NavigatorViewHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.hero_name);
        heroImage = (ImageView) itemView.findViewById(R.id.cardImageView);

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
