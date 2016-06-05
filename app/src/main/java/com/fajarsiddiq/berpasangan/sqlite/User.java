package com.fajarsiddiq.berpasangan.sqlite;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by Muhammad Fajar on 31/05/2016.
 */
public class User extends SugarRecord {
    @Unique
    String alias;
    String name;
    int coin;
    long totalDuration;
    int totalGame;
    int bestScore;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getTotalGame() {
        return totalGame;
    }

    public void setTotalGame(int totalGame) {
        this.totalGame = totalGame;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
