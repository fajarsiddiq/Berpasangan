package com.fajarsiddiq.berpasangan.module.highscore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.sqlite.Highscore;

import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.RecyclerView.Adapter;
import static butterknife.ButterKnife.bind;
import static android.view.LayoutInflater.from;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_score;
import static java.lang.String.valueOf;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_mode;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_timestamp;
import static com.fajarsiddiq.berpasangan.R.layout.layout_highscore;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class HighscoreAdapter extends Adapter<HighscoreAdapter.ViewHolder> {

    private List<Highscore> list;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(id_highscore_score)
        TextView score;
        @BindView(id_highscore_mode)
        TextView mode;
        @BindView(id_highscore_timestamp)
        TextView timestamp;

        public ViewHolder(View view) {
            super(view);
            bind(this, view);
        }
    }

    public HighscoreAdapter(List<Highscore> highscoreList) {
        this.list = highscoreList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = from(parent.getContext()).inflate(layout_highscore, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Highscore highscore = list.get(position);
        holder.score.setText(valueOf(highscore.getScore()));
        holder.mode.setText(valueOf(highscore.getMode()));
        holder.timestamp.setText(valueOf(highscore.getTimeStamp()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
