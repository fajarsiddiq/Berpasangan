package com.fajarsiddiq.berpasangan.module.board;

import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;

import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timer_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timer_finish_message;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timer_finish_title;


/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardHandler extends ModuleHandler {
    public int mWhatTimer;
    public int mWhatTimeout;

    public BoardHandler(final BoardFragment fragment) {
        super(fragment);
        mWhatTimer = mWhat + 1;
        mWhatTimeout = mWhat + 2;
    }

    @Override
    public void handleMessage(Message message) {
        BoardFragment fragment = (BoardFragment) mFragment;
        if(message.what == mWhatTimer) {
            fragment.getTimerTextView().setText((String) message.obj);
        } else if(message.what == mWhatTimeout) {
            final BoardActivity activity = (BoardActivity) fragment.getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(string_board_fragment_timer_finish_title)
                    .setMessage(string_board_fragment_timer_finish_message)
                    .setPositiveButton(fragment.getString(string_board_fragment_timer_positive), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(activity, "Sabar ya, belum diimplementasi", Toast.LENGTH_SHORT).show();
                        }
                    }).create().show();


        }
    }
}
