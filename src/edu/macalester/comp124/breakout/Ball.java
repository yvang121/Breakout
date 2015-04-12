package edu.macalester.comp124.breakout;

import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 * Ball object class
 */
public class Ball extends GOval {
    /**
    Constant variables.
    @param BALL_RADIUS which sets the ball radius to 15 pixels.
    @param dx is the number of pixels the ball moves in the x direction.
    @param dy is the number of pixels the ball moves in the y direction.
     */
    private static final int BALL_RADIUS = 15;
    private double dx = 7;
    private double dy = 7;

    /**
    Creates the ball object with a radius of 15 pixels (doesn't change),
    and extends a the GOval object, meaning the ball is a GOval object.
    Filled with color white.
     */
    public Ball() {
        super(BALL_RADIUS, BALL_RADIUS);
        setFilled(true);
        setFillColor(Color.WHITE);
    }

    /**
    Returns the difference in x (dx) which is set at 7 (above).
     @return dx where dx is a double-type number.
     */
    public double getDx() {
        return dx;
    }

    /**
    Returns the difference in y (dy) which is set at 7 (above).
    @return dy where dy is a double-type number.
     */
    public double getDy() {
        return dy;
    }

    /**
    Allows for a new value to be set for dx.
    @param newValue which will pass a value to set the dx to.
    @return the dx value and sets it to the passed parameter.
     */
    public void setDx(double newValue) {
        dx = newValue;
    }

    /**
    Allows for a new value to be set for dy.
    @param newValue which will pass a value to set the dy to.
    @return the dy value and sets it to the passed parameter.
     */
    public void setDy(double newValue) {
        dy = newValue;
    }

    /**
    Calls dx/dy for move and stores 'velocity' for a specific ball object.
    dx/dy can be changed when calling setters. Returns void.
     */
    public void move() {
        this.move(dx, dy);
        this.pause(45);
    }
}
