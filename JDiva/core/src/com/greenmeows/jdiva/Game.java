package com.greenmeows.jdiva;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.greenmeows.jdiva.song.Song;
import com.greenmeows.jdiva.song.Stage;

public class Game extends ApplicationAdapter {
	static SpriteBatch batch;
	Song song;
	static Stage stage;
	static FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;
	MainMenu mainmenu;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\Staubach.ttf"));
		song = new Song("MacaronMoon");
		stage = new Stage(song);
		mainmenu = new MainMenu("Staubach.ttf", Color.WHITE, 30, "Staubach.ttf", Color.WHITE, 15);
	}
	
	public static SpriteBatch getBatch() {
		return batch;
	}
	
	public static FreeTypeFontGenerator getGenerator() {
		return generator;
	}
	
	public static void createStage(Song song) {
		stage = new Stage(song);
		stage.start();
	}
	
	private void draw() {
		batch.begin();
		switch(GameStates.CURRENTSTATE) {
		case GameStates.MENU:
			mainmenu.draw();
			break;
		case GameStates.GAME:
			stage.draw();
			break;
		}
		batch.end();
	}
	
	private void logic() {
		switch(GameStates.CURRENTSTATE) {
		case GameStates.MENU:
			mainmenu.logic();
			break;
		case GameStates.GAME:
			stage.logic();
			break;
		}
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		draw();
		logic();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		generator.dispose();
	}
}
