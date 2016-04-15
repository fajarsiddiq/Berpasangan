package com.fajarsiddiq.berpasangan.module.board;

import com.fajarsiddiq.berpasangan.sqlite.Item;

/**
 * Created by Muhammad Fajar on 03/04/2016.
 */
public class Question extends Item {
    private boolean image;
    private boolean answered;

    public Question(String name) {
        super(name);
        answered = false;
        image = false;
    }

    public Question(String id, String name, String value) {
        super(id, name, value);
        answered = false;
        image = true;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
