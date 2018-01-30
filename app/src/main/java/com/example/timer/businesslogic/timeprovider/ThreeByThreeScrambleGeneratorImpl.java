package com.example.timer.businesslogic.timeprovider;

import java.util.Random;

/**
 * Created by ignacy on 15.01.18.
 */

public class ThreeByThreeScrambleGeneratorImpl implements ThreeByThreeScrambleGenerator {

	public String generate() {
		String generatedScramble = "";
		StringBuilder builder = new StringBuilder(25);
		ThreeByThreeMove lastMove = ThreeByThreeMove.R;
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 25; i++) {
			switch (random.nextInt(6)) {
				case 0:
					if (lastMove == ThreeByThreeMove.R) {
						if (i != 0) {
							i--;
						}
						break;
					}
					builder.append(ThreeByThreeMove.R);
					builder.append(generateSuffix());
					lastMove = ThreeByThreeMove.R;
					break;
				case 1:
					if (lastMove == ThreeByThreeMove.F) {
						i--;
						break;
					}
					builder.append(ThreeByThreeMove.F);
					builder.append(generateSuffix());
					lastMove = ThreeByThreeMove.F;
					break;
				case 2:
					if (lastMove == ThreeByThreeMove.L) {
						i--;
						break;
					}
					builder.append(ThreeByThreeMove.L);
					builder.append(generateSuffix());
					lastMove = ThreeByThreeMove.L;
					break;
				case 3:
					if (lastMove == ThreeByThreeMove.U) {
						i--;
						break;
					}
					builder.append(ThreeByThreeMove.U);
					builder.append(generateSuffix());
					lastMove = ThreeByThreeMove.U;
					break;
				case 4:
					if (lastMove == ThreeByThreeMove.D) {
						i--;
						break;
					}
					builder.append(ThreeByThreeMove.D);
					builder.append(generateSuffix());

					lastMove = ThreeByThreeMove.D;
					break;
				case 5:
					if (lastMove == ThreeByThreeMove.B) {
						i--;
						break;
					}
					builder.append(ThreeByThreeMove.B);
					builder.append(generateSuffix());

					lastMove = ThreeByThreeMove.B;
					break;
			}
		}
		generatedScramble = builder.toString();
		return generatedScramble;
	}

	private String generateSuffix() {
		Random random = new Random();
		switch (random.nextInt(3)) {
			case 0:
				return "";
			case 1:
				return "2";
			case 2:
				return "'";
		}
		return "";
	}

}
