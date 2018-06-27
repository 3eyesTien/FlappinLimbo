package com.tiengame.flappinlimbo.com.tiengame.helpers;

import com.badlogic.gdx.InputProcessor;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Bird;
import com.tiengame.flappinlimbo.com.tiengame.gameworld.GameWorld;
import com.tiengame.flappinlimbo.com.tiengame.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

public class InputHandler implements InputProcessor {

    private GameWorld gameWorld;
    private Bird bird;

    private java.util.List<SimpleButton> menuButtons;

    private SimpleButton playButton;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld gameWorld, float scaleFactorX, float scaleFactorY)
    {
        this.gameWorld = gameWorld;
        this.bird = gameWorld.getBird();

        int midPointY = gameWorld.getMidPointY();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(136 / 2 - (AssetsLoader.playButtonUp.getRegionWidth() / 2), midPointY + 50, 29, 16, AssetsLoader.playButtonUp, AssetsLoader.playButtonDown);
        menuButtons.add(playButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if(gameWorld.isMenu())
        {
            playButton.isTouchDown(screenX, screenY);
        } else if (gameWorld.isReady())
        {
            gameWorld.start();
        }

        bird.onClick();

        if(gameWorld.isGameOver() || gameWorld.isHighScore())
        {
            // Reset all variables, go to GameState.READY
            gameWorld.restart();
        }
        return true;    // Return true to say we handled a touchDown input
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {

        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if(gameWorld.isMenu())
        {
            if(playButton.isTouchUp(screenX, screenY))
            {
                gameWorld.ready();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyDown(int keyCode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keyCode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }

    private int scaleX(int screenX)
    {
        return (int) (screenX/scaleFactorX);
    }

    private int scaleY(int screenY)
    {
        return (int) (screenY/scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons()
    {
        return menuButtons;
    }
}
