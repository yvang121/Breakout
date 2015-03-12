package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Brick {
    private GRect brick;
    private static final int BRICK_WIDTH = 90;
    private static final int BRICK_HEIGHT = 25;

    public Brick (Color color) {
        this.brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        this.brick.setFilled(true);
        this.brick.setFillColor(color);
    }

    @Override
    public String toString() {
        return "Brick = " + brick;
    }

    public GRect getBrick() {
        return brick;
    }

    public void setBrick(GRect brick) {
        this.brick = brick;
    }

    public double getBrickWidth() {
        return brick.getWidth();
    }

    public double getBrickHeight() {
        return brick.getHeight();
    }
}
