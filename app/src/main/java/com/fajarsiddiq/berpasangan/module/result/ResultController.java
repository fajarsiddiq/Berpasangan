package com.fajarsiddiq.berpasangan.module.result;

import android.os.AsyncTask;
import android.util.Log;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.sqlite.Highscore;
import com.fajarsiddiq.berpasangan.sqlite.User;

import java.util.List;

import static com.fajarsiddiq.berpasangan.helper.HighscoreHelper.process;
/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultController extends ModuleController {
    private ResultHandler mHandler;

    ResultController(final ResultFragment fragment) {
        super(fragment.getContext());
        mHandler = new ResultHandler(fragment);
    }

    public void saveData(User user, com.fajarsiddiq.berpasangan.sqlite.Highscore highscore) {
        new SaveData().execute(new Object[]{user, highscore});
    }

    private class SaveData extends AsyncTask<Object, Void, com.fajarsiddiq.berpasangan.sqlite.Highscore> {

        @Override
        protected com.fajarsiddiq.berpasangan.sqlite.Highscore doInBackground(Object... params) {
            User temp = (User) params[0];
            User user = User.findById(User.class, 1);
            user.setBestScore(user.getBestScore() > temp.getBestScore() ? user.getBestScore() : temp.getBestScore());
            user.setTotalGame(user.getTotalGame() + 1);
            user.setCoin(user.getCoin() + temp.getCoin());
            user.setTotalScore(user.getTotalScore() + temp.getTotalScore());
            user.setTotalAccuracy(user.getTotalAccuracy() + temp.getTotalAccuracy());
            user.setTotalZonk(user.getTotalZonk() + temp.getTotalZonk());
            user.save();
            return ((com.fajarsiddiq.berpasangan.sqlite.Highscore) params[1]);
        }

        @Override
        protected void onPostExecute(com.fajarsiddiq.berpasangan.sqlite.Highscore highscore) {
            processHighscore(highscore);
        }
    }

    public void processHighscore(com.fajarsiddiq.berpasangan.sqlite.Highscore highscore) {
        new Highscore().execute(highscore);
    }

    private class Highscore extends AsyncTask<com.fajarsiddiq.berpasangan.sqlite.Highscore, Void, Boolean> {

        @Override
        protected Boolean doInBackground(com.fajarsiddiq.berpasangan.sqlite.Highscore... params) {
            com.fajarsiddiq.berpasangan.sqlite.Highscore highscore = params[0];
            List<com.fajarsiddiq.berpasangan.sqlite.Highscore> highscoreList = com.fajarsiddiq.berpasangan.sqlite.Highscore.listAll(com.fajarsiddiq.berpasangan.sqlite.Highscore.class);
            final boolean isHighScore = process(highscore, highscoreList);
            return isHighScore;
        }

        @Override
        protected void onPostExecute(Boolean isHighScore) {
            if(isHighScore)
                mHandler.sendEmptyMessage(mHandler.mWhatHighScore);
        }
    }
}
