package com.fajarsiddiq.berpasangan.module.board;

/**
 * Created by Muhammad Fajar on 11/04/2016.
 */
public class Answer extends Item {
    private boolean answered;

    public Answer(String value) {
        super(value);
        answered = false;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
