package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;

import java.awt.*;

/**
 * Created by Ye Vang on 3/11/2015.
 */
public class BreakoutWall extends GCompound {
    Brick redBrick = new Brick(Color.RED);

    public BreakoutWall() {
        for (int i = 0; i < redBrick.getBrick().getWidth()* 10; i += redBrick.getBrick().getWidth()) {
            for (int j = 0; j < redBrick.getBrick().getHeight() * 2; j += redBrick.getBrick().getHeight()) {
                Brick redBrick = new Brick(Color.RED);
                add(redBrick.getBrick(), i, j);

                Brick orangeBrick = new Brick(Color.ORANGE);
                add(orangeBrick.getBrick(), i, j + orangeBrick.getBrick().getHeight()*2);

                Brick yellowBrick = new Brick(Color.YELLOW);
                add(yellowBrick.getBrick(), i, j + yellowBrick.getBrick().getHeight()*4);

                Brick greenBrick = new Brick(Color.GREEN);
                add(greenBrick.getBrick(), i, j + greenBrick.getBrick().getHeight()*6);

                Brick cyanBrick = new Brick(Color.CYAN);
                add(cyanBrick.getBrick(), i, j + cyanBrick.getBrick().getHeight()*8);
            }
        }
    }
}
