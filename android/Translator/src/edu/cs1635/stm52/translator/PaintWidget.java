package edu.cs1635.stm52.translator;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class PaintWidget extends View implements OnTouchListener {
	int color;
	TranslatorPath currentPath;
	Paint paint;
	ArrayList<TranslatorPath> paths;

	public PaintWidget(Context context) {
		super(context);
		color = Color.BLACK;
		this.setMinimumHeight(700);
		this.setMinimumWidth(800);
		paths = new ArrayList<TranslatorPath>();
		currentPath = new TranslatorPath(700, 800);
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(10); // set the size
		paint.setDither(true); // set the dither to true
		paint.setStyle(Paint.Style.STROKE); // set to STOKE
		paint.setStrokeCap(Paint.Cap.ROUND); // set the paint cap to round too														// when they join.
		paint.setAntiAlias(true); // set anti alias so it smooths

		this.setOnTouchListener(this);
	}

	public PaintWidget(Context context, AttributeSet attrs, int defStyle) {
		this(context);
	}

	public PaintWidget(Context context, AttributeSet attrs) {
		this(context);
	}

	public void setColor(int c) {
		color = c;
		invalidate();
	}
	public void clear(){
		paths.clear();
		currentPath = new TranslatorPath(700, 800);
		invalidate();
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(this.getSuggestedMinimumWidth(),
				this.getSuggestedMinimumHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.RED);
		for(TranslatorPath p: paths){
			p.draw(canvas, paint);
		}
		currentPath.draw(canvas, paint);
	}

	public boolean onTouch(View v, MotionEvent e) {
		
		if(e.getActionMasked() == MotionEvent.ACTION_DOWN){
			paths.add(currentPath);
			currentPath = new TranslatorPath(700, 800);
			currentPath.setColor(color);
			currentPath.moveTo(e.getX(), e.getY());
		}
		if (currentPath.isEmpty()) {
			currentPath.moveTo(e.getX(), e.getY());
		}

		currentPath.lineTo(e.getX(), e.getY());
		invalidate();
		return true;
	}
}
