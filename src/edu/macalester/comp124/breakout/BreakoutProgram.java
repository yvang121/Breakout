package edu.macalester.comp124.breakout;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {
    Paddle paddle;
    Ball ball = new Ball();
    Brick brick = new Brick();
    RandomGenerator randX = new RandomGenerator();
    RandomGenerator randY = new RandomGenerator();
    int restarts = 3;
    int brickCount = 100;

    private static final int PADDLE_HEIGHT_PLACEMENT = 50;
    private static final int PADDLE_MOVE = 16;
    private static final double WINDOW_WIDTH_MAX = 900;
    private static final double WINDOW_HEIGHT_MAX = 800;
    private static final int WALL_PLACEMENT_Y = 100;

    /*
    Add listeners for keyboard events.
     */
    public void init() {
        addKeyListeners();
    }
    /*
    Run method for the Breakout program.
     */
    public void run() {
        setBackground(Color.DARK_GRAY);
        Dimension minimum = new Dimension(918, 900);
        setSize(minimum);

        createWall();

        paddle = new Paddle();
        add(paddle, getWidth()/2 - paddle.getWidth()/2, getHeight() - PADDLE_HEIGHT_PLACEMENT);

        ball = new Ball();
        double randXPos = randX.nextDouble(0, WINDOW_WIDTH_MAX - ball.getWidth());
        int randYPos = randY.nextInt(80);
        add(ball, randXPos, 250 + WALL_PLACEMENT_Y + randYPos);

        waitForClick();
        animateBall();
    }

    /* keyListeners pass all events into this method, and this method does things according to
    the input that was given. In this case, we're telling it what to do in the case of right and
    left arrows being pressed, and move the paddle. Restricts paddle to a certain position along
    the width of the window.
     */
    public void keyPressed(KeyEvent e) {
        double windowDifferenceR = WINDOW_WIDTH_MAX - (paddle.getX() + paddle.getWidth());
        double windowDifferenceL = paddle.getX();

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && windowDifferenceR < PADDLE_MOVE) {
            paddle.move(windowDifferenceR, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.getX() +
                paddle.getWidth() < WINDOW_WIDTH_MAX) {
            paddle.move(PADDLE_MOVE, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && (paddle.getX() +
                paddle.getWidth()) >= WINDOW_WIDTH_MAX) {
            paddle.move(0, 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && windowDifferenceL < PADDLE_MOVE) {
            paddle.move(-windowDifferenceL, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.getX() < WINDOW_WIDTH_MAX) {
            paddle.move(-PADDLE_MOVE, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.getX() <= 0) {
            paddle.move(0, 0);
        }
    }

    /*
    The method that contains all of the ball's behaviors, as well as cases in which it can encounter,
    and respond to them accordingly.
     */
    public void animateBall() {
        while (true) {
            ball.move();
//                If there are no more bricks, print the win message.
            if (brickCount == 0) {
                printWin();
                break;
            }
//                If the top left corner AND top right corner hit an object, move ball in opposite Y direction.
            if ((getElementAt(ball.getX(), ball.getY())) != null
                    && getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1) != null) {
                if (getElementAt(ball.getX(), ball.getY()) instanceof Brick
                        && getElementAt(ball.getX() + ball.getWidth(), ball.getY()) instanceof Brick) {
                    remove(getElementAt(ball.getX(), ball.getY()));
                    brickCount--;
                }
                ball.setDy(-ball.getDy());
            } //
//                If top right corner AND bottom right corner hit an object, move ball in opposite X direction.
            else if ((getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1)) != null &&
                    getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) != null) {
                if ((getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1)) instanceof Brick
                        && getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) instanceof Brick) {
                    remove(getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() - 1));
                    brickCount--;
                }
                ball.setDx(-ball.getDx());
            }
//                If bottom right corner AND bottom left corner hit an object, move ball in opposite Y direction.
            else if (getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) != null
                    && getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight() + 1) != null) {
                if (getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1) instanceof Brick
                        && getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight() + 1) instanceof Brick) {
                    remove(getElementAt(ball.getX() + ball.getWidth() + 1, ball.getY() + ball.getHeight() + 1));
                    brickCount--;
                }
                ball.setDy(-ball.getDy());
            }
