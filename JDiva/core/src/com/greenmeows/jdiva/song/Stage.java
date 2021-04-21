package com.greenmeows.jdiva.song;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.greenmeows.jdiva.Game;

public class Stage {
	
	private Song song;
	private ArrayList<Note> notes = new ArrayList<Note>();
	private ArrayList<Note> queuednotes = new ArrayList<Note>();
	private ArrayList<Integer> skips = new ArrayList<Integer>();
	private Sprite background = new Sprite();
	private Music music;
	private long starttime;
	private float spawndelay;
	private boolean started = false;
	private float counter;
	private int currentindex;
	
	private void addnotes() {
		counter += 1000*Gdx.graphics.getDeltaTime();
		if(counter >= spawndelay) {
			try {
				counter = 0;
				notes.add(queuednotes.get(0));
				queuednotes.remove(0);				}
			catch(Exception e) {
			
			}
		}			
		currentindex++;
	}
	
	
	private void antilag() {
		Iterator<Note> iter = notes.iterator();
		while(iter.hasNext()) {
			if(iter.next().isJudgementDrawn()) {
				iter.remove();
			}
		}
	}
	
	public Stage(Song song) {
		this.song = song;
		this.queuednotes = song.getNotes();
		this.skips = song.getSkips();
		this.background = song.getBackground();
		this.music = song.getMusic();
		this.spawndelay = song.getSpawndelay()*1000;
	}
	
	public void start() {
		if(!started) {
			starttime = Instant.now().toEpochMilli();
			started = true;
			music.play();
		}
	}
	
	public void stop() {
		if(started) {
			music.stop();
		}
	}
	
	public void logic() {
		addnotes();
		antilag();
		for(int i = 0; i < notes.size(); i++) {
			notes.get(i).logic();
		}
	}
	
	public void draw() {
		this.background.draw(Game.getBatch());
		for(int i = 0; i < notes.size(); i++) {
			notes.get(i).draw();
		}
	}
	
}
