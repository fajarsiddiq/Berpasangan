package com.fajarsiddiq.berpasangan.module;

import android.os.Handler;
import android.os.Message;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.nispok.snackbar.Snackbar.with;
import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT;
import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_LONG;
/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class ModuleHandler extends Handler {
    public final int mWhatFinish;
    public final int mWhatKeyboardHide;
    public final int mWhatSnackBar;
    protected final ModuleFragment mFragment;
    public int mWhat;

    protected ModuleHandler(final ModuleFragment fragment) {
        mWhatFinish = 0;
        mWhatKeyboardHide = 1;
        mWhatSnackBar = 2;
        mWhat = mWhatSnackBar;
        mFragment = fragment;
    }

    @Override
    public void handleMessage(final Message message) {
        if(mFragment == null || mFragment.getContext() == null)
            return;
        if(message.what == mWhatFinish)
            mFragment.getActivity().finish();
        else if(message.what == mWhatSnackBar)
            with(mFragment.getContext()).text((CharSequence) message.obj).duration(message.arg1 == 0 ? LENGTH_SHORT : LENGTH_LONG).show(mFragment.getActivity());
    }
}
