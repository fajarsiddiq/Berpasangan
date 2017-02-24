package com.fajarsiddiq.berpasangan.module.extra.store.content;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.norbsoft.typefacehelper.TypefaceHelper;

import butterknife.BindView;

import static com.fajarsiddiq.berpasangan.R.layout.layout_store_content;
import static com.fajarsiddiq.berpasangan.R.id.id_store_content_recycler_view;
import static butterknife.ButterKnife.bind;
/**
 * Created by fajar on 8/11/16.
 */

public class StoreContentFragment extends ModuleFragment {
    @BindView(id_store_content_recycler_view)
    RecyclerView mRecyclerView;
    private StoreContentController mController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_store_content, null);
        bind(this, view);
        TypefaceHelper.typeface(view);
        mController = new StoreContentController(this);
        mController.searchContent(getArguments().getString("Key"));
        return view;
    }
}
