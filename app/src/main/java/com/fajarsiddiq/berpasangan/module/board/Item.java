package com.fajarsiddiq.berpasangan.module.board;

/**
 * Created by Muhammad Fajar on 03/04/2016.
 */
public class Item {
    private String id;
    private String name;
    private String value;
    
    public Item(){
    }

    public Item(String value) {
        this.value = value;
    }

    public Item(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        Item item = (Item) object;
        return this.value.equals(item.value);
    }

    public void setAnswered(final boolean answered) {
        if(this instanceof Question)
            ((Question) this).setAnswered(answered);
        else if(this instanceof Answer)
            ((Answer) this).setAnswered(answered);
    }
}
