package com.fajarsiddiq.berpasangan.module.statistic.achievement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.fajarsiddiq.berpasangan.R.layout.layout_achievement_fragment;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

/**
 * Created by Muhammad Fajar on 08/06/2016.
 */
public class AchievementFragment extends ModuleFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_achievement_fragment, null);
        typeface(view);
        return view;
    }
}
