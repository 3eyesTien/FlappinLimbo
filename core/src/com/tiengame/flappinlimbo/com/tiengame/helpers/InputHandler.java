package com.tiengame.flappinlimbo.com.tiengame.helpers;

import com.badlogic.gdx.InputProcessor;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Bird;

public class InputHandler implements InputProcessor {

    private Bird gameBird;

    public InputHandler(Bird bird)
    {
        this.gameBird = bird;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        this.gameBird.onClick();
        return true;    // Return true to say we handled a touchDown input
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
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
}
