package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_best_score_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_total_coin_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_total_game_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_statistic_fragment;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticFragment extends ModuleFragment {
    private TextView mTotalGameTextView;
    private TextView mBestScoreTextView;
    private TextView mTotalCoinTextView;
    private StatisticController mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_statistic_fragment, null);
        mTotalCoinTextView = (TextView) view.findViewById(id_statistic_fragment_total_coin_text_view);
        mBestScoreTextView = (TextView) view.findViewById(id_statistic_fragment_best_score_text_view);
        mTotalGameTextView = (TextView) view.findViewById(id_statistic_fragment_total_game_text_view);
        mController = new StatisticController(this);
        return view;
    }

    public TextView getTotalGameTextView() {
        return mTotalGameTextView;
    }

    public TextView getBestScoreTextView() {
        return mBestScoreTextView;
    }

    public TextView getTotalCoinTextView() {
        return mTotalCoinTextView;
    }
}
