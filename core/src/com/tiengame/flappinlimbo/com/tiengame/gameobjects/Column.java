package com.tiengame.flappinlimbo.com.tiengame.gameobjects;

import java.util.Random;

public class Column extends Scrollable{

    private Random r;

    // Invoke super constructor with Column constructor
    public Column(float x, float y, int width, int height, float scrollSpeed)
    {
        super(x, y, width, height, scrollSpeed);

        // Initialize a Random object for random number generation
        r = new Random();
    }

    @Override
    public void reset(float newX)
    {
        // Call reset in super class
        super.reset(newX);

        // Change the height of the column to a random number
        height = r.nextInt(90) + 15;
    }
}
