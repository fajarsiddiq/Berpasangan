package com.fajarsiddiq.berpasangan.module.highscore;

import android.graphics.Point;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.helper.GameMode;
import com.fajarsiddiq.berpasangan.module.ModuleHandler;
import com.fajarsiddiq.berpasangan.sqlite.Highscore;

import java.util.List;

import static android.view.View.VISIBLE;
import static android.view.View.GONE;
import static android.view.LayoutInflater.from;
import static com.fajarsiddiq.berpasangan.R.color.color_blue_grey_1;
import static com.fajarsiddiq.berpasangan.R.color.color_blue_grey_2;
import static com.fajarsiddiq.berpasangan.R.color.color_blue_grey_3;
import static com.fajarsiddiq.berpasangan.R.color.color_blue_grey_4;
import static com.fajarsiddiq.berpasangan.R.color.color_blue_grey_5;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_card_view;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_score;
import static java.lang.String.valueOf;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;
import static com.fajarsiddiq.berpasangan.helper.TimeHelper.getDate;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_mode;
import static com.fajarsiddiq.berpasangan.R.id.id_highscore_timestamp;
import static com.fajarsiddiq.berpasangan.R.layout.layout_highscore;
import static com.fajarsiddiq.berpasangan.helper.GameMode.fromValue;
import static java.lang.Integer.parseInt;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class HighscoreHandler extends ModuleHandler {
    public int mWhatData;

    HighscoreHandler(final HighscoreFragment fragment) {
        super(fragment);
        mWhatData = mWhat + 1;
    }

    @Override
    public void handleMessage(Message message) {
        if(message.what == mWhatData) {
            HighscoreFragment fragment = (HighscoreFragment) mFragment;
            List<Highscore> highscoreList = (List<Highscore>) message.obj;
            if(highscoreList.size() == 0) {
                fragment.getNoDataTextView().setVisibility(VISIBLE);
            } else {
                View view;
                LinearLayout linearLayout = fragment.getLinearLayout();
                fragment.getNoDataTextView().setVisibility(GONE);
                Point size = new Point();
                fragment.getActivity().getWindowManager().getDefaultDisplay().getSize(size);
                int screenHeight = size.y;
                Highscore highscore;
                GameMode gameMode;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight /5);
                layoutParams.setMargins(20, 10, 20, 10);
                for (int i = 0; i < highscoreList.size(); i++) {
                    highscore = highscoreList.get(i);
                    view = from(fragment.getContext()).inflate(layout_highscore, null);
                    gameMode = fromValue(parseInt(highscore.getMode()));
                    ((TextView) view.findViewById(id_highscore_score)).setText(valueOf(highscore.getScore()));
                    ((TextView) view.findViewById(id_highscore_mode)).setText(gameMode.getName(fragment.getContext()));
                    ((TextView) view.findViewById(id_highscore_timestamp)).setText(getDate(highscore.getTimeStamp()));
                    typeface(view);
                    linearLayout.addView(view, layoutParams);
                }
            }
        }
    }
}
