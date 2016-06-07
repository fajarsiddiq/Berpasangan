package com.fajarsiddiq.berpasangan.helper;

import com.fajarsiddiq.berpasangan.sqlite.User;

/**
 * Created by Muhammad Fajar on 05/06/2016.
 */
public class SQLiteHelper {
    public static void prepareDb(final String name) {
        User user = User.findById(User.class, 1);
        if(user == null) {
            user = new User(name);
            user.setCoin(0);
            user.setTotalDuration(0);
            user.save();
        }
    }


}
