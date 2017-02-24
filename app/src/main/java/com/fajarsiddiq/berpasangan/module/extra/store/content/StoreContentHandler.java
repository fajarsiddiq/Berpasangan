package com.fajarsiddiq.berpasangan.module.extra.store.content;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;

/**
 * Created by fajar on 8/22/16.
 */

public class StoreContentHandler extends ModuleHandler {
    public final int mWhatRefresh;

    StoreContentHandler(final StoreContentFragment fragment) {
        super(fragment);
        mWhatRefresh = mWhat + 1;
    }
}
