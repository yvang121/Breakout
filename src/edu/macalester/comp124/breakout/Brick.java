package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Brick {
    private GRect brick;
    private static final double BRICK_WIDTH = 75;
    private static final double BRICK_HEIGHT = 25;

    public Brick() {
        this.brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
    }
}
