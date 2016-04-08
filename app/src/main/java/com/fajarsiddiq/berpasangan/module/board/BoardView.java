package com.fajarsiddiq.berpasangan.module.board;

/**
 * Created by Muhammad Fajar on 08/04/2016.
 */
public class BoardView {
    private int id;
    private int answered; //0 = unanswered, 1 = answered, 2 = is viewed now, 3 = zonk
    private String value;

    public BoardView(int id, int answered) {
        this.id = id;
        this.answered = answered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    }
}
