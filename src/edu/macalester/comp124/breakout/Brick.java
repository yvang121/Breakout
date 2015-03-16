package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Brick extends GRect {
    private static final int BRICK_WIDTH = 90;
    private static final int BRICK_HEIGHT = 25;

    public Brick(Color color) {
        super(BRICK_WIDTH, BRICK_HEIGHT);
        setFilled(true);
        setFillColor(color);
    }

    public Brick() {
        this(Color.RED);
    }
}
