package com.fajarsiddiq.berpasangan.module.board;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fajarsiddiq.berpasangan.helper.Drawable;
import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.module.result.ResultActivity;

import static android.view.LayoutInflater.from;
import static android.view.View.OnClickListener;
import static com.fajarsiddiq.berpasangan.R.drawable.drawable_check;
import static com.fajarsiddiq.berpasangan.R.drawable.drawable_tile;
import static com.fajarsiddiq.berpasangan.R.id.id_tile_image_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_tile;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_finish_message;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_finish_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_finish_title;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_remaining;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timeout_message;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timeout_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_timeout_title;
import static com.fajarsiddiq.berpasangan.helper.SnackBarHelper.useSnackBar;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardHandler extends ModuleHandler implements OnClickListener {
    public int mWhatTimer;
    public int mWhatTimeout;
    public int mWhatQuestion;
    public int mWhatFinish;
    public int mWhatRefresh;
    public int mWhatScore;

    private View first;
    private View second;

    public BoardHandler(final BoardActivity activity) {
        super(activity);
        mWhatTimer = mWhat + 1;
        mWhatTimeout = mWhat + 2;
        mWhatQuestion = mWhat + 3;
        mWhatFinish = mWhat + 4;
        mWhatRefresh = mWhat + 5;
        mWhatScore = mWhat + 6;
        first = null;
        second = null;
    }

    @Override
    public void handleMessage(Message message) {
        final BoardActivity activity = (BoardActivity) mActivity;
        if(message.what == mWhatTimer) {
            if(activity != null)
                activity.getTimerTextView().setText(new StringBuilder(activity.getString(string_board_fragment_time_remaining)).append(" ").append((String) message.obj));
        } else if(message.what == mWhatTimeout) {
            if(activity != null && !activity.isFinishing()) {
                activity.getBoardSound().playSound(activity.getBoardSound().LOSE_SOUND);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(string_board_fragment_timeout_title)
                        .setMessage(string_board_fragment_timeout_message)
                        .setPositiveButton(activity.getString(string_board_fragment_timeout_positive), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.startActivity(new Intent(activity, ResultActivity.class).putExtra(ResultActivity.data, new int[]{BoardActivity.attemp, BoardActivity.answered, BoardActivity.totalQuestion, parseInt(activity.getTimerTextView().getText().toString().split(" : ")[1]), parseInt(activity.getScoreTextView().getText().toString()), BoardActivity.zonk, BoardActivity.gameMode}));
                                activity.finish();
                            }
                        }).setCancelable(false).create().show();
            }
        } else if(message.what == mWhatQuestion) {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(size);
            int screenWidth = size.x;
            int screenHeight = size.y;

            final Item[] items = (Item[]) message.obj;
            final int x = message.arg1;
            final int y = message.arg2;
            final GridLayout gridLayout = activity.getGridLayout();

            View view;
            int temp = (x > y) ? x : y;
            temp = temp > 5 ? 5 : temp;
            int dim = screenWidth / temp;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dim, dim);
            for(int i = 0; i < items.length; i++) {
                view = from(activity).inflate(layout_tile, null);
                view.setLayoutParams(layoutParams);
                view.setId(i);
                view.setOnClickListener(this);
                gridLayout.addView(view);
            }
        } else if(message.what == mWhatRefresh) {
            final GridLayout gridLayout = activity.getGridLayout();
            BoardView[] views = (BoardView[]) message.obj;
            View view;
            int id, status;
            boolean image;
            for(int i = 0; i < views.length; i++) {
                view = gridLayout.findViewById(views[i].getId());
                id = views[i].getId();
                status = views[i].getAnswered();
                image = views[i].isImage();
                setBackground(view, id, status, image);
            }
        } else if(message.what == mWhatFinish) {
            activity.getBoardSound().playSound(activity.getBoardSound().WIN_SOUND);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            activity.pauseTimer();
            builder.setMessage(activity.getString(string_board_fragment_finish_message))
                    .setTitle(activity.getString(string_board_fragment_finish_title));
            builder.setPositiveButton(activity.getString(string_board_fragment_finish_positive), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    activity.startActivity(new Intent(activity, ResultActivity.class).putExtra(ResultActivity.data, new int[]{BoardActivity.attemp, BoardActivity.answered, BoardActivity.totalQuestion, parseInt(activity.getTimerTextView().getText().toString().split(" : ")[1]), parseInt(activity.getScoreTextView().getText().toString()), BoardActivity.zonk, BoardActivity.gameMode}));
                    activity.finish();
                }
            }).setCancelable(false).create().show();
        } else if(message.what == mWhatScore) {
            activity.getScoreTextView().setText(valueOf(message.obj));
        }
    }

    @Override
    public void onClick(View view) {
        BoardActivity.attemp += 1;
        BoardActivity activity = (BoardActivity) mActivity;
        activity.getBoardSound().playSound(activity.getBoardSound().TILE_SOUND);
        if(first == null && second == null) {
            refreshBoard(view.getId(), null);
            first = view;
            first.setOnClickListener(null);
        } else if(second == null) {
            refreshBoard(first.getId(), view.getId());
            second = view;
            second.setOnClickListener(null);

            if (activity.isZonk(first.getId()) || activity.isZonk(second.getId())) {
                useSnackBar(activity, activity, "It's zonk. Minus 5.");
                activity.updateScore(-5);
                BoardActivity.zonk += 1;
            }

            if (activity.isSame(first.getId(), second.getId())) {
                activity.getBoardSound().playSound(activity.getBoardSound().MATCH_SOUND);
                activity.updateScore(10);
                useSnackBar(activity, activity, "Good! Plus 10.");
                BoardActivity.answered += 1;
            }
            first.setOnClickListener(this);
            second.setOnClickListener(this);
            first = null;
            second = null;
        }
    }

    private String getImageName(final int id) {
        BoardActivity activity = (BoardActivity) mActivity;
        Item temp = activity.getCell(id);
        return temp instanceof Question ? temp.getName() : temp.getValue();
    }

    private void refreshBoard(final Integer id1, final Integer id2) {
        BoardActivity activity = (BoardActivity) mActivity;
        activity.refreshBoard(id1, id2);
    }

    private void setBackground(final View view, final int id, final int status, final boolean image) {
        if(status == 0)
            ((ImageView) view.findViewById(id_tile_image_view)).setImageResource(drawable_tile);
        else if(status == 1) {
            ((ImageView) view.findViewById(id_tile_image_view)).setImageResource(drawable_check);
            view.setOnClickListener(null);
        } else if(status == 2) {
            if(image) {
                int drawableId = Drawable.getDrawable(mActivity, getImageName(id));
                ((ImageView) view.findViewById(id_tile_image_view)).setImageResource(drawableId);
            }
        }
    }
}
