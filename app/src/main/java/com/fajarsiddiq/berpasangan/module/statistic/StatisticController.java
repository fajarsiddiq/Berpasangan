package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.AsyncTask;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.sqlite.User;

import static android.os.Message.obtain;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticController extends ModuleController {
    private StatisticHandler mHandler;

    StatisticController(final StatisticActivity activity) {
        super(activity);
        mHandler = new StatisticHandler(activity);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Void, User> {

        @Override
        protected User doInBackground(Void... params) {
            User user = User.findById(User.class, 1);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatData, user));
        }
    }
}
