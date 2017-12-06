package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.scenes.Hud;

import static com.mygdx.game.MainGame.*;


public class GameOverScreen implements Screen{
    private MainGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Music music;
    private Texture backGround;
    private Image BGI;
    private ClickListener clicked;
    private Stage stage;
    public GameOverScreen(final MainGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(404, 228, camera);
        font = new BitmapFont();
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        backGround = new Texture("GameOver.png");
        BGI = new Image(backGround);
        BGI.setSize(backGround.getWidth() / 2, backGround.getHeight() / 2);
        BGI.setPosition(350, 0);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Skin MenuButtons = new Skin(Gdx.files.internal("ButtonsMenu.json"));
        Button StartGame = new TextButton("Start Game", MenuButtons);
        StartGame.setSize(150, 50);
        StartGame.setPosition(150, 305);
        StartGame.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playScreen = new PlayScreen(game);
                game.setScreen(playScreen);
                playScreen.hud = new Hud(batch);
                camera.update();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button loadGame = new TextButton("Load Game", MenuButtons);
        loadGame.setSize(150, 50);
        loadGame.setPosition(150, 205);


        Button exitGame = new TextButton("Exit Game", MenuButtons);
        exitGame.setSize(150, 50);
        exitGame.setPosition(150, 105);
        exitGame.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               System.exit(0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        stage.addActor(BGI);
        stage.addActor(StartGame);
        stage.addActor(loadGame);
        stage.addActor(exitGame);
        //stage.addActor(BGI);
        music = Gdx.audio.newMusic(Gdx.files.internal("Greta_Sting.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }
    @Override
    public void show() {

    }

    private void handleInput(float delta){
   if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.exit(0);
        }

    }


    private void update(float delta){
        handleInput(delta);
        camera.update();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
