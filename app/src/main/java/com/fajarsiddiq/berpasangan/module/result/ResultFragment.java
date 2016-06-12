package com.fajarsiddiq.berpasangan.module.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.fajarsiddiq.berpasangan.sqlite.User;

import static android.view.View.OnClickListener;
import static com.fajarsiddiq.berpasangan.R.string.string_result_fragment_accuracy_result;
import static com.fajarsiddiq.berpasangan.R.string.string_result_fragment_time_left_result;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_accuracy_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_coin_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_correct_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_time_left_result_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_total_score_result_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_result_fragment;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_high_score_button;
import static com.fajarsiddiq.berpasangan.R.id.id_result_fragment_main_menu_button;
import static com.fajarsiddiq.berpasangan.R.string.string_result_fragment_correct_result;

/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultFragment extends ModuleFragment implements OnClickListener {
    private Button mHighScoreButton;
    private Button mMainMenuButton;
    private ResultController mController;
    private TextView mCorrectTextView;
    private TextView mAccuracyTextView;
    private TextView mTimeLeftTextView;
    private TextView mScoreTextView;
    private TextView mCoinTextView;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_result_fragment, null);
        mHighScoreButton = (Button) view.findViewById(id_result_fragment_high_score_button);
        mHighScoreButton.setOnClickListener(this);
        mMainMenuButton = (Button) view.findViewById(id_result_fragment_main_menu_button);
        mMainMenuButton.setOnClickListener(this);
        mAccuracyTextView = (TextView) view.findViewById(id_result_fragment_accuracy_result_text_view);
        mCoinTextView = (TextView) view.findViewById(id_result_fragment_coin_result_text_view); 
        mCorrectTextView = (TextView) view.findViewById(id_result_fragment_correct_result_text_view);
        mScoreTextView = (TextView) view.findViewById(id_result_fragment_total_score_result_text_view);
        mTimeLeftTextView = (TextView) view.findViewById(id_result_fragment_time_left_result_text_view);
        mController = new ResultController(this);
        user = new User();
        refreshData();
        return view;
    }

    public Button getHighScoreButton() {
        return mHighScoreButton;
    }

    public Button getMainMenuButton() {
        return mMainMenuButton;
    }

    public ResultController getController() {
        return mController;
    }

    public TextView getCorrectTextView() {
        return mCorrectTextView;
    }

    public TextView getAccuracyTextView() {
        return mAccuracyTextView;
    }

    public TextView getTimeLeftTextView() {
        return mTimeLeftTextView;
    }

    public TextView getScoreTextView() {
        return mScoreTextView;
    }

    public TextView getCoinTextView() {
        return mCoinTextView;
    }

    @Override
    public void onClick(final View view) {
        if(view.getId() == id_result_fragment_high_score_button) {
            //Not implemented yet
        } else if(view.getId() == id_result_fragment_main_menu_button) {
           getActivity().finish();
        }
    }

    private void refreshData() {
        int [] data = getActivity().getIntent().getIntArrayExtra(ResultActivity.data);
        final int attempt = data[0] / 2, answer = data[1], question = data[2], time = data[3], score = data[4], zonk = data[5];
        final double accuracy = ((double) answer) / attempt;
        user.setTotalAccuracy(accuracy);
        mCorrectTextView.setText(format(getString(string_result_fragment_correct_result), answer, question));
        mAccuracyTextView.setText(format(getString(string_result_fragment_accuracy_result), accuracy * 100));
        mTimeLeftTextView.setText(format(getString(string_result_fragment_time_left_result), time));
        final int finalScore = score + (int) (accuracy * 100) + (time * 10);
        mScoreTextView.setText(valueOf(finalScore));
        user.setBestScore(finalScore);
        mCoinTextView.setText(new StringBuilder(" + ").append(finalScore > 0 ? valueOf(finalScore / 10) : valueOf(0)));
        if(finalScore > 0) {
            user.setCoin(finalScore / 10);
            user.setTotalScore(finalScore);
        }
        user.setTotalZonk(zonk);
        saveData();
    }

    private void saveData() {
        mController.saveData(user);
    }
}
