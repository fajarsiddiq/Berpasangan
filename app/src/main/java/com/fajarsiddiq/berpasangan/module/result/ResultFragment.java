package com.fajarsiddiq.berpasangan.module.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultFragment extends ModuleFragment {
    private ResultController mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(0, null);
        mController = new ResultController(this);
        return view;
    }
}
