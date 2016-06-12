package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.fajarsiddiq.berpasangan.module.ModuleViewPagerAdapter;
import com.fajarsiddiq.berpasangan.module.statistic.achievement.AchievementFragment;
import com.fajarsiddiq.berpasangan.module.statistic.stats.StatsFragment;

import static android.support.v4.view.ViewPager.OnPageChangeListener;
import static android.support.v4.view.ViewPager.PageTransformer;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_best_score_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_name_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_total_coin_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_total_game_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_statistic_fragment_view_pager;
import static com.fajarsiddiq.berpasangan.R.layout.layout_statistic_fragment;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticFragment extends ModuleFragment implements OnPageChangeListener, PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    private TextView mTotalGameTextView;
    private TextView mBestScoreTextView;
    private TextView mTotalCoinTextView;
    private TextView mNameTextView;
    private StatisticController mController;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_statistic_fragment, null);
        mTotalCoinTextView = (TextView) view.findViewById(id_statistic_fragment_total_coin_text_view);
        mBestScoreTextView = (TextView) view.findViewById(id_statistic_fragment_best_score_text_view);
        mTotalGameTextView = (TextView) view.findViewById(id_statistic_fragment_total_game_text_view);
        mNameTextView = (TextView) view.findViewById(id_statistic_fragment_name_text_view);
        mController = new StatisticController(this);
        mViewPager = (ViewPager) view.findViewById(id_statistic_fragment_view_pager);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(new ModuleViewPagerAdapter(new ModuleFragment[] { new StatsFragment(), new AchievementFragment()}, getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(mViewPager.getAdapter().getCount());
        mViewPager.setPageTransformer(true, this);
        return view;
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

    public TextView getNameTextView() {
        return mNameTextView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ModuleFragment fragment = ((ModuleViewPagerAdapter) mViewPager.getAdapter()).getItem(position);
        if(fragment instanceof StatsFragment) {

        } else if(fragment instanceof AchievementFragment) {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Implementation straightly copied from https://developer.android.com/training/animation/screen-slide.html#pagetransformer
     */
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
