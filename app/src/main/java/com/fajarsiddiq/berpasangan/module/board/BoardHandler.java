package com.fajarsiddiq.berpasangan.module.board;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.Item;
import com.nispok.snackbar.Snackbar;

import static android.view.LayoutInflater.from;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timer_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timer_finish_message;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timer_finish_title;
import static com.fajarsiddiq.berpasangan.R.layout.layout_tile;
import static android.graphics.Color.parseColor;
import static android.view.View.OnClickListener;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardHandler extends ModuleHandler implements OnClickListener {
    public int mWhatTimer;
    public int mWhatTimeout;
    public int mWhatQuestion;

    private View first;

    public BoardHandler(final BoardFragment fragment) {
        super(fragment);
        mWhatTimer = mWhat + 1;
        mWhatTimeout = mWhat + 2;
        mWhatQuestion = mWhat + 3;
        first = null;
    }

    @Override
    public void handleMessage(Message message) {
        final BoardFragment fragment = (BoardFragment) mFragment;
        if(message.what == mWhatTimer) {
            fragment.getTimerTextView().setText((String) message.obj);
        } else if(message.what == mWhatTimeout) {
            final BoardActivity activity = (BoardActivity) fragment.getActivity();
            if(activity != null) {
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
        } else if(message.what == mWhatQuestion) {
            Point size = new Point();
            fragment.getActivity().getWindowManager().getDefaultDisplay().getSize(size);
            int screenWidth = size.x;
            int screenHeight = size.y;

            final Item[] items = (Item[]) message.obj;
            final int x = message.arg1;
            final int y = message.arg2;
            final GridLayout gridLayout = fragment.getGridLayout();

            View view;
            for(int ii = 0; ii < items.length; ii++) {
                view = from(fragment.getContext()).inflate(layout_tile, null);
                view.setMinimumWidth(screenWidth / ((x > y) ? x : y));
                view.setMinimumHeight(screenWidth / ((x > y) ? x : y));
                view.setBackgroundColor(parseColor(items[ii].getValue())); //http://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view
                view.setId(ii);
                view.setOnClickListener(this);
                gridLayout.addView(view);
            }
        }
    }

    @Override
    public void onClick(View view) {
        BoardFragment fragment = (BoardFragment) mFragment;
        if (first == null)
            first = view;
        else {
            Snackbar.with(mFragment.getContext()).text(fragment.isSame(first.getId(), view.getId()) ? "It's same" : "It's different").duration(Snackbar.SnackbarDuration.LENGTH_SHORT).show(mFragment.getActivity());
            first = null;
        }
    }
}
