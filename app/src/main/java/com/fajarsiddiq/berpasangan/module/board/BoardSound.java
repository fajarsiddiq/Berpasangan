package com.fajarsiddiq.berpasangan.module.board;

import android.content.Context;

import com.fajarsiddiq.berpasangan.helper.SoundHelper;

import static com.fajarsiddiq.berpasangan.R.raw.sound_game_lose;
import static com.fajarsiddiq.berpasangan.R.raw.sound_game_win;
import static com.fajarsiddiq.berpasangan.R.raw.sound_open_tile;
import static com.fajarsiddiq.berpasangan.R.raw.sound_pause_menu;
import static com.fajarsiddiq.berpasangan.R.raw.sound_tile_matched;

/**
 * Created by fajar on 8/5/16.
 */

public class BoardSound extends SoundHelper {
    final int TILE_SOUND = 1;
    final int MATCH_SOUND = 2;
    final int PAUSE_SOUND = 3;
    final int LOSE_SOUND = 4;
    final int WIN_SOUND = 5;


    public BoardSound(final Context context) {
        super(context, 6); //add one for background music
        init();
    }

    public void playSound(final int index) {
        super.playSong(index);
    }

    private void init() {
        addSound(TILE_SOUND, sound_open_tile);
        addSound(PAUSE_SOUND, sound_pause_menu);
        addSound(MATCH_SOUND, sound_tile_matched);
        addSound(LOSE_SOUND, sound_game_lose);
        addSound(WIN_SOUND, sound_game_win);
    }
}
