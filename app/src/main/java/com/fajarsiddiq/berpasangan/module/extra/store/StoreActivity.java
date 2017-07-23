package com.fajarsiddiq.berpasangan.module.extra.store;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.fajarsiddiq.berpasangan.sqlite.User;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.id.id_store_coin_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_store_frame_layout;
import static com.fajarsiddiq.berpasangan.R.layout.layout_store_activity;
import static java.lang.String.valueOf;
/**
 * Created by fajar on 8/10/16.
 */

public class StoreActivity extends ModuleActivity {
    public final static String ENCYCLOPEDIA_TAG = "Encyclopedia";
    public final static String CARD_TAG = "Card";
    public final static String BOARD_TAG = "Board";
    final static String STORE_TAG = "Store";
    int mCoin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_store_activity);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(manager.findFragmentByTag(STORE_TAG) == null) {
            transaction.add(id_store_frame_layout, new StoreFragment(), STORE_TAG);
            transaction.addToBackStack(STORE_TAG);
        } else
            transaction.replace(id_store_frame_layout, new StoreFragment(), STORE_TAG);
        transaction.commit();

        getCoinInfo();

        TypefaceHelper.typeface(this);

    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(id_store_frame_layout);
//        if(fragment instanceof StoreContentFragment) {
//            FragmentTransaction transaction = manager.beginTransaction();
//            String tag = manager.getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
//            transaction.replace(id_store_frame_layout, manager.findFragmentByTag(tag));
//            transaction.commit();
//        } else {
            finish();
//        }
    }

    private void getCoinInfo() {
        new CoinTask().execute();
    }

    void assignCoinValue(final int coin) {
        ((TextView) findViewById(id_store_coin_text_view)).setText(valueOf(coin));
        mCoin = coin;
    }

    private class CoinTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            User user = User.findById(User.class, 1);
            return user.getCoin();
        }

        @Override
        protected void onPostExecute(Integer coin) {
            assignCoinValue(coin);
        }
    }
}
