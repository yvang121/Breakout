package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {
    BreakoutWall wall;
    Paddle paddle;
    private static final int PADDLE_HEIGHT_PLACEMENT = 75;

    public void run() {
        setBackground(Color.DARK_GRAY);
        wall = new BreakoutWall();
        add(wall, 0, 0);
        paddle = new Paddle();
        add(paddle.getPaddle(), getWidth()/2 - paddle.getPaddle().getWidth()/2,
                getHeight() - PADDLE_HEIGHT_PLACEMENT);
    }
}
