package com.fajarsiddiq.berpasangan.helper;

import com.fajarsiddiq.berpasangan.sqlite.Highscore;
import com.orm.SugarRecord;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Muhammad Fajar on 14/06/2016.
 */
public class HighscoreHelper {
    public static List<Highscore> process(final Highscore newScore, List<Highscore> highscores) {
        HighscoreComparator comparator = new HighscoreComparator();
        sort(highscores, comparator);
        final int lastIndex = highscores.size() - 1;
        if(highscores.size() >= 5) {
            if(newScore.getScore() > highscores.get(lastIndex).getScore()) {
                Highscore deleted = Highscore.findById(Highscore.class, highscores.get(lastIndex).getId());
                Highscore.delete(deleted);
                highscores.remove(lastIndex);
                highscores.add(newScore);
            }
        } else {
            if(newScore.getScore() > 0)
                highscores.add(newScore);
        }

        sort(highscores, comparator);
        SugarRecord.saveInTx(highscores);
        return highscores;
    }

    public static List<Highscore> sortHighscore(List<Highscore> highscores) {
        sort(highscores, new HighscoreComparator());
        return highscores;
    }

    static class HighscoreComparator implements Comparator<Highscore> {

        @Override
        public int compare(Highscore one, Highscore two) {
            return two.getScore() - one.getScore();
        }
    }
}
