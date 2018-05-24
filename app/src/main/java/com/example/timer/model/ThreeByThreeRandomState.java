package com.example.timer.model;

import java.util.Arrays;

/**
 * Created by ignacy on 24.05.18.
 */

public class ThreeByThreeRandomState {

	Colours[] colours = new Colours[54];

	public Colours[] getColours() {
		return colours;
	}

	public void setColours(Colours[] colours) {
		this.colours = colours;
	}

	public Colours[] getUFace() {
		return Arrays.copyOfRange(colours, 0, 9);
	}

	public Colours[] getFFace() {
		return Arrays.copyOfRange(colours, 9, 18);
	}

	public Colours[] getLFace() {
		return Arrays.copyOfRange(colours, 18, 27);
	}

	public Colours[] getRFace() {
		return Arrays.copyOfRange(colours, 27, 36);
	}

	public Colours[] getBFace() {
		return Arrays.copyOfRange(colours, 36, 45);
	}

	public Colours[] getDFace() {
		return Arrays.copyOfRange(colours, 45, 54);
	}
}
