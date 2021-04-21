package com.greenmeows.jdiva.utils;

import com.greenmeows.jdiva.constants.NoteSettings;

public class Vector2f {
	
	private float x;
	private float y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getMagnitude(Vector2f vector) {
		float x1 = this.x;
		float x2 = vector.getX();
		float y1 = this.y;
		float y2 = vector.getY();
		
		return (float) Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	
	public float getGradient(Vector2f vector) {
		float x1 = this.x;
		float x2 = vector.getX();
		float y1 = this.y;
		float y2 = vector.getY();
		float dy = y2-y1;
		float dx = x2-x1;
		float gradient = dy/dx;
		return gradient;
	}
	
	public float getTime(Vector2f vector, float scrollspeed, float start) {
		float factor = scrollspeed/NoteSettings.DEFAULT_SPEED;
		float scrolltimeinseconds  =  (float) (Math.pow(NoteSettings.DEFAULT_SPEED/start, -1)/factor);
		float distance = getMagnitude(vector);
		float time = distance/start*scrolltimeinseconds;
		float ms = time*1000;
		return ms;
	}
	
	public float getX() {
		if(x == Float.NaN) {
			x = 0;
		}
		return x;
	}
	
	public float getY() {
		if(y == Float.NaN) {
			y = 0;
		}
		return y;
	}
	
	public void setX(float x) {
		if(x == Float.NaN) {
			x = 0;
		}
		this.x = x;
	}
	
	public void setY(float y) {
		if(y == Float.NaN) {
			y = 0;
		}
		this.y = y;
	}
}
