package com.tiengame.flappinlimbo.com.tiengame.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Column extends Scrollable{

    private Random r;

    private boolean isScored = false;

    // Collision Rectangles
    private Rectangle skullUp, skullDown, columnUp, columnDown;

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;  // y coordinate of where the ground starts

    // Invoke super constructor with Column constructor
    public Column(float x, float y, int width, int height, float scrollSpeed, float groundY)
    {
        super(x, y, width, height, scrollSpeed);

        // Initialize a Random object for random number generation
        r = new Random();

        skullUp = new Rectangle();
        skullDown = new Rectangle();
        columnUp = new Rectangle();
        columnDown = new Rectangle();

        this.groundY = groundY;
    }

    public boolean collides(Bird bird)
    {
        if(position.x < bird.getX() + bird.getWidth())
        {
            return(Intersector.overlaps(bird.getBoundingCircle(), columnUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), columnDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        // set() allows us to set up top-left corner's x,y coordinates along with width and height
        columnUp.set(position.x, position.y, width, height);
        columnDown.set(position.x, position.y + height + VERTICAL_GAP, width, groundY - (position.y + height + VERTICAL_GAP));

        // Skull width is 24 pixels, but the column width is only 22 so we shift the skull's x-coord 1 pixel to the left
        // Shift is (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, columnDown.y, SKULL_WIDTH, SKULL_HEIGHT);
    }

    @Override
    public void reset(float newX)
    {
        // Call reset in super class
        super.reset(newX);

        // Change the height of the column to a random number
        height = r.nextInt(90) + 15;

        isScored = false;
    }

    public void onRestart(float x, float scrollSpeed)
    {
        position.x = x;
        velocity.x = scrollSpeed;
    }

    public boolean isScored()
    {
        return isScored;
    }

    public void setScored(boolean bool)
    {
        isScored = bool;
    }

    public Rectangle getSkullUp() {return skullUp;}
    public Rectangle getSkullDown() { return skullDown; }
    public Rectangle getColumnUp() { return columnUp; }
    public Rectangle getColumnDown() { return columnDown; }
}