package com.tiengame.flappinlimbo.com.tiengame.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private Vector2 position;
    private Vector2 velocity;       // Change in position
    private Vector2 acceleration;   // Change in velocity

    private float rotation; // For handling bird rotation when it jumps and flies
    private int width;
    private int height;

    private Circle boundingCircle;

    public Bird(float x, float y, int width, int height)
    {

        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
    }

    public void update(float delta)
    {
        // Scale our vectors with delta to keep our game frame-rate independent in case our processor slows down more than usual
        this.velocity.add(this.acceleration.cpy().scl(delta)); // Add scaled acceleration vector to our velocity

        // Limit velocity to 200
        if(this.velocity.y > 200){
            this.velocity.y = 200;
        }

        this.position.add(this.velocity.cpy().scl(delta));  // Add updated scaled velocity to bird's position

        // Set the bounding circle's center to be (9,6) with respect to the bird
        // Set the radius to 6.5f
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Rotate counterclockwise (rising)
        if(velocity.y < 0)
        {
            rotation -= 600 * delta;

            if(rotation < -20)
            {
                rotation = 20;
            }
        }

        // Rotate clockwise (falling)
        if(isFalling())
        {
            rotation += 480 * delta;
            if(rotation > 90)
            {
                rotation = 90;
            }
        }
    }

    public void onClick()
    {
        this.velocity.y = -140;
    }

    public float getX()
    {
        return this.position.x;
    }

    public float getY()
    {
        return this.position.y;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public float getRotation()
    {
        return this.rotation;
    }

    // Determine when the bird rotates downward
    public boolean isFalling()
    {
        return velocity.y > 110;
    }

    // Determine when to stop animating the bird's flap
    public boolean shouldNotFlap()
    {
        return velocity.y > 70;
    }

    public Circle getBoundingCircle() { return boundingCircle; }
}
