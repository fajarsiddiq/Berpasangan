package com.fajarsiddiq.berpasangan.module;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ModuleViewPagerAdapter extends FragmentPagerAdapter {
    private final ModuleFragment[] mFragments;

    public ModuleViewPagerAdapter(final ModuleFragment[] fragments, final FragmentManager manager) {
        super(manager);
        mFragments = fragments;
    }

    public final ModuleFragment[] getItems() {
        return mFragments;
    }

    public final ModuleFragment getItem(final int position) {
        return mFragments == null ? null : mFragments[position];
    }

    public final int getCount() {
        return mFragments == null ? 0 : mFragments.length;
    }
}
