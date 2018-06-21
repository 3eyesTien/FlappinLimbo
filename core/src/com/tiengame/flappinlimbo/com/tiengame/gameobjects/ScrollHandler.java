package com.tiengame.flappinlimbo.com.tiengame.gameobjects;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.tiengame.flappinlimbo.com.tiengame.gameworld.GameWorld;

// Handles Scrollable objects on screen
public class ScrollHandler {

    private GameWorld gameWorld;

    // Create the five scrollable objects
    private Grass frontGrass, backGrass;
    private Column column1, column2, column3;

    // Scrollable object constants
    public static final int SCROLL_SPEED = -59;
    public static final int COLUMN_GAP = 49;

    // Constructor takes float to tell us where the grass and columns should be drawn
    public ScrollHandler(GameWorld gameWorld, float posY)
    {
        this.gameWorld = gameWorld;
        frontGrass = new Grass(0, posY, 143, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), posY, 143, 11, SCROLL_SPEED);

        column1 = new Column(210, 0, 22, 60, SCROLL_SPEED, posY);
        column2 = new Column(column1.getTailX() + COLUMN_GAP, 0, 22, 70, SCROLL_SPEED, posY);
        column3 = new Column(column2.getTailX() + COLUMN_GAP, 0, 22, 60, SCROLL_SPEED, posY);
    }

    public void update(float delta)
    {
        frontGrass.update(delta);
        backGrass.update(delta);
        column1.update(delta);
        column2.update(delta);
        column3.update(delta);

        // Check if any columns are offscreen
        if(column1.isOffScreen())
        {
            column1.reset(column3.getTailX() + COLUMN_GAP);
        }
        else if(column2.isOffScreen())
        {
            column2.reset(column1.getTailX() + COLUMN_GAP);
        }
        else if(column3.isOffScreen())
        {
            column3.reset(column2.getTailX() + COLUMN_GAP);
        }

        // Grass check
        if(frontGrass.isOffScreen())
        {
            frontGrass.reset(backGrass.getTailX());
        }
        else if(backGrass.isOffScreen())
        {
            backGrass.reset(frontGrass.getTailX());
        }
    }

    public void stop()
    {
        frontGrass.stop();
        backGrass.stop();
        column1.stop();
        column2.stop();
        column3.stop();
    }

    // Return true if any of the columns hit bird
    public boolean collides(Bird bird)
    {
        if(!column1.isScored() && column1.getX() + (column1.getWidth() / 2) < bird.getX() + bird.getWidth())
        {
            addScore(1);
            column1.setScored(true);
            //AssetLoader.score.play();
        } else if(!column2.isScored() && column2.getX() + (column2.getWidth() / 2) < bird.getX() + bird.getWidth())
        {
            addScore(1);
            column2.setScored(true);
            //AssetLoader.score.play();
        } else if(!column3.isScored() && column3.getX() + (column3.getWidth() / 2) < bird.getX() + bird.getWidth())
        {
            addScore(1);
            column3.setScored(true);
            //AssetLoader.score.play();
        }
        return (column1.collides(bird) || column2.collides(bird) || column3.collides(bird));
    }

    private void addScore(int increment)
    {
        gameWorld.addScore(increment);
    }

    public void onRestart()
    {
        frontGrass.onRestart(0, SCROLL_SPEED);
        backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
        column1.onRestart(210, SCROLL_SPEED);
        column2.onRestart(column1.getTailX() + COLUMN_GAP, SCROLL_SPEED);
        column3.onRestart(column2.getTailX() + COLUMN_GAP, SCROLL_SPEED);
    }

    // Getters for objects
    public Grass getFrontGrass() {return frontGrass;}
    public Grass getBackGrass() {return backGrass;}

    public Column getColumn1() {return column1;}
    public Column getColumn2() {return column2;}
    public Column getColumn3() {return column3;}
}
