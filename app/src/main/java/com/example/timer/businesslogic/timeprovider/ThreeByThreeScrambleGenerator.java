package com.example.timer.businesslogic.timeprovider;

import java.util.Random;

/**
 * Created by ignacy on 15.01.18.
 */

public class ThreeByThreeScrambleGenerator {

	ThreeByThreeMove.ThreeByThreeMoves r = ThreeByThreeMove.ThreeByThreeMoves.R;
	ThreeByThreeMove.ThreeByThreeMoves l = ThreeByThreeMove.ThreeByThreeMoves.L;
	ThreeByThreeMove.ThreeByThreeMoves u = ThreeByThreeMove.ThreeByThreeMoves.U;
	ThreeByThreeMove.ThreeByThreeMoves d = ThreeByThreeMove.ThreeByThreeMoves.D;
	ThreeByThreeMove.ThreeByThreeMoves b = ThreeByThreeMove.ThreeByThreeMoves.B;
	ThreeByThreeMove.ThreeByThreeMoves f = ThreeByThreeMove.ThreeByThreeMoves.F;

	public String generate() {
		String generatedScramble = "";
		StringBuilder builder = new StringBuilder(25);
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 25; i++) {
			switch (random.nextInt(6)) {
				case 1:
					builder.append(r);
					builder.append(generateSuffix());
					break;
				case 2:
					builder.append(l);
					builder.append(generateSuffix());
					break;
				case 3:
					builder.append(u);
					builder.append(generateSuffix());
					break;
				case 4:
					builder.append(d);
					builder.append(generateSuffix());
					break;
				case 5:
					builder.append(b);
					builder.append(generateSuffix());
					break;
				case 6:
					builder.append(f);
					builder.append(generateSuffix());
					break;
			}
		}
		generatedScramble = builder.toString();
		return generatedScramble;
	}

	private String generateSuffix() {
		Random random = new Random(System.currentTimeMillis());
		switch (random.nextInt(3)) {
			case 1:
				return "";
			case 2:
				return "'";
			case 3:
				return "2";
		}
		return "";
	}

}
