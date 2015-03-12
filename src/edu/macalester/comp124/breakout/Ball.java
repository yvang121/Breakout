package edu.macalester.comp124.breakout;

import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class Ball {
    private GOval ball;
    private static final int BALL_RADIUS = 15;

    public Ball() {
        this.ball = new GOval(BALL_RADIUS, BALL_RADIUS);
        this.ball.setFilled(true);
        this.ball.setFillColor(Color.WHITE);
    }

    public GOval getBall() {
        return ball;
    }

    public void setBall(GOval ball) {
        this.ball = ball;
    }
}