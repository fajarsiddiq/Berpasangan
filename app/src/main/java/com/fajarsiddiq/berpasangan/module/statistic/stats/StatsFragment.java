package com.fajarsiddiq.berpasangan.module.statistic.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_average_accuracy_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_average_score_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_coin_spent_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_encyclopedia_read_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_encyclopedia_unlocked_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_play_duration_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_stats_fragment;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

/**
 * Created by Muhammad Fajar on 08/06/2016.
 */
public class StatsFragment extends ModuleFragment {
    private TextView mAverageScoreTextView;
    private TextView mAverageAccuracyTextView;
    private TextView mEncyclopediaUnlockedTextView;
    private TextView mEncyclopediaReadTextView;
    private TextView mPlayDurationTextView;
    private TextView mCoinSpentTextView;
    private StatsController mController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_stats_fragment, null);
        mAverageAccuracyTextView = (TextView) view.findViewById(id_stats_fragment_average_accuracy_text_view);
        mAverageScoreTextView = (TextView) view.findViewById(id_stats_fragment_average_score_text_view);
        mEncyclopediaUnlockedTextView = (TextView) view.findViewById(id_stats_fragment_encyclopedia_unlocked_text_view);
        mEncyclopediaReadTextView = (TextView) view.findViewById(id_stats_fragment_encyclopedia_read_text_view);
        mPlayDurationTextView = (TextView) view.findViewById(id_stats_fragment_play_duration_text_view);
        mCoinSpentTextView = (TextView) view.findViewById(id_stats_fragment_coin_spent_text_view);
        typeface(view);
        mController = new StatsController(this);
        return view;
    }

    public TextView getAverageScoreTextView() {
        return mAverageScoreTextView;
    }

    public TextView getAverageAccuracyTextView() {
        return mAverageAccuracyTextView;
    }

    public TextView getEncyclopediaUnlockedTextView() {
        return mEncyclopediaUnlockedTextView;
    }

    public TextView getEncyclopediaReadTextView() {
        return mEncyclopediaReadTextView;
    }

    public TextView getPlayDurationTextView() {
        return mPlayDurationTextView;
    }

    public TextView getCoinSpentTextView() {
        return mCoinSpentTextView;
    }
}
