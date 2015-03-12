package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class BreakoutWall extends GCompound {
    Brick brick = new Brick(Color.RED);

    public BreakoutWall() {
        for (int i = 0; i < brick.getBrick().getWidth()* 10; i += brick.getBrick().getWidth()) {
            for (int j = 0; j < brick.getBrick().getHeight() * 2; j += brick.getBrick().getHeight()) {
                Brick redBrick = new Brick(Color.RED);
                add(redBrick.getBrick(), i, j + 150);

                Brick orangeBrick = new Brick(Color.ORANGE);
                add(orangeBrick.getBrick(), i, j + orangeBrick.getBrick().getHeight()*2 + 150);

                Brick yellowBrick = new Brick(Color.YELLOW);
                add(yellowBrick.getBrick(), i, j + yellowBrick.getBrick().getHeight()*4 + 150);

                Brick greenBrick = new Brick(Color.GREEN);
                add(greenBrick.getBrick(), i, j + greenBrick.getBrick().getHeight()*6 + 150);

                Brick cyanBrick = new Brick(Color.CYAN);
                add(cyanBrick.getBrick(), i, j + cyanBrick.getBrick().getHeight()*8 + 150);
            }
        }
    }
}
