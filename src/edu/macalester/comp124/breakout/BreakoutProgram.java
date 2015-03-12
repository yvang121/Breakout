package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

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
    private static final int PADDLE_MOVE = 15;
    private static final int BALL_MOVE = 7;
    private static final int WINDOW_WIDTH_MAX = 900;

    public void init() {
        addKeyListeners();
    }

    // Run method for the Breakout program.
    public void run() {
        setBackground(Color.DARK_GRAY);
        Dimension minimum = new Dimension(918, 900);
        setSize(minimum);
        Random randX = new Random();
        Random randY = new Random();

        brickWall = new BreakoutWall();
        add(brickWall, 0, 0);

        paddle = new Paddle();
        add(paddle.getPaddle(), getWidth()/2 - paddle.getPaddle().getWidth()/2,
                getHeight() - PADDLE_HEIGHT_PLACEMENT);

        ball = new Ball();
        int randXPos = randX.nextInt(WINDOW_WIDTH_MAX - ball.getBallWidth());
        int randYPos = randY.nextInt(80);
        add(ball.getBall(), randXPos, brickWall.getWallHeight() + randYPos);
        waitForClick();
        animateBall();
    }

    public void keyPressed(KeyEvent e) {
        double windowDifferenceR = WINDOW_WIDTH_MAX - (paddle.getPaddleX() + paddle.getPaddleWidth());
        double windowDifferenceL = paddle.getPaddleX();

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && windowDifferenceR < PADDLE_MOVE) {
            paddle.getPaddle().move(windowDifferenceR, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.getPaddleX() +
                paddle.getPaddleWidth() < WINDOW_WIDTH_MAX) {
            paddle.getPaddle().move(PADDLE_MOVE, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && (paddle.getPaddleX() +
                paddle.getPaddleWidth()) >= WINDOW_WIDTH_MAX) {
            paddle.getPaddle().move(0, 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && windowDifferenceL < PADDLE_MOVE) {
            paddle.getPaddle().move(-windowDifferenceL, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.getPaddleX() < WINDOW_WIDTH_MAX) {
            paddle.getPaddle().move(-PADDLE_MOVE, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.getPaddleX() <= 0) {
            paddle.getPaddle().move(0 , 0);
        }
    }

    public void animateBall() {
        Random randBoolean = new Random();
        Boolean randBool = randBoolean.nextBoolean();

        if (randBool == false) {
            while (true) {
                /*
                If the Boolean returns false, move in the diagonal left downward.
                 */
                ball.getBall().move(-BALL_MOVE, BALL_MOVE);
                ball.getBall().pause(55);

                /*
                If the top left corner AND top right corner hit an object, move ball in opposite Y direction.
                 */
                if (getElementAt(ball.getBallX(), ball.getBallY() - 1) != null &&
                        getElementAt((ball.getBallX() + ball.getBallWidth()), ball.getBallY() - 1) != null) {
                    while (true) {
                        ball.getBall().move(-BALL_MOVE, -BALL_MOVE);
                    }
                }
                /*
                If top right corner AND bottom right corner hit an object, move ball in opposite X direction.
                 */
                else if (getElementAt(ball.getBallX() + ball.getBallWidth() + 1, ball.getBallY()) != null
                        && getElementAt(ball.getBallX() + ball.getBallWidth() + 1, ball.getBallY() + ball.getBallHeight()) != null) {
                    ball.getBall().move(BALL_MOVE, BALL_MOVE);
                }
                /*
                If bottom right corner AND bottom left corner hit an object, move ball in opposite Y direction.
                 */
                else if (getElementAt(ball.getBallX() + ball.getBallWidth(), ball.getBallY() + ball.getBallHeight() + 1) != null
                        && getElementAt(ball.getBallX(), ball.getBallY() + ball.getBallHeight() + 1) != null) {
                    while (true) {
                        ball.getBall().move(-BALL_MOVE, -BALL_MOVE);
                    }
                }
                /*
                If bottom left corner AND top left corner hit an object, move ball in opposite X direction.
                 */
                else if(getElementAt(ball.getBallX() - 1, ball.getBallY() + ball.getBallHeight()) != null
                        && getElementAt(ball.getBallX() - 1, ball.getBallY()) != null) {
                    ball.getBall().move(-BALL_MOVE, BALL_MOVE);
                }
            }
        } else {
            while (true) {
                /*
                If Boolean returns true, move in the diagonal right downward
                 */
                ball.getBall().move(BALL_MOVE, BALL_MOVE);
                ball.getBall().pause(55);

                /*
                If the top left corner AND top right corner hit an object, move ball in opposite Y direction.
                 */
                if (getElementAt(ball.getBallX(), ball.getBallY() - 1) != null &&
                        getElementAt((ball.getBallX() + ball.getBallWidth()), ball.getBallY() - 1) != null) {
                    ball.getBall().move(-BALL_MOVE, -BALL_MOVE);
                }
                /*
                If top right corner AND bottom right corner hit an object, move ball in opposite X direction.
                 */
                else if (getElementAt(ball.getBallX() + ball.getBallWidth() + 1, ball.getBallY()) != null
                        && getElementAt(ball.getBallX() + ball.getBallWidth() + 1, ball.getBallY() + ball.getBallHeight()) != null) {
                    ball.getBall().move(BALL_MOVE, BALL_MOVE);
                }
                /*
                If bottom right corner AND bottom left corner hit an object, move ball in opposite Y direction.
                 */
                else if (getElementAt(ball.getBallX() + ball.getBallWidth(), ball.getBallY() + ball.getBallHeight() + 1) != null
                        && getElementAt(ball.getBallX(), ball.getBallY() + ball.getBallHeight() + 1) != null) {
                    ball.getBall().move(BALL_MOVE, -BALL_MOVE);
                }
                /*
                If bottom left corner AND top left corner hit an object, move ball in opposite X direction.
                 */
                else if(getElementAt(ball.getBallX() - 1, ball.getBallY() + ball.getBallHeight()) != null
                        && getElementAt(ball.getBallX() - 1, ball.getBallY()) != null) {
                    ball.getBall().move(-BALL_MOVE, BALL_MOVE);
                }
            }
        }
    }
}
