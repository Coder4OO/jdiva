package com.greenmeows.jdiva;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.greenmeows.jdiva.song.Song;
import com.greenmeows.jdiva.song.Stage;
import com.greenmeows.jdiva.utils.FileUtils;

public class Game extends ApplicationAdapter {
	static SpriteBatch batch;
	Song song;
	Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		song = new Song("MacaronMoon");
		stage = new Stage(song);
		stage.start();
	}
	
	public static SpriteBatch getBatch() {
		return batch;
	}
	
	private void draw() {
		batch.begin();
		stage.draw();
		batch.end();
	}
	
	private void logic() {
		stage.logic();
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
	}
}
