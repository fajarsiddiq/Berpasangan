package com.fajarsiddiq.berpasangan.module.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.fajarsiddiq.berpasangan.R;
import com.fajarsiddiq.berpasangan.helper.SQLiteHelper;
import com.fajarsiddiq.berpasangan.helper.SharedPreferenceHelper;
import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.fajarsiddiq.berpasangan.module.board.BoardActivity;
import com.fajarsiddiq.berpasangan.module.extra.store.StoreActivity;
import com.fajarsiddiq.berpasangan.module.statistic.StatisticActivity;
import com.fajarsiddiq.berpasangan.module.tutorial.TutorialActivity;
import com.fajarsiddiq.berpasangan.sqlite.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.nispok.snackbar.Snackbar;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.orm.SugarContext;

import static com.fajarsiddiq.berpasangan.R.array.array_level_name;
import static com.fajarsiddiq.berpasangan.R.id.id_extra_dialog_image_view_1;
import static com.fajarsiddiq.berpasangan.R.id.id_extra_dialog_image_view_2;
import static com.fajarsiddiq.berpasangan.R.id.id_extra_dialog_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_extra_dialog_text_view_1;
import static com.fajarsiddiq.berpasangan.R.id.id_extra_dialog_text_view_2;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_coin_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_extra_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_help_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_highscore_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_start_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_statistic_image_view;
import static com.fajarsiddiq.berpasangan.R.id.imageview_achievement;
import static com.fajarsiddiq.berpasangan.R.layout.layout_main_activity;
import static com.fajarsiddiq.berpasangan.R.layout.layout_main_fragment_extra_dialog;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_negative;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_title;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_select_level_title;
import static com.fajarsiddiq.berpasangan.helper.DialogHelper.buildDialog;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Muhammad Fajar on 17/03/2016.
 */
