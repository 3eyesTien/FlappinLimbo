package com.tiengame.flappinlimbo.com.tiengame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Bird;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Column;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.Grass;
import com.tiengame.flappinlimbo.com.tiengame.gameobjects.ScrollHandler;
import com.tiengame.flappinlimbo.com.tiengame.helpers.AssetsLoader;
import com.tiengame.flappinlimbo.com.tiengame.helpers.InputHandler;
import com.tiengame.flappinlimbo.com.tiengame.ui.SimpleButton;

import java.util.List;

// Responsible for handling rendering processes
public class GameRenderer {

    private int midPointY;
    private int gameHeight;

    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch sb;

    // Game objects
    private Bird bird;
    private ScrollHandler scrollHandler;
    private Grass frontGrass, backGrass;
    private Column column1, column2, column3;

    // Game assets
    private TextureRegion background, grass;
    private Animation<TextureRegion> birdAnimations;
    private TextureRegion midBird, downBird, upBird;
    private TextureRegion skullUp, skullDown, column;

    // Buttons
    private List<SimpleButton> menuButtons;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        gameWorld = world;

//        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);

        sb = new SpriteBatch();

        // Attach spritebatch sb to camera
        sb.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Call helpers to initialize instance variables
        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        bird = gameWorld.getBird();
        scrollHandler = gameWorld.getScrollHandler();
        frontGrass = scrollHandler.getFrontGrass();
        backGrass = scrollHandler.getBackGrass();
        column1 = scrollHandler.getColumn1();
        column2 = scrollHandler.getColumn2();
        column3 = scrollHandler.getColumn3();
    }

    private void initAssets() {
        this.background = AssetsLoader.background;
        this.grass = AssetsLoader.grass;
        this.birdAnimations = AssetsLoader.birdAnimations;
        this.midBird = AssetsLoader.bird;
        this.downBird = AssetsLoader.downBird;
        this.upBird = AssetsLoader.upBird;
        this.skullUp = AssetsLoader.skullUp;
        this.skullDown = AssetsLoader.skullDown;
        this.column = AssetsLoader.column;
    }

    private void drawGrass() {
        sb.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
        sb.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        sb.draw(skullUp, column1.getX() - 1, column1.getY() + column1.getHeight() - 14, 24, 14);
        sb.draw(skullDown, column1.getX() - 1, column1.getY() + column1.getHeight() + 45, 24, 14);

        sb.draw(skullUp, column2.getX() - 1, column2.getY() + column2.getHeight() - 14, 24, 14);
        sb.draw(skullDown, column2.getX() - 1, column2.getY() + column2.getHeight() + 45, 24, 14);

        sb.draw(skullUp, column3.getX() - 1, column3.getY() + column3.getHeight() - 14, 24, 14);
        sb.draw(skullDown, column3.getX() - 1, column3.getY() + column3.getHeight() + 45, 24, 14);
    }

    private void drawColumns() {
        sb.draw(column, column1.getX(), column1.getY(), column1.getWidth(), column1.getHeight());
        sb.draw(column, column1.getX(), column1.getY() + column1.getHeight() + 45, column1.getWidth(), midPointY + 66 - (column1.getHeight() + 45));

        sb.draw(column, column2.getX(), column2.getY(), column2.getWidth(), column2.getHeight());
        sb.draw(column, column2.getX(), column2.getY() + column2.getHeight() + 45, column2.getWidth(), midPointY + 66 - (column2.getHeight() + 45));

        sb.draw(column, column3.getX(), column3.getY(), column3.getWidth(), column3.getHeight());
        sb.draw(column, column3.getX(), column3.getY() + column3.getHeight() + 45, column3.getWidth(), midPointY + 66 - (column3.getHeight() + 45));
    }

    private void drawCenteredBird(float runTime) {
        sb.draw(birdAnimations.getKeyFrame(runTime), 59, bird.getY() - 15, bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
    }

    private void drawBird(float runTime) {
        if (bird.shouldNotFlap()) {
            sb.draw(midBird, bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        } else {
            sb.draw(birdAnimations.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }
    }

    private void drawMenuUI() {
        sb.draw(AssetsLoader.flLogo, 136 / 2 - 56, midPointY - 50, AssetsLoader.flLogo.getRegionWidth() / 1.2f, AssetsLoader.flLogo.getRegionHeight() / 1.2f);

        for (SimpleButton button : menuButtons) {
            button.draw(sb);
        }
    }

    private void drawScore()
    {
        int length = ("" + gameWorld.getScore()).length();
        AssetsLoader.fontShadow.draw(sb, "" + gameWorld.getScore(), 68 - (3 * length), midPointY - 82);
        AssetsLoader.font.draw(sb, "" + gameWorld.getScore(), 68 - (3 * length), midPointY - 83);
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin shapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // Tell the shapeRenderer to finish rendering
        // This must be done for each shape
        shapeRenderer.end();

        // Begin sprite batch
        sb.begin();

        // Disable transparency
        // This is good for performance.
        // Don't draw if transparency if the image doesn't need it
        sb.disableBlending();
        sb.draw(background, 0, midPointY + 23, 136, 43);

        // Draw Grass
        drawGrass();

        // Draw columns
        drawColumns();

        // The bird and skulls needs transparency
        // Enable transparency
        sb.enableBlending();

        // Draw skulls
        drawSkulls();

        if (gameWorld.isRunning())
        {
            drawBird(runTime);
            drawScore();
        }else if (gameWorld.isReady()) {
            drawBird(runTime);
            drawScore();
        } else if (gameWorld.isMenu())
        {
            drawCenteredBird(runTime);
            drawMenuUI();
        } else if (gameWorld.isGameOver())
        {
            drawBird(runTime);
            drawScore();
        }else if(gameWorld.isHighScore())
        {
            drawBird(runTime);
            drawScore();
        }
        sb.end();
    }
}