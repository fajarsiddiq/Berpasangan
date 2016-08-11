package com.fajarsiddiq.berpasangan.module.extra.store.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.layout.layout_store_content;
/**
 * Created by fajar on 8/11/16.
 */

public class StoreContentFragment extends ModuleFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_store_content, null);
        TypefaceHelper.typeface(view);
        return view;
    }
}
