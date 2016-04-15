package com.fajarsiddiq.berpasangan.module.board;

/**
 * Created by Muhammad Fajar on 08/04/2016.
 */
public class BoardView {
    private int id;
    private int answered; //0 = unanswered, 1 = answered, 2 = is viewed now, 3 = zonk
    private boolean image;

    public BoardView(int id, int answered, boolean image) {
        this.id = id;
        this.answered = answered;
        this.image = image;
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

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
