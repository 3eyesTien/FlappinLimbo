package com.tiengame.flappinlimbo.com.tiengame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.tiengame.flappinlimbo.com.tiengame.gameworld.GameRenderer;
import com.tiengame.flappinlimbo.com.tiengame.gameworld.GameWorld;
import com.tiengame.flappinlimbo.com.tiengame.helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    private float runTime = 0;

    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight)); // Create the inputHandler and give it a reference to the Bird

        renderer = new GameRenderer(world, (int) gameHeight, midPointY);
    }

    @Override
    public void render(float delta)
    {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);

        // Convert frame rate to string and print it
        Gdx.app.log("GameScreen FPS: ", (1/delta) + "");
    }

    @Override
    public void resize(int width, int height)
    {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
