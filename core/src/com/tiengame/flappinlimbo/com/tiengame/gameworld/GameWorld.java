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

    private Rectangle ground;

    private int score = 0;
    private int midPointY;

    private GameState currentState;

    public enum GameState{
        READY,
        RUNNING,
        GAMEOVER
    }

    public GameWorld(int midPointY){
        currentState = GameState.READY;
        this.midPointY = midPointY;

        bird = new Bird(33, midPointY - 5, 17, 12);

        // The grass starts at 66 pixels below midPointY
        scrollHandler = new ScrollHandler(this, midPointY + 66);

        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta)
    {
        switch(currentState)
        {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    public void updateReady(float delta)
    {

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
        }
        Gdx.app.log("GameWorld", "update");
    }

    public Bird getBird() {
        return bird;
    }

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
}
