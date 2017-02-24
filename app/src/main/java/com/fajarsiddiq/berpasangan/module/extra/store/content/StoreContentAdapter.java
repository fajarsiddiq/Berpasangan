package com.fajarsiddiq.berpasangan.module.extra.store.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.helper.Drawable;
import com.fajarsiddiq.berpasangan.sqlite.Highscore;
import com.fajarsiddiq.berpasangan.sqlite.StoreContent;

import java.util.List;

import butterknife.BindView;

import static android.view.LayoutInflater.from;
import static butterknife.ButterKnife.bind;
import static com.fajarsiddiq.berpasangan.R.id.id_store_content_linear_layout;
import static com.fajarsiddiq.berpasangan.R.id.id_store_item_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_store_item_lock;
import static com.fajarsiddiq.berpasangan.R.id.id_store_item_text_view;
import static com.fajarsiddiq.berpasangan.R.drawable.drawable_locked;
import static com.fajarsiddiq.berpasangan.R.layout.layout_store_item;
import static java.lang.String.valueOf;
import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by fajar on 8/15/16.
 */

public class StoreContentAdapter extends Adapter<StoreContentAdapter.ViewHolder> {
    private List<StoreContent> list;
    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(id_store_content_linear_layout)
        FrameLayout frame;
        @BindView(id_store_item_text_view)
        TextView name;
        @BindView(id_store_item_image_view)
        ImageView icon;
        @BindView(id_store_item_lock)
        ImageView lock;

        public ViewHolder(View view) {
            super(view);
            bind(this, view);
        }
    }

    public StoreContentAdapter(List<StoreContent> storeContents, final Context context) {
        this.list = storeContents;
        mContext = context;
    }

    @Override
    public StoreContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = from(parent.getContext()).inflate(layout_store_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoreContentAdapter.ViewHolder holder, int position) {
        StoreContent content = list.get(position);
        holder.name.setText(content.getName());
        holder.lock.setImageResource(content.getUnlocked() == 0 ? drawable_locked : 0);
        holder.icon.setImageResource(Drawable.getDrawable(mContext, content.getResName()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
