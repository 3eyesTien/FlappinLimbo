package com.tiengame.flappinlimbo.com.tiengame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsLoader {

    public static Texture texture;
    public static TextureRegion background, grass;

    public static Animation<TextureRegion> birdAnimations;
    public static TextureRegion bird, downBird, upBird;

    public static TextureRegion skullUp, skullDown, column;

    public static Sound dead;

    public static void load()
    {
        texture = new Texture(Gdx.files.internal("textures/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

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
    }

    public static void dispose()
    {
        // Dispose texture when we are finished
        texture.dispose();
    }
}
