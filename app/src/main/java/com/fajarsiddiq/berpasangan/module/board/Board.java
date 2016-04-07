package com.fajarsiddiq.berpasangan.module.board;

import com.fajarsiddiq.berpasangan.sqlite.Item;

/**
 * Created by Muhammad Fajar on 03/04/2016.
 */
public class Board {
    private int height;
    private int width;
    private Item[] board;

    public Board(final int width, final int height) {
        this.height = height;
        this.width = width;
        board = new Item[width*height];
    }

    public Board(final Item[] items) {
        this.board = items;
    }

    public Item getCell(final int loc) {
        return board[loc];
    }
}