//                If bottom left corner AND top left corner hit an object, move ball in opposite X direction.
            else if((getElementAt(ball.getX(), ball.getY() + ball.getHeight()) != null
                    && getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()) != null)
                    || getElementAt(ball.getX() + ball.getWidth()/2, ball.getY() + ball.getHeight() + 1) != null) {
                if (getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight()) instanceof Brick) {
                    remove(getElementAt(ball.getX() - 1, ball.getY() + ball.getHeight()));
                    brickCount--;
                } else if (getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()) instanceof Brick) {
                    remove(getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()));
                    brickCount--;
                } else if (getElementAt(ball.getX(), ball.getY() + ball.getHeight()) instanceof Paddle) {
                    ball.setDx(-ball.getDx());
                    ball.setDy(-ball.getDy());
                }
                ball.setDx(-ball.getDx());
            }
            /*
            Window bounds and what to do when ball goes past it (width/height of window).
             */
            if (ball.getX() + ball.getWidth() >= WINDOW_WIDTH_MAX) {
            // If ball goes beyond window width, reverse X direction
                ball.setDx(-ball.getDx());
            }
            if (ball.getY() + ball.getHeight() >= WINDOW_HEIGHT_MAX
                    && ball.getY() + ball.getHeight() + ball.getWidth() >= WINDOW_HEIGHT_MAX) {
            // If ball goes below window height, reverse Y direction
                ball.setDy(-ball.getDy());
//                restarts--;
//                if (restarts > 0) {
//                    restart();
//                } else if (restarts == 0) {
//                    printGameOver();
//                    break;
//                }
            }
            if (ball.getX() <= 0) {
            // If ball goes left of window width, reverse X direction
                ball.setDx(-ball.getDx());
            }
            if (ball.getY() <= 0) {
            // If ball goes above window height, reverse Y direction
                ball.setDy(-ball.getDy());
            }

            /*
            If ball hits one of the four corners of the screen;
             */
            if ((ball.getX() + ball.getWidth() >= WINDOW_WIDTH_MAX)
                    && (ball.getX() + ball.getHeight() + ball.getWidth() >= WINDOW_WIDTH_MAX)
                    && (ball.getY() + ball.getHeight() >= WINDOW_HEIGHT_MAX)
                    && (ball.getY() + ball.getHeight() + ball.getWidth() >= WINDOW_HEIGHT_MAX)) {
            // If it hits bottom right corner of window, reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            }
            if ((ball.getX() <= 0)
                    && (ball.getX() + ball.getHeight() <= 0)
                    && (ball.getY() + ball.getHeight() >= WINDOW_HEIGHT_MAX)
                    && (ball.getY() + ball.getHeight() + ball.getWidth() >= WINDOW_HEIGHT_MAX)) {
            // If it hits the bottom left corner of window , reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            }
            if ((ball.getX() <= 0)
                    && (ball.getX() + ball.getHeight() <= 0)
                    && (ball.getY() <= 0)
                    && (ball.getY() + ball.getWidth()) <= 0) {
            // If it hits top left corner of window, reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            }
            if ((ball.getX() + ball.getWidth() >= WINDOW_WIDTH_MAX)
                    && (ball.getX() + ball.getHeight() + ball.getWidth() >= WINDOW_WIDTH_MAX)
                    && (ball.getY() <= 0)
                    && (ball.getY() + ball.getWidth() <= 0)) {
            // if it hits top right corner of window, reverse X and Y direction
                ball.setDx(-ball.getDx());
                ball.setDy(-ball.getDy());
            }
        }
    }

    /*
    Creates the brick wall for the ball to "break".
     */
    public void createWall() {
        for (int i = 0; i < brick.getWidth()* 10; i += brick.getWidth()) {
            for (int j = 0; j < brick.getHeight() * 2; j += brick.getHeight()) {
                Brick redBrick = new Brick(Color.RED);
                add(redBrick, i, j + WALL_PLACEMENT_Y);

                Brick orangeBrick = new Brick(Color.ORANGE);
                add(orangeBrick, i, j + orangeBrick.getHeight()*2 + WALL_PLACEMENT_Y);

                Brick yellowBrick = new Brick(Color.YELLOW);
                add(yellowBrick, i, j + yellowBrick.getHeight()*4 + WALL_PLACEMENT_Y);

                Brick greenBrick = new Brick(Color.GREEN);
                add(greenBrick, i, j + greenBrick.getHeight()*6 + WALL_PLACEMENT_Y);

                Brick cyanBrick = new Brick(Color.CYAN);
                add(cyanBrick, i, j + cyanBrick.getHeight()*8 + WALL_PLACEMENT_Y);
            }
        }
    }

    /*
    Method that stores the window popup in the case that there are no more bricks.
     */
    public void printWin() {
        JOptionPane.showMessageDialog(null, "Congratulations! You win an imaginary cookie!",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
    Method that stores the window popup in the case that there are no more restarts.
     */
    public void printGameOver() {
        JOptionPane.showMessageDialog(null, "No more restarts! Sorry!",
                "Out of Restarts", JOptionPane.ERROR_MESSAGE);
    }
    /*
    Restart method is called when the user fails to hit the ball. Resets ball and calls animate method.
     */
    public void restart() {
        remove(ball);
        double randXPos = randX.nextDouble(0, WINDOW_WIDTH_MAX - ball.getWidth());
        int randYPos = randY.nextInt(80);
        add(ball, randXPos, 250 + WALL_PLACEMENT_Y + randYPos);
        waitForClick();
        animateBall();
    }
}