package com.tiengame.flappinlimbo.com.tiengame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsLoader {

    public static Texture texture, logoTexture;
    public static TextureRegion background, grass;

    public static Animation<TextureRegion> birdAnimations;
    public static TextureRegion bird, downBird, upBird, logo, flLogo, playButtonUp, playButtonDown;

    public static TextureRegion skullUp, skullDown, column;

    public static Sound dead, flap;

    public static BitmapFont font, fontShadow;

    public static Preferences preferences;

    public static void load()
    {
        logoTexture = new Texture(Gdx.files.internal("textures/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("textures/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        flLogo = new TextureRegion(texture, 0, 55, 135, 24);
        flLogo.flip(false, true);

        background = new TextureRegion(texture, 0, 0, 136, 43);
        background.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        downBird = new TextureRegion(texture, 136, 0, 17, 12);
        downBird.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        upBird = new TextureRegion(texture, 170, 0, 17, 12);
        upBird.flip(false, true);

        TextureRegion[] birds = { downBird, bird, upBird };
        birdAnimations = new Animation<TextureRegion>(0.06f, birds);
        birdAnimations.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);

        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        column = new TextureRegion(texture, 136, 16, 22, 3);
        column.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("sounds/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("sounds/flap.wav"));
        //score = Gdx.audio.newSound(Gdx.files.internal("sounds/ .wav"));

        font = new BitmapFont(Gdx.files.internal("textures/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        fontShadow = new BitmapFont(Gdx.files.internal("textures/shadow.fnt"));
        fontShadow.getData().setScale(.25f, -.25f);

        // Create/retrieve preferences file
        preferences = Gdx.app.getPreferences("FlappinLimbo");

        // Default high score
        if(!preferences.contains("highScore"))
        {
            preferences.putInteger("highScore", 0);
        }
    }

    // Takes an integer and maps it to the high score String in the preferences file
    public static void setHighScore(int highScore)
    {
        preferences.putInteger("highScore", highScore);
        preferences.flush();
    }

    // Finds current high score to display
    public static int getHighScore()
    {
        return preferences.getInteger("highScore");
    }

    public static void dispose()
    {
        // Dispose texture when we are finished
        texture.dispose();
        logoTexture.dispose();

        dead.dispose();
        flap.dispose();
        //score.dispose();

        font.dispose();
        fontShadow.dispose();
    }
}
