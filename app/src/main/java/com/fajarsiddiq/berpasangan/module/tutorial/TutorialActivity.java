package com.fajarsiddiq.berpasangan.module.tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.fajarsiddiq.berpasangan.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by fajar on 8/8/16.
 * Based on this library https://github.com/apl-devs/AppIntro
 */

public class TutorialActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int background = ContextCompat.getColor(this, R.color.tutorial_background);
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_title_1), getString(R.string.tutorial_desc_1), R.drawable.slide1, background));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_title_2), getString(R.string.tutorial_desc_2), R.drawable.slide2, background));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_title_3), getString(R.string.tutorial_desc_3), R.drawable.slide3, background));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_title_4), getString(R.string.tutorial_desc_4), R.drawable.slide4, background));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_title_5), getString(R.string.tutorial_desc_5), R.drawable.slide5, background));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_title_6), getString(R.string.tutorial_desc_6), R.drawable.slide6, background));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(ContextCompat.getColor(this, R.color.color_blue_grey_4));
        setSeparatorColor(ContextCompat.getColor(this, R.color.color_white));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
