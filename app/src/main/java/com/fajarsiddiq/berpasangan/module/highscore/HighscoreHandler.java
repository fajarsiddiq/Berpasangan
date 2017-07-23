package com.fajarsiddiq.berpasangan.module.highscore;

import android.graphics.Point;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.helper.GameMode;
import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.Highscore;

import java.util.List;

import static android.view.LayoutInflater.from;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_mode;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_score;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_timestamp;
import static com.fajarsiddiq.berpasangan.R.layout.layout_highscore;
import static com.fajarsiddiq.berpasangan.helper.GameMode.fromValue;
import static com.fajarsiddiq.berpasangan.helper.TimeHelper.getDate;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class HighscoreHandler extends ModuleHandler {
    public int mWhatData;

    HighscoreHandler(final HighscoreActivity activity) {
        super(activity);
        mWhatData = mWhat + 1;
    }

    @Override
    public void handleMessage(Message message) {
        if(message.what == mWhatData) {
            HighscoreActivity activity = (HighscoreActivity) mActivity;
            List<Highscore> highscoreList = (List<Highscore>) message.obj;
            if(highscoreList.size() == 0) {
                activity.getNoDataTextView().setVisibility(VISIBLE);
            } else {
                View view;
                LinearLayout linearLayout = activity.getLinearLayout();
                activity.getNoDataTextView().setVisibility(GONE);
                Point size = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(size);
                int screenHeight = size.y;
                Highscore highscore;
                GameMode gameMode;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight /5);
                layoutParams.setMargins(20, 10, 20, 10);
                for (int i = 0; i < highscoreList.size(); i++) {
                    highscore = highscoreList.get(i);
                    view = from(activity).inflate(layout_highscore, null);
                    gameMode = fromValue(parseInt(highscore.getMode()));
                    ((TextView) view.findViewById(id_highscore_score)).setText(valueOf(highscore.getScore()));
                    ((TextView) view.findViewById(id_highscore_mode)).setText(gameMode.getName(activity));
                    ((TextView) view.findViewById(id_highscore_timestamp)).setText(getDate(highscore.getTimeStamp()));
                    typeface(view);
                    linearLayout.addView(view, layoutParams);
                }
            }
        }
    }
}
