package com.example.timer.model;

import android.graphics.Color;

/**
 * Created by ignacy on 24.05.18.
 */

public enum Colours {
	WHITE("#FFFFFF"),
	YELLOW("#FFFF00"),
	RED("#FF0000"),
	ORANGE("#FFA500"),
	GREEN("#008000"),
	BLUE("#0000FF");
	int colour;

	Colours(String colour) {
		this.colour = Color.parseColor(colour);
	}

	public int getColour() {
		return colour;
	}
}
