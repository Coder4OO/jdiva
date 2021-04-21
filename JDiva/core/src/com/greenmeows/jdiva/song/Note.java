package com.greenmeows.jdiva.song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.greenmeows.jdiva.Game;
import com.greenmeows.jdiva.constants.Constants;
import com.greenmeows.jdiva.constants.Judgements;
import com.greenmeows.jdiva.constants.NoteSettings;

public class Note {
	
	private Vector2 pos;
	private Vector2 coverpos;
	private Sprite hitSprite;
	private Sprite coverSprite;
	private float notespeed;
	private int keybind;
	private float bpm;
	private float xslope;
	private float yslope;
	private float slopelength;
	private float xdir;
	private float ydir;
	private float counter = 0;
	private BitmapFont judgement;
	private int judge;
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	private boolean ishit = false;
	private boolean judgementdrawn = false;
	private boolean haspassedzero = false;
	
	private float calculateSpeed() {
		float factor = bpm/100;
		float speed = NoteSettings.DEFAULT_SPEED*factor;
		return speed;
	}
	
	
	public Note(float x, float y, float coverx, float covery, float bpm, String keybind) {
		Sprite hit = null;
		Sprite cover = null;
		this.bpm = bpm;
		switch(keybind) {
		case "triangle":
			this.keybind = Keys.Q;
			hit = new Sprite(new Texture(Gdx.files.internal("images\\trianglehit.png")));
			cover = new Sprite(new Texture(Gdx.files.internal("images\\trianglecover.png")));
			break;
		case "square":
			this.keybind = Keys.W;
			hit = new Sprite(new Texture(Gdx.files.internal("images\\squarehit.png")));
			cover = new Sprite(new Texture(Gdx.files.internal("images\\squarecover.png")));
			break;
		case "cross":
			this.keybind = Keys.O;
			hit = new Sprite(new Texture(Gdx.files.internal("images\\crosshit.png")));
			cover = new Sprite(new Texture(Gdx.files.internal("images\\crosscover.png")));
			break;
		case "circle":
			this.keybind = Keys.P;
			hit = new Sprite(new Texture(Gdx.files.internal("images\\circlehit.png")));
			cover = new Sprite(new Texture(Gdx.files.internal("images\\circlecover.png")));
			break;
		}
		x *= Constants.WIDTH;
		y *= Constants.HEIGHT;
		pos = new Vector2(x, y);
		coverx *= Constants.WIDTH;
		covery *= Constants.HEIGHT;
		coverpos = new Vector2(coverx, covery);
		hitSprite = hit;
		coverSprite = cover;
		this.notespeed = calculateSpeed();
		hitSprite.setX(pos.x);
		hitSprite.setY(pos.y);
		coverSprite.setSize(100, 100);
		hitSprite.setSize(100, 100);
		xslope = (float) (pos.x-coverpos.x);
		yslope = (float) (pos.y-coverpos.y);
		slopelength = ((float) Math.sqrt((xslope*xslope)+(yslope*yslope)));
		xdir = xslope/slopelength;
		ydir = yslope/slopelength;
		parameter.size = 25;
		parameter.shadowColor = Color.BLACK;
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 5;
		judgement = Game.getGenerator().generateFont(parameter);
	}
	
	private void drawJudgement() {
		String text = "";
		switch(judge) {
		case Judgements.SCORE_COOL:
			text = "COOL";
			judgement.setColor(Color.GOLD);
			break;
		case Judgements.SCORE_GREAT:
			text = "GREAT";
			judgement.setColor(Color.GRAY);
			break;
		case Judgements.SCORE_OKAY:
			text = "OKAY";
			judgement.setColor(Color.BROWN);
			break;
		case Judgements.SCORE_SAFE:
			text = "SAFE";
			judgement.setColor(Color.GREEN);
			break;
		}
		judgement.draw(Game.getBatch(), new StringBuffer(text), pos.x, pos.y);
	}
	
	
	private int detect_hit(float time) {
		if(time <= Judgements.TIMING_COOL) {
			ishit = true;
			return Judgements.SCORE_COOL;
		}
		else if(time > Judgements.TIMING_COOL && time <= Judgements.TIMING_GREAT) {
			ishit = true;
			return Judgements.SCORE_GREAT;
		}
		else if(time > Judgements.TIMING_GREAT && time <= Judgements.TIMING_OKAY) {
			ishit = true;
			return Judgements.SCORE_OKAY;
		}
		else if(time > Judgements.TIMING_OKAY && time <= Judgements.TIMING_SAFE) {
			ishit = true;
			return Judgements.SCORE_SAFE;
		}
		else {
			System.out.println("Hacking");
			System.exit(0);
			return 0;
		}
	}
	
	
	
	public void logic() {
		if(!ishit) {
			coverpos.x += xdir*notespeed*Gdx.graphics.getDeltaTime();
			coverpos.y += ydir*notespeed*Gdx.graphics.getDeltaTime();
			float distance = coverpos.dst(pos);
			float factor = bpm/100;
			float constant = (float) (Math.pow(NoteSettings.DEFAULT_SPEED, -1)*Constants.WIDTH)/factor;
			float time = constant*distance/Constants.WIDTH;
			time *= 1000;
			if(time < 10) {
				haspassedzero = true;
			}
			boolean canhit = time <= Judgements.TIMING_SAFE;
			if(time > Judgements.TIMING_SAFE*7.5 && haspassedzero) {
				ishit = true;
			}
			if(canhit) {
				if(Gdx.input.isKeyJustPressed(keybind)) {
					judge = detect_hit(time);
					switch(judge) {
					case Judgements.SCORE_COOL:
						System.out.println("COOL");
						break;
					case Judgements.SCORE_GREAT:
						System.out.println("GREAT");
						break;
					case Judgements.SCORE_OKAY:
						System.out.println("OKAY");
						break;
					case Judgements.SCORE_SAFE:
						System.out.println("SAFE");
						break;
					default:
						System.out.println("MISS");
						break;
					}
				}
			}
		}
	}
	
	public boolean isHit(){
		return ishit;
	}
	
	
	
	public void setHit(boolean ishit) {
		this.ishit = ishit;
	}
	
	public boolean isJudgementDrawn() {
		return judgementdrawn;
	}
	
	public void draw() {
		if(!ishit) {
			coverSprite.setX(coverpos.x);
			coverSprite.setY(coverpos.y);
			hitSprite.draw(Game.getBatch());
			coverSprite.draw(Game.getBatch());
		}
		float factor = bpm/100*2;
		float drawtime = 1;
		if(judge > 0) {
			counter += Gdx.graphics.getDeltaTime();
			if(counter <= drawtime) {
				drawJudgement();
			}
			else {
				judgementdrawn = true;
			}
		}
	}
	
}
