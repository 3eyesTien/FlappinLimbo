package com.tiengame.flappinlimbo.com.tiengame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tiengame.flappinlimbo.FLGame;
import com.tiengame.flappinlimbo.com.tiengame.TweenAccessors.SpriteAccessor;
import com.tiengame.flappinlimbo.com.tiengame.helpers.AssetsLoader;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class SplashScreen implements Screen {

    private TweenManager manager;
    private SpriteBatch sb;
    private Sprite sprite;
    private FLGame game;

    public SplashScreen(FLGame game)
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        sprite = new Sprite(AssetsLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * 0.7f;
        float scale = desiredWidth / sprite.getWidth();

        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2) - (sprite.getHeight() / 2));

        setupTween();
        sb = new SpriteBatch();
    }

    private void setupTween()
    {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, 0.8f).target(1).ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 0.4f)
                .build().setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
    }

    @Override
    public void render(float delta)
    {
        manager.update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sprite.draw(sb);
        sb.end();
    }

    @Override
    public void resize(int width, int height)
    {}

    @Override
    public void hide()
    {}

    @Override
    public void pause()
    {}

    @Override
    public void resume()
    {}

    @Override
    public void dispose()
    {}
}
