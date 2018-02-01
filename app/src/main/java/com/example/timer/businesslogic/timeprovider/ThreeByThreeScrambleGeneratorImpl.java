package com.example.timer.businesslogic.timeprovider;

import com.google.common.collect.ImmutableMap;

import java.util.Random;

import static com.example.timer.businesslogic.timeprovider.ThreeByThreeMove.B;
import static com.example.timer.businesslogic.timeprovider.ThreeByThreeMove.D;
import static com.example.timer.businesslogic.timeprovider.ThreeByThreeMove.F;
import static com.example.timer.businesslogic.timeprovider.ThreeByThreeMove.L;
import static com.example.timer.businesslogic.timeprovider.ThreeByThreeMove.R;
import static com.example.timer.businesslogic.timeprovider.ThreeByThreeMove.U;

/**
 * Created by ignacy on 15.01.18.
 */

public class ThreeByThreeScrambleGeneratorImpl implements ThreeByThreeScrambleGenerator {

	public static final ImmutableMap<Integer, ThreeByThreeMove> MAP = ImmutableMap.<Integer, ThreeByThreeMove>builder().put(0, R)
			.put(1, L)
			.put(2, F)
			.put(3, B)
			.put(4, D)
			.put(5, U)
			.build();

	public String generate() {
		String generatedScramble = "";
		StringBuilder builder = new StringBuilder(25);
		Random random = new Random(System.currentTimeMillis());
		int previousInt = 0;
		for (int i = 0; i < 25; i++) {
			int nextInt;
			do {
				nextInt = random.nextInt(6);
			} while (nextInt == previousInt);

			ThreeByThreeMove move = MAP.get(nextInt);
			builder.append(move);
			builder.append(generateSuffix());
			previousInt = nextInt;
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
