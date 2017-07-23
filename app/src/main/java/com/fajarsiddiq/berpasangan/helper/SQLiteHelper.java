package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;
import android.os.AsyncTask;

import com.fajarsiddiq.berpasangan.sqlite.StoreContent;
import com.fajarsiddiq.berpasangan.sqlite.User;

import static com.fajarsiddiq.berpasangan.R.array.array_store_content_monument;

/**
 * Created by Muhammad Fajar on 05/06/2016.
 */
public class SQLiteHelper {
    public static void prepareDb(final Context context) {
        User user = User.findById(User.class, 1);
        if(user == null) {
            user = new User();
            user.setCoin(0);
            user.setTotalDuration(0);
            user.save();
        }
        prepareItem(context);
    }

    private static void prepareItem(final Context context) {
        long contentNumber = StoreContent.count(StoreContent.class);
        if(contentNumber == 0) {
            //no data in table
            new InitData().execute(context);
        }
    }

    private static class InitData extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            final Context context = contexts[0];
            String[] content = context.getResources().getStringArray(array_store_content_monument);
            String[] split;
            for(int i = 0; i < content.length; i++) {
                split = content[i].split("_");
                new StoreContent(split[0], split[1], 0, 1).save();
            }
            return null;
        }
    }


}
