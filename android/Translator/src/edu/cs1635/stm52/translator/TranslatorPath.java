package edu.cs1635.stm52.translator;

import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

/**
 * 
 * @author sean
 * TranslatorPath has two main added functionalities:
 * 1. To make it so each path has an individual color to draw.
 * 2. When transmitting data, we can normalize it before sending it, so we store
 * 		an extra set of points to do so.
 */
public class TranslatorPath extends Path {
	private ArrayList<Integer> points;
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
		points = new ArrayList<Integer>();
		normalizedWidth = NORMALIZED_WIDTH / width_;
		normalizedHeight = NORMALIZED_HEIGHT / height_;
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
		
		points.add((int) (dx * normalizedWidth));
		points.add((int) (dy * normalizedHeight));
	}
	
	/**
	 * setColor: Simple method for changing the color.
	 * @param newColor an integer constant you want to set the color to.
	 */
	public void setColor(int newColor){
		color = newColor;
	}
	
	public ArrayList<Integer> encodedPoints(){
		return points;
	}
	
	/**
	 * draw: Each path now has an individual color, so the canvas must be
	 * 		brought to the path, like so. Nondestructive to the paint object.
	 * @param canvas the canvas you want to draw to.
	 */
	public void draw(Canvas canvas, Paint paint){
		int savedColor = paint.getColor();

		paint.setColor(color);
		canvas.drawPath(this, paint);
		paint.setColor(savedColor);
	}
}
