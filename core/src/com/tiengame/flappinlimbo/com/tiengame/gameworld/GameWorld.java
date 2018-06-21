package com.tiengame.flappinlimbo.com.tiengame.gameworld;

import com.badlogic.gdx.Gdx;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Bird;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.ScrollHandler;
import com.tiengame.flappinlimbo.com.tiengame.helpers.AssetsLoader;

// Responsible for updating what happens in the gameworld before being rendered to the screen
public class GameWorld {

    private Bird bird;
    private ScrollHandler scrollHandler;

    private boolean isAlive = true;

    public GameWorld(int midPointY){

        bird = new Bird(33, midPointY - 5, 17, 12);

        // The grass starts at 66 pixels below midPointY
        scrollHandler = new ScrollHandler(midPointY + 66);
    }
    public void update(float delta){
        bird.update(delta);
        scrollHandler.update(delta);

        if(isAlive && scrollHandler.collides(bird))
        {
            // Clean up on game over
            scrollHandler.stop();
            AssetsLoader.dead.play();
            isAlive = false;
        }
        Gdx.app.log("GameWorld", "update");
    }

    public Bird getBird() {
        return bird;
    }
    public ScrollHandler getScrollHandler() {return scrollHandler;}
}
