package com.unava.dia.dotapedia.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.RecyclerViewClickListener;

/**
 * Created by Dia on 14.10.2018.
 */

public class ChooseHeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    RecyclerViewClickListener itemClickListener;
    TextView heroName;

    public ChooseHeroViewHolder(View itemView) {
        super(itemView);

        heroName = (TextView) itemView.findViewById(R.id.hero_name);

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
