package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGenerator;
import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by ignacy on 30.01.18.
 */

public class ScrambleGeneratorTest {

	@Test
	public void providesScrambleOfProperLength() {
		ThreeByThreeScrambleGenerator generator = new ThreeByThreeScrambleGeneratorImpl();
		String scramble = generator.generate();
		assertThat(getAmountOfMoves(scramble)).isEqualTo(25);
	}

	@Test
	public void providesScrambleWithNoInvalidRepetitions() {
		ThreeByThreeScrambleGenerator generator = new ThreeByThreeScrambleGeneratorImpl();
		String scramble = generator.generate();
		assertThat(hasInvalidRepetitions(scramble)).isFalse();
	}

	private boolean hasInvalidRepetitions(String scramble) {
		List<String> chars = new ArrayList<>();
		for (int i = 0; i < scramble.length(); i++) {
			chars.add(scramble.substring(i, i + 1));
		}
		String previousMove = "";
		for (String letter : chars) {
			if (isMove(letter)) {
				if (previousMove.equals(letter)) {
					return true;
				}
				previousMove = letter;
			}
		}
		return false;

	}

	private boolean isMove(String letter) {

		return Arrays.asList("R", "L", "B", "D", "U", "F")
				.contains(letter);
	}

	private int getAmountOfMoves(String scramble) {
		List<String> chars = new ArrayList<String>();
		for (int i = 0; i < scramble.length(); i++) {
			chars.add(scramble.substring(i, i + 1));
		}
		int moveCounter = 0;
		for (String letter : chars) {
			if (isMove(letter)) {
				moveCounter++;
			}
		}
		return moveCounter;
	}
}
