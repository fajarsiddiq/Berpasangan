package com.fajarsiddiq.berpasangan.sqlite;

import com.orm.dsl.Unique;

/**
 * Created by fajar on 8/22/16.
 */

public class StoreContent extends Entity {
    @Unique
    String resName;
    String name;
    int unlocked;
    int type;

    public StoreContent() {}

    public StoreContent(final String resName) {
        this.resName = resName;
    }

    public StoreContent(String resName, String name, int unlocked, int type) {
        this.resName = resName;
        this.name = name;
        this.unlocked = unlocked;
        this.type = type;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(int unlocked) {
        this.unlocked = unlocked;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
