package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {
    BreakoutWall brickWall;
    Paddle paddle;
    Ball ball;
    private static final int PADDLE_HEIGHT_PLACEMENT = 50;

    public void init() {
        addKeyListeners();
    }

    public void run() {
        setBackground(Color.DARK_GRAY);
        Dimension minimum = new Dimension(918,900);
        setSize(minimum);

        brickWall = new BreakoutWall();
        add(brickWall, 0, 0);

        paddle = new Paddle();
        add(paddle.getPaddle(), getWidth()/2 - paddle.getPaddle().getWidth()/2,
                getHeight() - PADDLE_HEIGHT_PLACEMENT);

        ball = new Ball();
        add(ball.getBall(), getWidth() / 2 - ball.getBall().getWidth(),
                paddle.getPaddle().getY() - paddle.getPaddle().getHeight() - 5);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: paddle.getPaddle().move(-13, 0); break;
            case KeyEvent.VK_RIGHT: paddle.getPaddle().move(13, 0); break;
        }
    }
}
