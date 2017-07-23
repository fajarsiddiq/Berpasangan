package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.fajarsiddiq.berpasangan.R;

/**
 * Created by Muhammad Fajar on 22/07/2017.
 */

public class SharedPreferenceHelper {
    private final static String PREFERENCE_FILE_NAME = "com.fajarsiddiq.berpasangan.PREFERENCE";

    private static void setData(Context context, int keyId, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(context.getString(keyId), value);
        editor.commit();
    }

    private static void setData(Context context, int keyId, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(context.getString(keyId), value);
        editor.commit();
    }

    private static String getData(Context context, int keyId, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(context.getString(keyId), defaultValue);
    }

    private static int getData(Context context, int keyId, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(context.getString(keyId), defaultValue);
    }

    public static void setAchievement(Context context, int achievementName) {
        setData(context, achievementName, 1);
    }

    public static boolean isAchievementUnlocked(Context context, int achievementName) {
        return getData(context, achievementName, 0) == 1;
    }

    public static void setUsername(Context context, String username) {
        setData(context, R.string.pref_username, username);
    }

    public static String getUsername(Context context) {
        return getData(context, R.string.pref_username, "Player");
    }

    public static void setNotFirstLogin(Context context, boolean firstLogin) {
        setData(context, R.string.pref_first_login, firstLogin ? 1 : 0);
    }

    public static boolean isNotFirstLogin(Context context) {
        return getData(context, R.string.pref_first_login, 0) == 1;
    }

    public static void setDuration(Context context, long newDuration) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(context.getString(R.string.pref_duration), newDuration);
        editor.commit();
    }

    public static long getDuration(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getLong(context.getString(R.string.pref_duration), 0);
    }

    public static void setMusicSwitch(Context context, boolean isOn) {
        setData(context, R.string.pref_sound_switch, isOn ? 1 : 0);
    }

    public static boolean isMusicSwitchOn(Context context) {
        return getData(context, R.string.pref_sound_switch, 1) == 1;
    }
}
