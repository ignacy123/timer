package com.example.timer.businesslogic.timeprovider;

/**
 * Created by ignacy on 15.01.18.
 */

class ThreeByThreeMove {
	private ThreeByThreeMoves move;

	public ThreeByThreeMoves getMove() {
		return move;
	}

	public void setMove(ThreeByThreeMoves move) {
		this.move = move;
	}



	public enum ThreeByThreeMoves {
		R, L, B, F, U, D;
	}
}
