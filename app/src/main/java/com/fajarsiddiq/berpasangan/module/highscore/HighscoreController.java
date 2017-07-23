package com.fajarsiddiq.berpasangan.module.highscore;

import android.os.AsyncTask;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.sqlite.Highscore;

import java.util.List;

import static android.os.Message.obtain;
import static com.fajarsiddiq.berpasangan.helper.HighscoreHelper.sortHighscore;
import static com.fajarsiddiq.berpasangan.sqlite.Highscore.listAll;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class HighscoreController extends ModuleController {
    private HighscoreHandler mHandler;

    HighscoreController(final HighscoreActivity activity) {
        super(activity);
        mHandler = new HighscoreHandler(activity);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Void, List<Highscore>> {

        @Override
        protected List<Highscore> doInBackground(Void... params) {
            List<Highscore> highscoreList = listAll(Highscore.class);
            sortHighscore(highscoreList);
            return highscoreList;
        }

        @Override
        protected void onPostExecute(List<Highscore> highscores) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatData, highscores));
        }
    }
}
