package com.tiengame.flappinlimbo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.tiengame.flappinlimbo.com.tiengame.helpers.AssetsLoader;
import com.tiengame.flappinlimbo.com.tiengame.screens.GameScreen;

public class FLGame extends Game {

	@Override
	public void create () {
		AssetsLoader.load();
	    setScreen(new GameScreen());
	}

	@Override
    public void dispose()
    {
        super.dispose();
        AssetsLoader.dispose();
    }
}
