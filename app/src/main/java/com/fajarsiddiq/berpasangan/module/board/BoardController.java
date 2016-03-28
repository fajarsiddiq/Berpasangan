package com.fajarsiddiq.berpasangan.module.board;

import android.os.AsyncTask;
import android.os.CountDownTimer;

import com.fajarsiddiq.berpasangan.module.ModuleController;

import static android.os.Message.obtain;
import static java.lang.String.valueOf;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardController extends ModuleController {
    private BoardHandler mHandler;
    private CountDownTimer mTimer;

    public BoardController(BoardFragment fragment) {
        super(fragment.getContext());
        mHandler = new BoardHandler(fragment);
    }

    public void initTimer(final int duration) {
        new Timer().execute(duration);
    }

    private class Timer extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(final Integer... params) {
            boolean isFinished = false;

            new Thread(new Runnable() {
                int time = params[0];
                @Override
                public void run() {
                    while (!currentThread().isInterrupted()) {
                        publishProgress(time);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        --time;
                        if(time < 0)
                            currentThread().interrupt();
                    }
                }
            }).start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatTimer, valueOf(values[0])));
            if(values[0] == 0)
                mHandler.sendEmptyMessage(mHandler.mWhatTimeout);
        }
    }

}
