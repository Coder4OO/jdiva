package com.greenmeows.jdiva;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.greenmeows.jdiva.constants.Constants;
import com.greenmeows.jdiva.song.Song;
import com.greenmeows.jdiva.utils.FileUtils;

public class MainMenu {
	
	private ArrayList<Song> songs = FileUtils.getAllSongs();
	private BitmapFont titlefont;
	private int currentoption;
	private BitmapFont optionfont;
	private Color optioncolor;
	
	public MainMenu(String titlefontname, Color titlecolor, int titletextsize, String optionfontname, Color optioncolor, int optiontextsize) {
		FreeTypeFontGenerator titlegenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\"+titlefontname));
		FreeTypeFontParameter titleparam = new FreeTypeFontParameter();
		titleparam.size = titletextsize;
		this.titlefont = titlegenerator.generateFont(titleparam);
		titlegenerator.dispose();
		titlefont.setColor(titlecolor);
		FreeTypeFontGenerator optiongenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\"+optionfontname));
		FreeTypeFontParameter optionparam = new FreeTypeFontParameter();
		optionparam.size = optiontextsize;
		this.optionfont = optiongenerator.generateFont(optionparam);
		this.optionfont.setColor(optioncolor);
		optiongenerator.dispose();
	}
	
	public void logic() {
		if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
			if(currentoption > 0) {
				currentoption--;
			}
			else {
				currentoption = songs.size()-1;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S)) {
			if(currentoption < songs.size()-1) {
				currentoption++;
			}
			else {
				currentoption = 0;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			Game.createStage(songs.get(currentoption));
			GameStates.CURRENTSTATE = GameStates.GAME;
		}
	}
	
	public void draw() {
		//create cyan background
		Gdx.gl.glClearColor(0, (float) 0.1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// draw title
		titlefont.draw(Game.getBatch(), new StringBuffer("SELECT YOUR SONG"), Constants.WIDTH/3+100, Constants.HEIGHT/10*9);
		//draw options
		for(Song song : songs) {
			String name = song.getName();
			int index = songs.indexOf(song);
			if(index == currentoption) {
				this.optionfont.setColor(Color.GOLD);
			}
			else {
				this.optionfont.setColor(optioncolor);
			}
			this.optionfont.draw(Game.getBatch(), new StringBuffer(name), Constants.WIDTH/2-50, Constants.HEIGHT/10*8-(150*index));
		}
	}
	
}
