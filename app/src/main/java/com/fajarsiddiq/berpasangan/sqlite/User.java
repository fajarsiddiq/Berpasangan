package com.fajarsiddiq.berpasangan.sqlite;

import com.orm.dsl.Unique;

/**
 * Created by Muhammad Fajar on 31/05/2016.
 */
public class User extends Entity {
    @Unique
    String alias;
    String name;
    int coin;
    long totalDuration;
    int totalGame;
    int bestScore;
    int totalScore;
    double totalAccuracy;
    int totalZonk;
    int totalEncyclopediaUnlocked;
    int getTotalEncyclopediaRead;
    int coinSpent;

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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public double getTotalAccuracy() {
        return totalAccuracy;
    }

    public void setTotalAccuracy(double totalAccuracy) {
        this.totalAccuracy = totalAccuracy;
    }

    public int getTotalZonk() {
        return totalZonk;
    }

    public void setTotalZonk(int totalZonk) {
        this.totalZonk = totalZonk;
    }

    public int getTotalEncyclopediaUnlocked() {
        return totalEncyclopediaUnlocked;
    }

    public void setTotalEncyclopediaUnlocked(int totalEncyclopediaUnlocked) {
        this.totalEncyclopediaUnlocked = totalEncyclopediaUnlocked;
    }

    public int getGetTotalEncyclopediaRead() {
        return getTotalEncyclopediaRead;
    }

    public void setGetTotalEncyclopediaRead(int getTotalEncyclopediaRead) {
        this.getTotalEncyclopediaRead = getTotalEncyclopediaRead;
    }

    public int getCoinSpent() {
        return coinSpent;
    }

    public void setCoinSpent(int coinSpent) {
        this.coinSpent = coinSpent;
    }
}
