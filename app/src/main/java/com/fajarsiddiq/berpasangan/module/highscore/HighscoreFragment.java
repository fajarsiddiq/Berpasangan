package com.fajarsiddiq.berpasangan.module.highscore;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.fajarsiddiq.berpasangan.sqlite.Highscore;

import java.util.List;

import butterknife.BindView;

import static com.fajarsiddiq.berpasangan.R.id.id_highscore_fragment_no_data_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_highscores_fragment_linear_layout;
import static com.fajarsiddiq.berpasangan.R.layout.layout_highscores_fragment;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;
import static butterknife.ButterKnife.bind;

/**
 * Created by Muhammad Fajar on 15/06/2016.
 */
public class HighscoreFragment extends ModuleFragment {

    @BindView(id_highscores_fragment_linear_layout)
    LinearLayout mLinearLayout;
    @BindView(id_highscore_fragment_no_data_text_view)
    TextView mNoDataTextView;

    private HighscoreAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_highscores_fragment, null);
        typeface(view);
        bind(this, view);
        new HighscoreController(this);
        return view;
    }

    public LinearLayout getLinearLayout() {
        return mLinearLayout;
    }

    public TextView getNoDataTextView() {
        return mNoDataTextView;
    }
}
