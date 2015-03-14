package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Paddle extends GRect{
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;

    public Paddle() {
        super(PADDLE_WIDTH, PADDLE_HEIGHT);
        setFilled(true);
        setFillColor(Color.lightGray);
    }
}
