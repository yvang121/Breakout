package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class BreakoutWall extends GCompound {
    Brick brick = new Brick(Color.RED);

    public BreakoutWall() {
        for (int i = 0; i < brick.getBrickWidth()* 10; i += brick.getBrickWidth()) {
            for (int j = 0; j < brick.getBrickHeight() * 2; j += brick.getBrickHeight()) {
                Brick redBrick = new Brick(Color.RED);
                add(redBrick.getBrick(), i, j + 125);

                Brick orangeBrick = new Brick(Color.ORANGE);
                add(orangeBrick.getBrick(), i, j + orangeBrick.getBrickHeight()*2 + 125);

                Brick yellowBrick = new Brick(Color.YELLOW);
                add(yellowBrick.getBrick(), i, j + yellowBrick.getBrickHeight()*4 + 125);

                Brick greenBrick = new Brick(Color.GREEN);
                add(greenBrick.getBrick(), i, j + greenBrick.getBrickHeight()*6 + 125);

                Brick cyanBrick = new Brick(Color.CYAN);
                add(cyanBrick.getBrick(), i, j + cyanBrick.getBrickHeight()*8 + 125);
            }
        }
    }

    public int getWallHeight() {
        return (int) brick.getBrickHeight()*10 + 125;
    }

    public int getWallWidth() {
        return (int) brick.getBrickWidth()*10;
    }
}
