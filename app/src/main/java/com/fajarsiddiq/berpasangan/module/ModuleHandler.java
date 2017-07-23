package com.fajarsiddiq.berpasangan.module;

import android.os.Handler;
import android.os.Message;

import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_LONG;
import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT;
import static com.nispok.snackbar.Snackbar.with;
/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class ModuleHandler extends Handler {
    public final int mWhatFinish;
    public final int mWhatKeyboardHide;
    public final int mWhatSnackBar;
    protected final ModuleActivity mActivity;
    public int mWhat;

    protected ModuleHandler(final ModuleActivity activity) {
        mWhatFinish = 0;
        mWhatKeyboardHide = 1;
        mWhatSnackBar = 2;
        mWhat = mWhatSnackBar;
        mActivity = activity;
    }

    @Override
    public void handleMessage(final Message message) {
        if(mActivity == null)
            return;
        if(message.what == mWhatFinish)
            mActivity.finish();
        else if(message.what == mWhatSnackBar)
            with(mActivity).text((CharSequence) message.obj).duration(message.arg1 == 0 ? LENGTH_SHORT : LENGTH_LONG).show(mActivity);
    }
}
