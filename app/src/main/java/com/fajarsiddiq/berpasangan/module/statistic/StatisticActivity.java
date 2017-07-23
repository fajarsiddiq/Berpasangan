package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.Bundle;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.helper.ImageLoader;
import com.fajarsiddiq.berpasangan.helper.SharedPreferenceHelper;
import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_best_score_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_name_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_total_coin_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_total_game_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_average_accuracy_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_average_score_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_coin_spent_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_encyclopedia_read_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_encyclopedia_unlocked_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_stats_fragment_play_duration_text_view;
import static com.fajarsiddiq.berpasangan.R.id.statistic_avatar;
import static com.fajarsiddiq.berpasangan.R.layout.layout_statistic_activity;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticActivity extends ModuleActivity {
    private TextView mTotalGameTextView;
    private TextView mBestScoreTextView;
    private TextView mTotalCoinTextView;
    private TextView mNameTextView;
    private StatisticController mController;
    private CircularImageView mAvatar;
    private TextView mAverageScoreTextView;
    private TextView mAverageAccuracyTextView;
    private TextView mEncyclopediaUnlockedTextView;
    private TextView mEncyclopediaReadTextView;
    private TextView mPlayDurationTextView;
    private TextView mCoinSpentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_statistic_activity);
        TypefaceHelper.typeface(this);
        initUI();
    }

    public void initUI() {
        mTotalCoinTextView = (TextView) findViewById(id_statistic_fragment_total_coin_text_view);
        mBestScoreTextView = (TextView) findViewById(id_statistic_fragment_best_score_text_view);
        mTotalGameTextView = (TextView) findViewById(id_statistic_fragment_total_game_text_view);
        mNameTextView = (TextView) findViewById(id_statistic_fragment_name_text_view);
        mNameTextView.setText(SharedPreferenceHelper.getUsername(this));
        mAvatar = (CircularImageView) findViewById(statistic_avatar);
        mController = new StatisticController(this);

        //statistic part
        mAverageAccuracyTextView = (TextView) findViewById(id_stats_fragment_average_accuracy_text_view);
        mAverageScoreTextView = (TextView) findViewById(id_stats_fragment_average_score_text_view);
        mEncyclopediaUnlockedTextView = (TextView) findViewById(id_stats_fragment_encyclopedia_unlocked_text_view);
        mEncyclopediaReadTextView = (TextView) findViewById(id_stats_fragment_encyclopedia_read_text_view);
        mPlayDurationTextView = (TextView) findViewById(id_stats_fragment_play_duration_text_view);
        mCoinSpentTextView = (TextView) findViewById(id_stats_fragment_coin_spent_text_view);
        typeface(this);
    }

    public TextView getTotalGameTextView() {
        return mTotalGameTextView;
    }

    public TextView getBestScoreTextView() {
        return mBestScoreTextView;
    }

    public TextView getTotalCoinTextView() {
        return mTotalCoinTextView;
    }

    public TextView getAverageScoreTextView() {
        return mAverageScoreTextView;
    }

    public TextView getAverageAccuracyTextView() {
        return mAverageAccuracyTextView;
    }

    public TextView getEncyclopediaUnlockedTextView() {
        return mEncyclopediaUnlockedTextView;
    }

    public TextView getEncyclopediaReadTextView() {
        return mEncyclopediaReadTextView;
    }

    public TextView getPlayDurationTextView() {
        return mPlayDurationTextView;
    }

    public TextView getCoinSpentTextView() {
        return mCoinSpentTextView;
    }

//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        ModuleFragment fragment = ((ModuleViewPagerAdapter) mViewPager.getAdapter()).getItem(position);
//        if(fragment instanceof StatsFragment) {
//
//        } else if(fragment instanceof AchievementFragment) {
//
//        }
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    /**
//     * Implementation straightly copied from https://developer.android.com/training/animation/screen-slide.html#pagetransformer
//     */
//    @Override
//    public void transformPage(View view, float position) {
//        int pageWidth = view.getWidth();
//        int pageHeight = view.getHeight();
//
//        if (position < -1) { // [-Infinity,-1)
//            // This page is way off-screen to the left.
//            view.setAlpha(0);
//
//        } else if (position <= 1) { // [-1,1]
//            // Modify the default slide transition to shrink the page as well
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//            if (position < 0) {
//                view.setTranslationX(horzMargin - vertMargin / 2);
//            } else {
//                view.setTranslationX(-horzMargin + vertMargin / 2);
//            }
//
//            // Scale the page down (between MIN_SCALE and 1)
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);
//
//            // Fade the page relative to its size.
//            view.setAlpha(MIN_ALPHA +
//                    (scaleFactor - MIN_SCALE) /
//                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//        } else { // (1,+Infinity]
//            // This page is way off-screen to the right.
//            view.setAlpha(0);
//        }
//    }

    @Override
    public void onSignInSucceeded() {
        //get avatar
        Player currentPlayer = Games.Players.getCurrentPlayer(getApiClient());
        mNameTextView.setText(currentPlayer.getName());
        ImageLoader.loadImage(this, currentPlayer.getHiResImageUrl(), mAvatar);
    }
}
