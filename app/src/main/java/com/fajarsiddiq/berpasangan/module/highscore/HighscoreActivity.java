package com.fajarsiddiq.berpasangan.module.highscore;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

import butterknife.BindView;

import static butterknife.ButterKnife.bind;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_fragment_no_data_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_highscores_fragment_linear_layout;
import static com.fajarsiddiq.berpasangan.R.layout.layout_highscore_activity;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class HighscoreActivity extends ModuleActivity {
    @BindView(id_highscores_fragment_linear_layout)
    LinearLayout mLinearLayout;
    @BindView(id_highscore_fragment_no_data_text_view)
    TextView mNoDataTextView;

    private HighscoreAdapter mAdapter;

    private void initUI() {
        typeface(this);
        bind(this);
        new HighscoreController(this);
    }

    public LinearLayout getLinearLayout() {
        return mLinearLayout;
    }

    public TextView getNoDataTextView() {
        return mNoDataTextView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_highscore_activity);
        TypefaceHelper.typeface(this);
        initUI();
    }
}
