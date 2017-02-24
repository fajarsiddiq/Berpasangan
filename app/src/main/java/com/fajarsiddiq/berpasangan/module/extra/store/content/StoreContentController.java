package com.fajarsiddiq.berpasangan.module.extra.store.content;

import android.os.AsyncTask;
import android.util.Log;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.module.extra.store.StoreActivity;
import com.fajarsiddiq.berpasangan.sqlite.StoreContent;

import java.util.List;

import static android.os.Message.obtain;

/**
 * Created by fajar on 8/22/16.
 */

public class StoreContentController extends ModuleController {
    private StoreContentHandler mHandler;

    StoreContentController(final StoreContentFragment fragment) {
        super(fragment.getContext());
        mHandler = new StoreContentHandler(fragment);
    }

    public void searchContent(final String category) {
        new InitStore().execute(category);
    }

    private class InitStore extends AsyncTask<String, Void, List<StoreContent>> {
        @Override
        protected List<StoreContent> doInBackground(String... strings) {
            final String category = strings[0];
            List<StoreContent> contents;
            if(category.equals(StoreActivity.ENCYCLOPEDIA_TAG))
                contents = StoreContent.find(StoreContent.class, "type = ?", "1");
            else
                return null;
            Log.i("Test", contents.size() + "");
            return contents;
        }

        @Override
        protected void onPostExecute(List<StoreContent> storeContents) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatRefresh, storeContents));
        }
    }
}
