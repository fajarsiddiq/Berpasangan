package com.fajarsiddiq.berpasangan.module.result;

import android.os.Message;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;

/**
 * Created by Muhammad Fajar on 07/08/2016.
 */
public class ResultHandler extends ModuleHandler {
    final int mWhatHighScore = 2;

    ResultHandler(){
        super(null);
    }

//    ResultHandler(final ResultFragment fragment) {
//        super(fragment);
//        mWhatHighScore = mWhat + 1;
//    }

    @Override
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if(message.what == mWhatHighScore) {
//            ((ResultFragment) mFragment).showAvatarDialog();
        }
    }
}
