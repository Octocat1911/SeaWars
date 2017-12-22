package com.team5.seawar.ship;

public class ShipPosition {
    private int x;
    private int y;
    public enum Orientation {TOP, BOTTOM, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};
    private Orientation orientation;

    public ShipPosition(int x, int y, Orientation orientation){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}

