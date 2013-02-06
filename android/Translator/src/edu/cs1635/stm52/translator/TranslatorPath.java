package edu.cs1635.stm52.translator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

public class TranslatorPath extends Path {
	private ArrayList<Point> points;
	float normalizedWidth;
	float normalizedHeight;
	int color;
	/**
	 * TranslatorPath
	 * 
	 * @param width_ is the width of the view to normalize the points
	 * @param height_ is the height of the view to normalize the points
	 */
	public TranslatorPath(int width_, int height_){
		super();
		final float NORMALIZED_WIDTH = 254.0f;
		final float NORMALIZED_HEIGHT = 254.0f;
		points = new ArrayList<Point>();
		normalizedWidth = width_ / NORMALIZED_WIDTH;
		normalizedHeight = height_ / NORMALIZED_HEIGHT;
		color = Color.BLACK;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.graphics.Path#lineTo(float, float)
	 * 
	 * Adds additional functionality to map normalized values.
	 */
	public void lineTo(float dx, float dy){
		super.lineTo(dx, dy);
		points.add(new Point((int)(dx * normalizedWidth), (int)(dy * normalizedHeight)));
	}
	
	public void setColor(int newColor){
		color = newColor;
	}
	
	/**
	 * 
	 * @param canvas the canvas you want to draw to.
	 */
	public void draw(Canvas canvas, Paint paint){
		int savedColor = paint.getColor();
		paint.setColor(color);
		canvas.drawPath(this, paint);
		paint.setColor(savedColor);
	}
}
