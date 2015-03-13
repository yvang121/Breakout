package edu.macalester.comp124.breakout;

import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Ball extends GOval {
    private static final int BALL_RADIUS = 15;
    private double dx = 7;
    private double dy = 7;

    public Ball() {
        super(BALL_RADIUS, BALL_RADIUS);
        setFilled(true);
        setFillColor(Color.WHITE);
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double newValue) {
        dx = newValue;
    }

    public void setDy(double newValue) {
        dy = newValue;
    }

    public void move() {
        this.move(dx, dy);
        this.pause(10);
    }
}