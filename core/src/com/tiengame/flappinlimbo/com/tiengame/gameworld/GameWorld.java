package com.tiengame.flappinlimbo.com.tiengame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Bird;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.ScrollHandler;
import com.tiengame.flappinlimbo.com.tiengame.helpers.AssetsLoader;

// Responsible for updating what happens in the gameworld before being rendered to the screen
public class GameWorld {

    private Bird bird;
    private ScrollHandler scrollHandler;
    private float runTime = 0;

    private Rectangle ground;

    private int score = 0;
    private int midPointY;

    private GameState currentState;

    public enum GameState{
        MENU,
        READY,
        RUNNING,
        GAMEOVER,
        HIGHSCORE
    }

    public GameWorld(int midPointY){
        currentState = GameState.MENU;
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);

        // The grass starts at 66 pixels below midPointY
        scrollHandler = new ScrollHandler(this, midPointY + 66);

        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta)
    {
        runTime += delta;
        switch(currentState)
        {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    public void updateReady(float delta)
    {
        bird.updateReady(runTime);
        scrollHandler.updateReady(delta);
    }

    public void updateRunning(float delta){

        // Add delta cap if the game takes too long to process to update
        // so that we don't break the collision detection
        if(delta > .15f)
        {
            delta = .15f;
        }

        bird.update(delta);
        scrollHandler.update(delta);

        if(scrollHandler.collides(bird) && bird.isAlive())
        {
            // Clean up on game over
            scrollHandler.stop();
            bird.die();
            AssetsLoader.dead.play();
        }

        if(Intersector.overlaps(bird.getBoundingCircle(), ground))
        {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            if(score > AssetsLoader.getHighScore())
            {
                AssetsLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public Bird getBird() {
        return bird;
    }

    public int getMidPointY() { return midPointY; }

    public int getScore()
    {
        return score;
    }

    public void addScore(int increment)
    {
        score += increment;
    }

    public ScrollHandler getScrollHandler() {return scrollHandler;}

    public boolean isReady()
    {
        return currentState == GameState.READY;
    }

    public void start()
    {
        currentState = GameState.RUNNING;
    }

    public void ready() { currentState = GameState.READY; }

    public void restart()
    {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scrollHandler.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver()
    {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore()
    {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() { return currentState == GameState.MENU; }

    public boolean isRunning() { return currentState == GameState.RUNNING; }
}
