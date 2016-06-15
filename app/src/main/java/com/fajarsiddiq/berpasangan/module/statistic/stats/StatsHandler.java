package com.fajarsiddiq.berpasangan.module.statistic.stats;

import android.os.Message;
import android.util.Log;
import android.util.TypedValue;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.User;

import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_average_score;
import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_play_duration;
import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_play_duration_no_hour;
import static java.lang.Double.isNaN;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_average_accuracy;


/**
 * Created by Muhammad Fajar on 12/06/2016.
 */
public class StatsHandler extends ModuleHandler {
    public int mWhatData;

    StatsHandler(final StatsFragment fragment) {
        super(fragment);
        mWhatData = mWhat + 1;
    }

    @Override
    public void handleMessage(Message message) {
        if(message.what == mWhatData) {
            StatsFragment fragment = (StatsFragment) mFragment;
            User user = (User) message.obj;
            int totalGame = user.getTotalGame();
            double accuracy = user.getTotalAccuracy() / totalGame;
            double score = (double) user.getTotalScore() / totalGame;
            fragment.getAverageAccuracyTextView().setText(format(fragment.getString(string_stats_fragment_average_accuracy),  isNaN(accuracy) ? 0.00 : accuracy * 100));
            fragment.getAverageScoreTextView().setText(format(fragment.getString(string_stats_fragment_average_score), isNaN(score) ? 0.00 : score));
            fragment.getCoinSpentTextView().setText(valueOf(user.getCoinSpent()));
            fragment.getEncyclopediaUnlockedTextView().setText(valueOf(user.getTotalEncyclopediaUnlocked()));
            fragment.getEncyclopediaReadTextView().setText(valueOf(user.getGetTotalEncyclopediaRead()));
            long duration = user.getTotalDuration();
            int hours = 0;
            long minutes = MILLISECONDS.toMinutes(duration);
            if(minutes > 59) {
                hours = (int) minutes / 60;
                minutes = minutes % 60;
            }
            if(hours > 0) {
                fragment.getPlayDurationTextView().setText(format(fragment.getString(string_stats_fragment_play_duration), hours, (int) minutes));
            } else {
                fragment.getPlayDurationTextView().setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                fragment.getPlayDurationTextView().setText(format(fragment.getString(string_stats_fragment_play_duration_no_hour), (int) minutes));
            }
        }
    }
}
