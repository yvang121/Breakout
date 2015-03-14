package edu.macalester.comp124.breakout;

import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

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
    private static final int PADDLE_MOVE = 15;
    private static final int BALL_MOVE = 7;
    private static final double WINDOW_WIDTH_MAX = 900;
    private static final double WINDOW_HEIGHT_MAX = 700;

    public void init() {
        addKeyListeners();
        addMouseListeners();
    }

    // Run method for the Breakout program.
    public void run() {
        setBackground(Color.DARK_GRAY);
        Dimension minimum = new Dimension(918, 900);
        setSize(minimum);
        RandomGenerator randX = new RandomGenerator();
        RandomGenerator randY = new RandomGenerator();

        brickWall = new BreakoutWall();
        add(brickWall, 0, 0);

        paddle = new Paddle();
        add(paddle.getPaddle(), getWidth()/2 - paddle.getPaddle().getWidth()/2,
                getHeight() - PADDLE_HEIGHT_PLACEMENT);

        ball = new Ball();
        double randXPos = randX.nextDouble(0, WINDOW_WIDTH_MAX - ball.getWidth());
        int randYPos = randY.nextInt(80);
        add(ball, randXPos, brickWall.getWallHeight() + randYPos);
        waitForClick();
        animateBall();
    }

    /* keyListeners pass all events into this method, and this method does things according to
    the input that was given. In this case, we're telling it what to do in the case of right and
    left arrows being pressed, and move the paddle. Restricts paddle to a certain position along
    the width of the window.
     */
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
        int brickCount = 100;
        while (true) {
            GObject objAtTopLeft = getElementAt(ball.getX() - 1, ball.getY() - 1);
            GObject objAtTopRight = getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1);
            GObject objAtBottomLeft = getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight() + 1);
            GObject objAtBottomRight = getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1);

            ball.move();

//                If the top left corner AND top right corner hit an object, move ball in opposite Y direction.
            if ((getElementAt(ball.getX() - 1, ball.getY() - 1)) != null
                    && getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1) != null) {
                if (getElementAt(ball.getX() - 1, ball.getY() - 1) instanceof BreakoutWall
                && getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1) instanceof BreakoutWall) {
                    remove(brickWall.getElementAt(ball.getX() - 1, ball.getY() - 1));
                    remove(brickWall.getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1));
                }
                ball.setDy(-ball.getDy());
            } //
//                If top right corner AND bottom right corner hit an object, move ball in opposite X direction.
            else if ((getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1)) != null &&
                    getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) != null) {
                remove(brickWall.getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1));
                remove(brickWall.getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1));
                ball.setDx(-ball.getDx());
            }
//                If bottom right corner AND bottom left corner hit an object, move ball in opposite Y direction.
            else if (getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) != null
                    && getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight() + 1) != null) {
                if (getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) instanceof BreakoutWall
                        && getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight() + 1) instanceof BreakoutWall) {
                    remove(brickWall.getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1));
                    remove(brickWall.getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight() + 1));
                }
                ball.setDy(-ball.getDy());
            }
//                If bottom left corner AND top left corner hit an object, move ball in opposite X direction.
            else if((getElementAt(ball.getX(), ball.getY() + ball.getHeight()) != null
                    && getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()) != null)
                    || getElementAt(ball.getX() + ball.getWidth()/2, ball.getY() + ball.getHeight() + 1) != null) {
                if (getElementAt(ball.getX(), ball.getY() + ball.getHeight()) instanceof BreakoutWall
                        && getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()) instanceof BreakoutWall) {
                    remove(brickWall.getElementAt(ball.getX(), ball.getY() + ball.getHeight()));
                    remove(brickWall.getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()));
                }
                ball.setDx(-ball.getDx());
            }

            /*
            Window bounds and what to do when ball hits a corner of window
             */
            if (ball.getX() + ball.getWidth() >= WINDOW_WIDTH_MAX) { // If ball goes beyond window width, reverse X direction
                ball.setDx(-ball.getDx());
            } else if (ball.getY() + ball.getHeight() >= WINDOW_HEIGHT_MAX) { // If ball goes below window height,
            // reverse Y direction
                ball.setDy(-ball.getDy());
            } else if (ball.getX() <= 0) { // If ball goes left of window width, reverse X direction
                ball.setDx(-ball.getDx());
            } else if (ball.getY() <= 0) { // If ball goes above window height, reverse Y direction
                ball.setDy(-ball.getDy());
            } else if ((ball.getX() + ball.getWidth() >= WINDOW_WIDTH_MAX)
                    && (ball.getY() + ball.getHeight()) >= WINDOW_HEIGHT_MAX) {
                    // If it hits bottom right corner of window, reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            } else if ((ball.getX() <= 0) && (ball.getY() + ball.getHeight() >= WINDOW_HEIGHT_MAX)) {
                // If it hits the bottom left corner of window , reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            } else if ((ball.getX() <= 0) && (ball.getY() <= 0)) {
            // If it hits top left corner of window, reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            } else if ((ball.getX() >= WINDOW_WIDTH_MAX) && (ball.getY() <= 0)) {
            // if it hits top right corner of window, reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            }
        }
    }

//    private GObject objAt;
//    private GPoint location;
//    public void mousePressed(MouseEvent e) {
//        location = new GPoint(e.getPoint());
//        objAt = getElementAt(location);
//    }
//
//    public void mouseDragged(MouseEvent e) {
//        if (objAt != null){
//            objAt.move(e.getX() - location.getX(), e.getY() - location.getY());
//            location = new GPoint(e.getPoint());
//        }
//    }
}