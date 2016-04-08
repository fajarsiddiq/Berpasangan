package com.fajarsiddiq.berpasangan.module.board;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.Item;

import static android.view.LayoutInflater.from;
import static com.fajarsiddiq.berpasangan.R.drawable.drawable_tile_background;
import static com.fajarsiddiq.berpasangan.R.string.abc_activity_chooser_view_see_all;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_finish_message;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_finish_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_finish_title;
import static com.fajarsiddiq.berpasangan.R.layout.layout_tile;
import static android.graphics.Color.parseColor;
import static android.view.View.OnClickListener;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timeout_message;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timeout_positive;
import static com.fajarsiddiq.berpasangan.helper.SnackBarHelper.useSnackBar;
import static com.fajarsiddiq.berpasangan.R.color.color_grey;
import static com.fajarsiddiq.berpasangan.R.drawable.drawable_check;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardHandler extends ModuleHandler implements OnClickListener {
    public int mWhatTimer;
    public int mWhatTimeout;
    public int mWhatQuestion;
    public int mWhatFinish;
    public int mWhatRefresh;

    private View first;
    private View second;

    public BoardHandler(final BoardFragment fragment) {
        super(fragment);
        mWhatTimer = mWhat + 1;
        mWhatTimeout = mWhat + 2;
        mWhatQuestion = mWhat + 3;
        mWhatFinish = mWhat + 4;
        mWhatRefresh = mWhat + 5;
        first = null;
        second = null;
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
                builder.setTitle(string_board_fragment_finish_title)
                        .setMessage(string_board_fragment_timeout_message)
                        .setPositiveButton(fragment.getString(string_board_fragment_timeout_positive), new DialogInterface.OnClickListener() {
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
            for(int i = 0; i < items.length; i++) {
                view = from(fragment.getContext()).inflate(layout_tile, null);
                view.setMinimumWidth(screenWidth / ((x > y) ? x : y));
                view.setMinimumHeight(screenWidth / ((x > y) ? x : y));
                //view.setBackgroundColor(parseColor(items[ii].getValue())); //http://stackoverflow.com/questions/2173936/how-to-set-background-color-of-a-view
                view.setId(i);
                view.setOnClickListener(this);
                gridLayout.addView(view);
            }
        } else if(message.what == mWhatRefresh) {
            final GridLayout gridLayout = fragment.getGridLayout();
            BoardView[] views = (BoardView[]) message.obj;
            View view;
            int id, status;
            for(int i = 0; i < views.length; i++) {
                view = gridLayout.findViewById(views[i].getId());
                id = views[i].getId();
                status = views[i].getAnswered();
                setBackground(view, id, status);
            }
        } else if(message.what == mWhatFinish) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
            builder.setMessage(fragment.getString(string_board_fragment_finish_message))
                    .setTitle(fragment.getString(string_board_fragment_finish_title));
            builder.setPositiveButton(fragment.getString(string_board_fragment_finish_positive), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    fragment.getActivity().finish();
                }
            });
            builder.create().show();
        }
    }

    @Override
    public void onClick(View view) {
        BoardFragment fragment = (BoardFragment) mFragment;
        Log.i("test", view.getId() + "");
        if(first == null && second == null) {
            refreshBoard(view.getId(), null);
            first = view;
            first.setOnClickListener(null);
        } else if(second == null) {
            refreshBoard(first.getId(), view.getId());
            second = view;
            first.setOnClickListener(this);
            if(fragment.isZonk(first.getId()) || fragment.isZonk(second.getId())) {
                useSnackBar(fragment.getContext(), fragment.getActivity(), "It's zonk. Minus 5.");
            }

            if(fragment.isSame(first.getId(), second.getId())) {
                useSnackBar(fragment.getContext(), fragment.getActivity(), "Good! Plus 10.");
                refreshBoard(null, null);
//                first.setBackgroundResource(drawable_check);
//                first.setOnClickListener(null);
//                second.setBackgroundResource(drawable_check);
//                second.setOnClickListener(null);
            }
            first = null;
            second = null;
        } else {
            first = null;
            second = null;
            refreshBoard(view.getId(), null);
            first = view;
        }
    }

    private String getColor(final int id) {
        BoardFragment fragment = (BoardFragment) mFragment;
        return fragment.getColor(id);
    }

    private void refreshBoard(final Integer id1, final Integer id2) {
        BoardFragment fragment = (BoardFragment) mFragment;
        Log.i("test", String.valueOf(id1) + " " + String.valueOf(id2));
        fragment.refreshBoard(id1, id2);
    }

    private void setBackground(final View view, final int id, final int status) {
        if(status == 0) {
            if(SDK_INT < JELLY_BEAN) //http://stackoverflow.com/questions/12523005/how-set-background-drawable-programmatically-in-android
                view.setBackgroundDrawable(mFragment.getResources().getDrawable(drawable_tile_background));
            else
                view.setBackground(mFragment.getResources().getDrawable(drawable_tile_background));
        } else if(status == 1) {
            view.setBackgroundResource(drawable_check);
            view.setOnClickListener(null);
        } else if(status == 2) {
            view.setBackgroundColor(parseColor(getColor(id)));
        }
    }
}
