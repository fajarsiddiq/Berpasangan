package com.fajarsiddiq.berpasangan.module.result;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.R;
import com.fajarsiddiq.berpasangan.helper.GameMode;
import com.fajarsiddiq.berpasangan.helper.SharedPreferenceHelper;
import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.fajarsiddiq.berpasangan.sqlite.User;
import com.google.android.gms.games.Games;
import com.norbsoft.typefacehelper.TypefaceHelper;

import java.text.DecimalFormat;

import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_accuracy_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_avatar_1;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_avatar_2;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_coin_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_correct_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_high_score_button;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_main_menu_button;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_time_left_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_total_score_result_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_highscore_avatar_pick_dialog;
import static com.fajarsiddiq.berpasangan.R.layout.layout_result_activity;
import static com.fajarsiddiq.berpasangan.R.string.string_result_fragment_accuracy_result;
import static com.fajarsiddiq.berpasangan.R.string.string_result_fragment_correct_result;
import static com.fajarsiddiq.berpasangan.R.string.string_result_fragment_time_left_result;
import static java.lang.String.format;
import static java.lang.String.valueOf;

/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultActivity extends ModuleActivity implements View.OnClickListener {
    public static String data = ResultActivity.class.getName() + ".data";

    private Button mHighScoreButton;
    private Button mMainMenuButton;
    private ResultController mController;
    private TextView mCorrectTextView;
    private TextView mAccuracyTextView;
    private TextView mTimeLeftTextView;
    private TextView mScoreTextView;
    private TextView mCoinTextView;
    private User user;
    private int finalScore, mode, finalAccuracy;
    private boolean isWarmUpDone = false, isSuperheroDone = false, isPlentyOfCoins = false, isSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_result_activity);
        TypefaceHelper.typeface(this);
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getApiClient().connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getApiClient().isConnected()) {
            getApiClient().disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void initUI() {
        mHighScoreButton = (Button) findViewById(id_result_fragment_high_score_button);
        mHighScoreButton.setOnClickListener(this);
        mMainMenuButton = (Button) findViewById(id_result_fragment_main_menu_button);
        mMainMenuButton.setOnClickListener(this);
        mAccuracyTextView = (TextView) findViewById(id_result_fragment_accuracy_result_text_view);
        mCoinTextView = (TextView) findViewById(id_result_fragment_coin_result_text_view);
        mCorrectTextView = (TextView) findViewById(id_result_fragment_correct_result_text_view);
        mScoreTextView = (TextView) findViewById(id_result_fragment_total_score_result_text_view);
        mTimeLeftTextView = (TextView) findViewById(id_result_fragment_time_left_result_text_view);
        mController = new ResultController(this);
        user = new User();
        int [] data = this.getIntent().getIntArrayExtra(ResultActivity.data);
        refreshData(data);
    }

    @Override
    public void onClick(final View view) {
        if(view.getId() == id_result_fragment_high_score_button) {
            startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()), 10001);
        } else if(view.getId() == id_result_fragment_main_menu_button) {
            this.finish();
        } else if(view.getId() == id_result_fragment_avatar_1) {
            Log.i("Test", "Avatar pertama dipilih");
        } else if(view.getId() == id_result_fragment_avatar_2) {
            Log.i("Test", "Avatar kedua dipilih");
        }
    }

    private void refreshData(final int[] data) {
        final int attempt = data[0] / 2, answer = data[1], question = data[2], time = data[3], score = data[4], zonk = data[5], mode = data[6];
        final double accuracy = ((double) answer) / attempt;
        user.setTotalAccuracy(accuracy);
        mCorrectTextView.setText(format(getString(string_result_fragment_correct_result), answer, question));
        mAccuracyTextView.setText(format(getString(string_result_fragment_accuracy_result), accuracy * 100));
        mTimeLeftTextView.setText(format(getString(string_result_fragment_time_left_result), time));
        DecimalFormat decimalFormat = new DecimalFormat("#");
        double res = Double.valueOf(decimalFormat.format(accuracy*1000));
        finalAccuracy = (int) res;
        final int finalScore = score + (int) (accuracy * 100) + (time * 10);
        mScoreTextView.setText(valueOf(finalScore));
        user.setBestScore(finalScore);
        mCoinTextView.setText(new StringBuilder(" + ").append(finalScore > 0 ? valueOf(finalScore / 10) : valueOf(0)));
        if(finalScore > 0) {
            user.setCoin(finalScore / 10);
            user.setTotalScore(finalScore);
        }
        user.setTotalZonk(zonk);
        this.finalScore = finalScore;
        this.mode = mode;
        //use highscore from Google Play Games
//        final Highscore highscore = new Highscore(currentTimeMillis(), "FJR", finalScore, valueOf(mode));
//        saveData(highscore);
        saveData();

        //warmup, superhero, and plenty of coins achievement
        isWarmUpDone = answer == question && mode == GameMode.MODE_4x4.getMode() && !SharedPreferenceHelper.isAchievementUnlocked(this, R.string.pref_warmup);
        isSuperheroDone = answer == question && mode == GameMode.MODE_6x5.getMode() && !SharedPreferenceHelper.isAchievementUnlocked(this, R.string.pref_superhero);
        isPlentyOfCoins = finalScore >= 400 && !SharedPreferenceHelper.isAchievementUnlocked(this, R.string.pref_accuracy);
    }

    private void saveData() {
        mController.saveData(user);
    }

    private void submitData() {
        if (getApiClient().isConnected()) {
            String leaderboardId;
            switch (mode) {
                case 0:
                    leaderboardId = getString(R.string.leaderboard_skor_tertinggi_4x4);
                    break;
                case 1:
                    leaderboardId = getString(R.string.leaderboard_skor_tertinggi_5x5);
                    break;
                case 2:
                    leaderboardId = getString(R.string.leaderboard_skor_tertinggi_6x5);
                    break;
                default:
                    leaderboardId = null;
            }
            if (leaderboardId != null) {
                Games.Leaderboards.submitScore(getApiClient(), leaderboardId, finalScore);
            }
            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard_koin_terbanyak), user.getCoin());
            if (isWarmUpDone) {
                SharedPreferenceHelper.setAchievement(this, R.string.pref_warmup);
                Games.Achievements.unlock(getApiClient(), getString(R.string.achievement_pemanasan));
            }
            if (isSuperheroDone) {
                SharedPreferenceHelper.setAchievement(this, R.string.pref_superhero);
                Games.Achievements.unlock(getApiClient(), getString(R.string.achievement_superhero));
            }
            if (isPlentyOfCoins) {
                SharedPreferenceHelper.setAchievement(this, R.string.pref_coins);
                Games.Achievements.unlock(getApiClient(), getString(R.string.achievement_panen_koin));
            }
            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard_akurasi), finalAccuracy);
            if (finalAccuracy > 650) {
                SharedPreferenceHelper.setAchievement(this, R.string.pref_accuracy);
                Games.Achievements.unlock(getApiClient(), getString(R.string.achievement_ingatan_kuat));
            }
        }
    }

    public void showAvatarDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(layout_highscore_avatar_pick_dialog);
        dialog.setTitle("Selamat!");
        ImageView avatar = (ImageView) dialog.findViewById(id_result_fragment_avatar_1);
        avatar.setOnClickListener(this);
        avatar = (ImageView) dialog.findViewById(id_result_fragment_avatar_2);
        avatar.setOnClickListener(this);
        dialog.show();
    }

    @Override
    public void onSignInFailed() {
        Log.i("ResultActivity", "Sign in failed");
    }

    @Override
    public void onSignInSucceeded() {
        if (!isSent) {
            submitData();
            isSent = true;
        }
    }
}
