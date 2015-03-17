package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Brick extends GRect {
    private static final int BRICK_WIDTH = 90;
    private static final int BRICK_HEIGHT = 25;

    /*
    Constructor for Brick objects. Takes parameter of color,
    which then will fill the brick to the passed color parameter.
    Will always have a width of 90 and a height of 25.
     */
    public Brick(Color color) {
        super(BRICK_WIDTH, BRICK_HEIGHT);
        setFilled(true);
        setFillColor(color);
    }

    /*
    Default constructor if no color is passed is red.
    Bricks are usually red, right?
     */
    public Brick() {
        this(Color.RED);
    }
}
