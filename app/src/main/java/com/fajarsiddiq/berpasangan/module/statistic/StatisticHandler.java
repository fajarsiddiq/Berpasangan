package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.Message;
import android.util.TypedValue;

import com.fajarsiddiq.berpasangan.helper.SharedPreferenceHelper;
import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.User;

import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_average_accuracy;
import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_average_score;
import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_play_duration;
import static com.fajarsiddiq.berpasangan.R.string.string_stats_fragment_play_duration_no_hour;
import static java.lang.Double.isNaN;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticHandler extends ModuleHandler {
    public int mWhatData;

    StatisticHandler(final StatisticActivity activity) {
        super(activity);
        mWhatData = mWhat + 1;
    }

    @Override
    public void handleMessage(Message message) {
        if(message.what == mWhatData) {
            StatisticActivity activity = (StatisticActivity) mActivity;
            User user = (User) message.obj;
            activity.getBestScoreTextView().setText(valueOf(user.getBestScore()));
            activity.getTotalCoinTextView().setText(valueOf(user.getCoin()));
            activity.getTotalGameTextView().setText(valueOf(user.getTotalGame()));
            int totalGame = user.getTotalGame();
            double accuracy = user.getTotalAccuracy() / totalGame;
            double score = (double) user.getTotalScore() / totalGame;
            activity.getAverageAccuracyTextView().setText(format(activity.getString(string_stats_fragment_average_accuracy),  isNaN(accuracy) ? 0.00 : accuracy * 100));
            activity.getAverageScoreTextView().setText(format(activity.getString(string_stats_fragment_average_score), isNaN(score) ? 0.00 : score));
            activity.getCoinSpentTextView().setText(valueOf(user.getCoinSpent()));
            activity.getEncyclopediaUnlockedTextView().setText(valueOf(user.getTotalEncyclopediaUnlocked()));
            activity.getEncyclopediaReadTextView().setText(valueOf(user.getGetTotalEncyclopediaRead()));
            long duration = SharedPreferenceHelper.getDuration(activity);
            int hours = 0;
            long minutes = MILLISECONDS.toMinutes(duration);
            if(minutes > 59) {
                hours = (int) minutes / 60;
                minutes = minutes % 60;
            }
            if(hours > 0) {
                activity.getPlayDurationTextView().setText(format(activity.getString(string_stats_fragment_play_duration), hours, (int) minutes));
            } else {
                activity.getPlayDurationTextView().setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                activity.getPlayDurationTextView().setText(format(activity.getString(string_stats_fragment_play_duration_no_hour), (int) minutes));
            }
        }
    }
}
