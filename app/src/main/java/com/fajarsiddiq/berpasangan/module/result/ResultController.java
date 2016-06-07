package com.fajarsiddiq.berpasangan.module.result;

import android.os.AsyncTask;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.sqlite.User;

/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultController extends ModuleController {

    ResultController(final ResultFragment fragment) {
        super(fragment.getContext());
    }

    public void saveData(User user) {
        new SaveData().execute(user);
    }

    private class SaveData extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... params) {
            User temp = params[0];
            User user = User.findById(User.class, 1);
            user.setBestScore(user.getBestScore() > temp.getBestScore() ? user.getBestScore() : temp.getBestScore());
            user.setTotalGame(user.getTotalGame() + 1);
            user.setCoin(user.getCoin() + temp.getCoin());
            user.setTotalScore(user.getTotalScore() + temp.getTotalScore());
            user.setTotalAccuracy(user.getTotalAccuracy() + temp.getTotalAccuracy());
            user.save();
            return null;
        }
    }
}
