package com.fajarsiddiq.berpasangan.module.board;

import android.os.AsyncTask;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.sqlite.Item;

import static android.os.Message.obtain;
import static java.lang.String.valueOf;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardController extends ModuleController {
    private BoardHandler mHandler;
    private Board board;
    private Thread mThread;
    private int x;
    private int y;

    public BoardController(BoardFragment fragment) {
        super(fragment.getContext());
        mHandler = new BoardHandler(fragment);
    }

    public void initTimer(final int duration) {
        new Timer().execute(duration);
    }

    public void initQuestion(final int x, final int y) {
        this.x = x;
        this.y = y;
        new Generator().execute(x, y);
    }

    public void stopTimer() {
        if(mThread.isAlive())
            mThread.interrupt();
    }

    private class Timer extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(final Integer... params) {
            mThread = new Thread(new Runnable() {
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
            });
            mThread.start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatTimer, valueOf(values[0])));
            if(values[0] == 0)
                mHandler.sendEmptyMessage(mHandler.mWhatTimeout);
        }
    }

    private class Generator extends AsyncTask<Integer, Integer, Item[]> {

        @Override
        protected Item[] doInBackground(Integer... params) {
            Item[] items = new Item[params[0]*params[1]];
            board = new Board(params[0], params[1]);
            return items;
        }

        @Override
        protected void onPostExecute(Item[] items) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatQuestion, x, y, items));
        }
    }

}
