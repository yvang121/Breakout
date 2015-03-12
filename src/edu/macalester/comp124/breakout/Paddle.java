package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Paddle {
    private GRect paddle;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;

    public Paddle() {
        this.paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        this.paddle.setFilled(true);
        this.paddle.setFillColor(Color.lightGray);
    }

    public GRect getPaddle() {
        return paddle;
    }

    public void setPaddle(GRect paddle) {
        this.paddle = paddle;
    }

    public int getPaddleX() {
        return (int) paddle.getX();
    }

    public int getPaddleY() {
        return (int) paddle.getY();
    }

    public int getPaddleWidth() {
        return (int) paddle.getWidth();
    }

    public int getPaddleHeight() {
        return (int) paddle.getHeight();
    }
}
