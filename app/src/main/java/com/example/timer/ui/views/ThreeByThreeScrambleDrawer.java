package com.example.timer.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.timer.model.Colours;
import com.example.timer.model.ThreeByThreeRandomState;

import java.util.Random;

public class ThreeByThreeScrambleDrawer extends View {

	private static ThreeByThreeRandomState state = new ThreeByThreeRandomState();

	static {
		Colours[] colours = new Colours[54];
		for (int i = 0; i < 54; i++) {
			colours[i] = Colours.values()[new Random().nextInt(6)];
		}
		state.setColours(colours);
	}
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Rect rect = new Rect();

	private final static int PADDING = 10;
	private final static int INNER_PADDING = 4;

	public void DrawRandomState(ThreeByThreeRandomState state) {

		this.state = state;
	}

	public ThreeByThreeScrambleDrawer(Context context) {
		super(context);

		initializeParameters(context, null);
	}

	public ThreeByThreeScrambleDrawer(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		initializeParameters(context, attrs);
	}

	public ThreeByThreeScrambleDrawer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initializeParameters(context, attrs);
	}

	@TargetApi(21)
	public ThreeByThreeScrambleDrawer(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);

		initializeParameters(context, attrs);
	}

	private void initializeParameters(Context context, AttributeSet attributes) {
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Colours[] helper = state.getUFace();
		int helperColourIndex;
		super.onDraw(canvas);


		//		if (state != null) {
		final int height = canvas.getHeight() - 4 * PADDING;
		final int width = canvas.getWidth() - 5 * PADDING;
		final int squareSize = height / 3;
		final int innerSquareSize = (squareSize - 4 * INNER_PADDING) / 3;
		paint.setColor(Color.BLACK);
		for (int i = 0; i < 3; i++) {
			int left = squareSize + 2 * PADDING;
			int top = (i + 1) * PADDING + i * squareSize;
			rect.set(left, top, left + squareSize, top + squareSize);
			canvas.drawRect(rect, paint);
			if (i == 0) {
				helper = state.getUFace();
			}
			if (i == 1) {
				helper = state.getFFace();
			}
			if (i == 2) {
				helper = state.getDFace();
			}

			for (int j = 0; j < 3; j++) {
				helperColourIndex = j * 3;
				int innerTop = top + (j + 1) * INNER_PADDING + j * innerSquareSize;
				for (int k = 0; k < 3; k++) {
					paint.setColor(helper[helperColourIndex + k].getColour());
					int innerLeft = left + k * innerSquareSize + (k + 1) * INNER_PADDING;
					rect.set(innerLeft, innerTop, innerLeft + innerSquareSize, innerTop + innerSquareSize);
					canvas.drawRect(rect, paint);

				}
			}
		}
		for (int i = 0; i < 4; i++) {
			int left = (i + 1) * PADDING + i * squareSize;
			int top = squareSize + PADDING * 2;
			rect.set(left, top, left + squareSize, top + squareSize);
			canvas.drawRect(rect, paint);

			if (i == 0) {
				helper = state.getLFace();
			}
			if (i == 1) {
				helper = state.getFFace();
			}
			if (i == 2) {
				helper = state.getRFace();
			}
			if (i == 3) {
				helper = state.getBFace();
			}
			for (int j = 0; j < 3; j++) {
				helperColourIndex = j * 3;
				int innerTop = top + (j + 1) * INNER_PADDING + j * innerSquareSize;
				for (int k = 0; k < 3; k++) {
					paint.setColor(helper[helperColourIndex + k].getColour());
					int innerLeft = left + k * innerSquareSize + (k + 1) * INNER_PADDING;
					rect.set(innerLeft, innerTop, innerLeft + innerSquareSize, innerTop + innerSquareSize);
					canvas.drawRect(rect, paint);

				}
			}
		}
		//		}
	}
}