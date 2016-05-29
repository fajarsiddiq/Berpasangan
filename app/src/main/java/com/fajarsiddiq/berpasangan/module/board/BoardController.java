package com.fajarsiddiq.berpasangan.module.board;

import android.os.AsyncTask;
import android.util.Log;

import com.fajarsiddiq.berpasangan.module.ModuleController;
import com.fajarsiddiq.berpasangan.sqlite.Item;

import java.util.List;

import static android.os.Message.obtain;
import static com.fajarsiddiq.berpasangan.R.array.array_question;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;
import static java.util.Collections.shuffle;
import static java.util.Arrays.asList;
import static com.fajarsiddiq.berpasangan.helper.ColorPickerHelper.getColor;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardController extends ModuleController {
    private BoardHandler mHandler;
    private Board mBoard;
    private Thread mThread;
    private int x;
    private int y;
    private int mNoOfTrue;
    private int mScore;
    private int mTimeRemaining;
    private boolean isRun;

    public BoardController(BoardFragment fragment) {
        super(fragment.getContext());
        mHandler = new BoardHandler(fragment);
        mNoOfTrue = 0;
        mScore = 0;
    }

    public void initTimer(final int duration) {
        isRun = true;
        new Timer().execute(duration);
    }

    public void initQuestion(final int x, final int y) {
        this.x = x;
        this.y = y;
        new Generator().execute(x, y);
    }

    public void stopTimer() {
        if(mThread.isAlive())
            mThread.currentThread().interrupt();
    }

    public void pauseTimer() {
        isRun = false;
    }

    public void resumeTimer() {
        isRun = true;
        new Timer().execute(mTimeRemaining);
    }

    public void updateScore(final int diff) {
        this.mScore += diff;
        mHandler.sendMessage(obtain(mHandler, mHandler.mWhatScore, mScore));
    }

    private class Timer extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(final Integer... params) {
            mThread = new Thread(new Runnable() {
                int time = params[0];
                @Override
                public void run() {
                    while (isRun) {
                        publishProgress(time);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            Log.i("Test", "Exception");
                        }
                        --time;
                        if(time < 0)
                            isRun = false;
                    }
                }
            });
            mThread.start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatTimer, valueOf(values[0])));
            mTimeRemaining = values[0];
            if(values[0] == 0)
                mHandler.sendEmptyMessage(mHandler.mWhatTimeout);
        }
    }

    private class Generator extends AsyncTask<Integer, Integer, Item[]> {

        @Override
        protected Item[] doInBackground(Integer... params) {
            Item[] items = new Item[params[0]*params[1]];
            int noOfQuestion = items.length / 2;
            final String[] questions = random(mContext.getResources().getStringArray(array_question), noOfQuestion);
            Question question;
            Answer answer;
            for(int i = 0; i < noOfQuestion; i++) {
                question = new Question(valueOf(i), questions[i].split("_")[0], questions[i].split("_")[1]);
//                question.setColor(getColor(mContext));
                answer = new Answer(questions[i].split("_")[1]);
                items[i] = question;
                items[i+noOfQuestion] = answer;
            }
            if(items.length % 2 != 0)
                items[items.length-1] = new QuestionZonk("#000000");

            List<Item> temp = asList(items);
            shuffle(temp);
            items = temp.toArray(new Item[temp.size()]);
            mBoard = new Board(items);
            return items;
        }

        @Override
        protected void onPostExecute(Item[] items) {
            mHandler.sendMessage(obtain(mHandler, mHandler.mWhatQuestion, x, y, items));
            StringBuilder sb = new StringBuilder();
            for(Item item : items) {
                if(item instanceof Question)
                    sb.append(item.getName()).append(" ");
                else if(item instanceof Answer)
                    sb.append(item.getValue()).append(" ");
            }
            Log.i("test", sb.toString());

        }
    }

    public boolean isSame(final int id1, final int id2) {
        Item item1 = mBoard.getCell(id1), item2 = mBoard.getCell(id2);
        if((item1 instanceof Question && item2 instanceof Answer) || (item1 instanceof Answer && item2 instanceof Question)) {
            boolean same = item1.equals(item2);
            if(same)
                matched(id1, id2);
            return same;
        }
        return false;
    }

    private String[] random(final String[] question, final int noOfQuestion) {
        List<String> temp = asList(question);
        shuffle(temp);
        String[] random = new String[noOfQuestion];
        for(int i = 0; i < noOfQuestion; i++) {
            random[i] = temp.get(i);
        }
        return random;
    }

    private void matched(final int id1, final int id2) {
        Item item1 = mBoard.getCell(id1), item2 = mBoard.getCell(id2);
        item1.setAnswered(true);
        item2.setAnswered(true);
        mNoOfTrue++;
        boolean finish = checkFinish();
        if(finish)
            mHandler.sendEmptyMessage(mHandler.mWhatFinish);
    }

    private boolean checkFinish() {
        boolean isFinish = mNoOfTrue == (x*y) / 2;
        Log.i("Test", "Finish is " + String.valueOf(isFinish));
        return isFinish;
    }

    public boolean isZonk(final int id) {
        return mBoard.getCell(id) instanceof QuestionZonk;
    }

    public Item getCell(final int id) {
        return mBoard.getCell(id);
    }

    public String getValue(final int id) {
        return mBoard.getCell(id).getValue();
    }

    public void getView() {
        BoardView[] views = new BoardView[mBoard.getLength()];
        for(int i = 0; i < views.length; i++) {
            Item temp = mBoard.getCell(i);
            int status;
            boolean image = true;
            if(temp instanceof Question) {
                status = ((Question) temp).isAnswered() ? 1 : 0;
                image = ((Question) temp).isImage();
            } else if(temp instanceof Answer) {
                status = ((Answer) temp).isAnswered() ? 1 : 0;
            } else
                status = 0;
            views[i] = new BoardView(i, status, image);
        }
        mHandler.sendMessage(obtain(mHandler, mHandler.mWhatRefresh, views));
    }

    public void getView(final int id) {
        BoardView[] views = new BoardView[mBoard.getLength()];
        for(int i = 0; i < views.length; i++) {
            Item temp = mBoard.getCell(i);
            int status;
            boolean image = true;
            Log.i("test controller", "Index " + i + " " + temp.getValue());
            if(temp instanceof Question) {
                if(i == id)
                    status = 2;
                else
                    status = ((Question) temp).isAnswered() ? 1 : 0;
                image = ((Question) temp).isImage();
                Log.i("test", "status = " + status + " image " + valueOf(image));
            } else if(temp instanceof Answer) {
                if(i == id)
                    status = 2;
                else
                    status = ((Answer) temp).isAnswered() ? 1 : 0;
                Log.i("test cont", "status " + status);
            } else {
                if(i == id)
                    status = 2;
                else
                    status = 0;
            }
            views[i] = new BoardView(i, status, image);
        }
        mHandler.sendMessage(obtain(mHandler, mHandler.mWhatRefresh, views));
    }

    public void getView(final int id1, final int id2) {
        BoardView[] views = new BoardView[mBoard.getLength()];
        for(int i = 0; i < views.length; i++) {
            Item temp = mBoard.getCell(i);
            int status;
            boolean image = true;
            if(temp instanceof Question) {
                if(i == id1 || i == id2)
                    status = 2;
                else
                    status = ((Question) temp).isAnswered() ? 1 : 0;
                image = ((Question) temp).isImage();
            } else if(temp instanceof Answer) {
                if(i == id1 || i == id2)
                    status = 2;
                else
                    status = ((Answer) temp).isAnswered() ? 1 : 0;
            } else {
                if(i == id1 || i == id2)
                    status = 2;
                else
                    status = 0;
            }
            views[i] = new BoardView(i, status, image);
        }
        mHandler.sendMessage(obtain(mHandler, mHandler.mWhatRefresh, views));
    }
}