public class MainActivity extends ModuleActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    // Request code used to invoke sign in user interactions.
    private static final int RC_SIGN_IN = 9001;
    //for tutorial-related achievement
    private static final int TUTORIAL_LAUNCH_CODE = 100;

    private long duration;
    private ImageView mStartImageView;
    private ImageView mExtraImageView;
    private ImageView mStatisticImageView;
    private ImageView mHighscoreImageView;
    private ImageView mSettingImageView;
    private ImageView mExitImageView;
    private TextView mCoinTextView;
    private boolean isTutorialFinished = false;
    // Are we currently resolving a connection failure?
    private boolean mResolvingConnectionFailure = false;

    // Has the user clicked the sign-in button?
    private boolean mSignInClicked = false;

    // Set to true to automatically start the sign in flow when the Activity starts.
    // Set to false to require the user to click the button in order to sign in.
    private boolean mAutoStartSignInFlow = true;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("MainActivity", "Successfully signed in.");
        String username = Games.Players.getCurrentPlayer(getApiClient()).getDisplayName();
        if (!username.equals(SharedPreferenceHelper.getUsername(this))) {
            SharedPreferenceHelper.setUsername(this, username);
        }

        if (SharedPreferenceHelper.isNotFirstLogin(this)) {
            Snackbar.with(this)
                    .position(Snackbar.SnackbarPosition.TOP)
                    .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                    .text("Halo " + username + " :)").show(this);
        } else {
            SharedPreferenceHelper.setNotFirstLogin(this, true);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        getApiClient().connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            return;
        }

        if (mSignInClicked || mAutoStartSignInFlow) {
            mAutoStartSignInFlow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = BaseGameUtils.resolveConnectionFailure(this, getApiClient(), connectionResult, RC_SIGN_IN,
                    com.google.example.games.basegameutils.R.string.sign_in_other_error);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SugarContext.init(this);
        SQLiteHelper.prepareDb(this);
        super.onCreate(savedInstanceState);

        setContentView(layout_main_activity);
        duration = currentTimeMillis();
        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/NordRegular.ttf"))
                .create();
        TypefaceHelper.init(typeface);
        TypefaceHelper.typeface(this);
        Stetho.initializeWithDefaults(getApplicationContext());
        initUI();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getApiClient().isConnected()) {
            getApiClient().disconnect();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RC_SIGN_IN) {
//            Log.d(TAG, "onActivityResult with requestCode == RC_SIGN_IN, responseCode="
//                    + resultCode + ", intent=" + data);
//            mSignInClicked = false;
//            mResolvingConnectionFailure = false;
//            if (resultCode == RESULT_OK) {
//                getApiClient().connect();
//            } else {
//                BaseGameUtils.showActivityResultError(this,requestCode, resultCode, com.google.example.games.basegameutils.R.string.sign_in_other_error);
//            }
//        }
//    }

    private void initUI() {
        mStartImageView = (ImageView) findViewById(id_main_fragment_start_image_view);
        mExtraImageView = (ImageView) findViewById(id_main_fragment_extra_image_view);
        mStatisticImageView = (ImageView) findViewById(id_main_fragment_statistic_image_view);
        mSettingImageView = (ImageView) findViewById(id_main_fragment_help_image_view);
        mHighscoreImageView = (ImageView) findViewById(id_main_fragment_highscore_image_view);
        mExitImageView = (ImageView) findViewById(imageview_achievement);
        mCoinTextView = (TextView) findViewById(id_main_fragment_coin_text_view);
        refreshCoin();

        mStartImageView.setOnClickListener(this);
        mExtraImageView.setOnClickListener(this);
        mHighscoreImageView.setOnClickListener(this);
        mStatisticImageView.setOnClickListener(this);
        mSettingImageView.setOnClickListener(this);
        mExitImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (view.getId()) {
            case id_main_fragment_start_image_view:
                final String[] levels = getResources().getStringArray(array_level_name);
                builder.setTitle(getString(string_main_activity_select_level_title));
                builder.setItems(array_level_name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        for (int ii = 0; ii < levels.length; ii++) {
                            if (ii == which) {
                                int row = parseInt(levels[ii].split(" ")[0]);
                                int column = parseInt(levels[ii].split(" ")[2]);
                                startActivity(new Intent(getBaseContext(), BoardActivity.class).putExtra(BoardActivity.row, row).putExtra(BoardActivity.column, column).putExtra(BoardActivity.mode, ii));
                            }
                        }
                    }
                });
                builder.create().show();
                break;
            case id_main_fragment_extra_image_view:
                showDialogExtra();
                break;
            case id_main_fragment_highscore_image_view:
//                startActivity(new Intent(this, HighscoreActivity.class));
//                using highscore from Google Play Games
                startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()), 10001);
                break;
            case id_main_fragment_statistic_image_view:
                saveDuration();
                startActivity(new Intent(this, StatisticActivity.class));
                break;
            case id_main_fragment_help_image_view:
                showDialogSetting();
                break;
            case imageview_achievement:
                startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()),
                        10002);
                break;
            case id_extra_dialog_image_view_1:
                Log.i("Test", "Halo ensiklopedia");
                break;
            case id_extra_dialog_image_view_2:
                startActivity(new Intent(this, StoreActivity.class));
                break;
            case R.id.id_setting_tutorial:
                startActivityForResult(new Intent(this, TutorialActivity.class), TUTORIAL_LAUNCH_CODE);
        }
    }

    private void refreshCoin() {
        User user = User.findById(User.class, 1);
        mCoinTextView.setText(new StringBuilder(" x ").append(valueOf(user.getCoin())));
    }

    private void showDialogExtra() {
        Dialog mDialog = buildDialog(this, layout_main_fragment_extra_dialog);
        mDialog.findViewById(id_extra_dialog_image_view_1).setOnClickListener(this);
        mDialog.findViewById(id_extra_dialog_image_view_2).setOnClickListener(this);
        typeface(mDialog.findViewById(id_extra_dialog_text_view));
        typeface(mDialog.findViewById(id_extra_dialog_text_view_1));
        typeface(mDialog.findViewById(id_extra_dialog_text_view_2));
        mDialog.show();
    }

    private void showDialogSetting() {
        Dialog dialog = buildDialog(this, R.layout.layout_setting_dialog);
        ((Switch) dialog.findViewById(R.id.sound_switch)).setOnCheckedChangeListener(this);
        ((Switch) dialog.findViewById(R.id.sound_switch)).setChecked(SharedPreferenceHelper.isMusicSwitchOn(this));
        dialog.findViewById(R.id.id_setting_tutorial).setOnClickListener(this);
        typeface(dialog.findViewById(R.id.id_setting_description));
        typeface(dialog.findViewById(R.id.id_setting_sound));
        typeface(dialog.findViewById(R.id.id_setting_tutorial));
        dialog.show();
    }

    @Override
    protected void onActivityResult(int request, int response, Intent data) {
        super.onActivityResult(request, response, data);
        if (request == TUTORIAL_LAUNCH_CODE && response == RESULT_OK) {
            //unlock the achievement
            if (!SharedPreferenceHelper.isAchievementUnlocked(this, R.string.pref_tutorial)) {
                isTutorialFinished = true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(string_main_activity_exit_prompt))
                .setTitle(getString(string_main_activity_exit_prompt_title));
        builder.setPositiveButton(getString(string_main_activity_exit_prompt_positive), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                saveDuration();
                finish();
            }
        });
        builder.setNegativeButton(getString(string_main_activity_exit_prompt_negative), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        typeface(dialog.findViewById(android.R.id.message));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getApiClient().connect();
        refreshCoin();
    }

    private void saveDuration() {
        long prevDuration = SharedPreferenceHelper.getDuration(this);
        duration = currentTimeMillis() - duration;
        duration = duration + prevDuration;
        SharedPreferenceHelper.setDuration(this, duration);
        duration = currentTimeMillis();
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {
        //check for duration
        long duration = SharedPreferenceHelper.getDuration(this);
        if (duration > 3600000 && !SharedPreferenceHelper.isAchievementUnlocked(this, R.string.pref_fan)) {
            SharedPreferenceHelper.setAchievement(this, R.string.pref_fan);
            Games.Achievements.unlock(getApiClient(), getString(R.string.achievement_penggemar));
        }
        if (isTutorialFinished && !SharedPreferenceHelper.isAchievementUnlocked(this, R.string.pref_tutorial)) {
            SharedPreferenceHelper.setAchievement(this, R.string.pref_tutorial);
            Games.Achievements.unlock(getApiClient(), getString(R.string.achievement_paham_aturan));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferenceHelper.setMusicSwitch(this, b);
    }
}
