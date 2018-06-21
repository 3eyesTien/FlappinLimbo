package com.tiengame.flappinlimbo.com.tiengame.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isOffScreen;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed)
    {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isOffScreen = false;
    }

    public void update(float delta)
    {
        position.add(velocity.cpy().scl(delta));

        // If the scrollable object is offscreen
        if(position.x + width < 0)
        {
            isOffScreen = true;
        }
    }

    // Reset: Override in subclasses for specific behavior
    public void reset(float newX)
    {
        position.x = newX;
        isOffScreen = false;
    }

    public void stop()
    {
        velocity.x = 0;
    }

    public boolean isOffScreen()
    {
        return isOffScreen;
    }

    // Getters
    public float getTailX()
    {
        return position.x + width;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }
}
