package com.fajarsiddiq.berpasangan.sqlite;

import com.orm.dsl.Unique;

/**
 * Created by Muhammad Fajar on 31/05/2016.
 */
public class Highscore extends Entity {
    @Unique
    long timeStamp;
    String alias;
    int score;
    String mode;

    public Highscore() {}

    public Highscore(long timeStamp, String alias, int score, String mode) {
        this.timeStamp = timeStamp;
        this.alias = alias;
        this.score = score;
        this.mode = mode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
