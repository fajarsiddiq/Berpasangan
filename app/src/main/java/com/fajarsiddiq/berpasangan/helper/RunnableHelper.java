package com.fajarsiddiq.berpasangan.helper;

/**
 * Created by Muhammad Fajar on 12/05/2016.
 * Mostly code is retrieved from http://stackoverflow.com/a/6776463
 */
public class RunnableHelper implements Runnable {
    private Object mPauseLock;
    private boolean mPaused;
    private boolean mFinished;

    public RunnableHelper() {
        mPauseLock = new Object();
        mPaused = false;
        mFinished = false;
    }

    public void run() {
        while (!mFinished) {
            // Do stuff.

            synchronized (mPauseLock) {
                while (mPaused) {
                    try {
                        mPauseLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    /**
     * Call this on pause.
     */
    public void onPause() {
        synchronized (mPauseLock) {
            mPaused = true;
        }
    }

    /**
     * Call this on resume.
     */
    public void onResume() {
        synchronized (mPauseLock) {
            mPaused = false;
            mPauseLock.notifyAll();
        }
    }

}
