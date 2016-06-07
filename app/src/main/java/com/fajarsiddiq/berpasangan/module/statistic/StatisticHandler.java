package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.Message;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.User;

import static java.lang.String.valueOf;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticHandler extends ModuleHandler {
    public int mWhatData;

    StatisticHandler(final StatisticFragment fragment) {
        super(fragment);
        mWhatData = mWhat + 1;
    }

    @Override
    public void handleMessage(Message message) {
        if(message.what == mWhatData) {
            StatisticFragment fragment = (StatisticFragment) mFragment;
            User user = (User) message.obj;
            fragment.getBestScoreTextView().setText(valueOf(user.getBestScore()));
            fragment.getTotalCoinTextView().setText(valueOf(user.getCoin()));
            fragment.getTotalGameTextView().setText(valueOf(user.getTotalGame()));
        }
    }
}
