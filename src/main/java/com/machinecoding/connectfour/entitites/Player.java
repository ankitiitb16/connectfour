package com.machinecoding.connectfour.entitites;

import com.machinecoding.connectfour.enums.DiscColor;

public class Player {
    private String name;
    private DiscColor color;

    public Player(DiscColor discColor, String name) {
        this.color = discColor;
        this.name = name;
    }

    public DiscColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
