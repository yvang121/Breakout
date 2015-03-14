package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class BreakoutWall extends GCompound {
    Brick brick = new Brick(Color.RED);

    public BreakoutWall() {
        for (int i = 0; i < brick.getWidth()* 10; i += brick.getWidth()) {
            for (int j = 0; j < brick.getHeight() * 2; j += brick.getHeight()) {
                Brick redBrick = new Brick(Color.RED);
                add(redBrick, i, j);

                Brick orangeBrick = new Brick(Color.ORANGE);
                add(orangeBrick, i, j + orangeBrick.getHeight()*2);

                Brick yellowBrick = new Brick(Color.YELLOW);
                add(yellowBrick, i, j + yellowBrick.getHeight()*4);

                Brick greenBrick = new Brick(Color.GREEN);
                add(greenBrick, i, j + greenBrick.getHeight()*6);

                Brick cyanBrick = new Brick(Color.CYAN);
                add(cyanBrick, i, j + cyanBrick.getHeight()*8);
            }
        }
    }
}
