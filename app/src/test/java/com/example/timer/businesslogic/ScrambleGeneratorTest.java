package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGenerator;
import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ignacy on 30.01.18.
 */

public class ScrambleGeneratorTest {

	@Test
	public void providesScrambleOfProperLength() {
		ThreeByThreeScrambleGenerator generator = new ThreeByThreeScrambleGeneratorImpl();
		String scramble = generator.generate();
		assertThat(getAmountOfMoves(scramble), is(25));
	}

	@Test
	public void providesScrambleWithNoInvalidRepetitions() {
		ThreeByThreeScrambleGenerator generator = new ThreeByThreeScrambleGeneratorImpl();
		String scramble = generator.generate();
		assertThat(hasInvalidRepetitions(scramble), is(false));
	}

	private boolean hasInvalidRepetitions(String scramble) {
		List<String> chars = new ArrayList<String>();
		for (int i = 0; i < scramble.length(); i++) {
			chars.add(scramble.substring(i, i + 1));
		}
		String previousMove = "";
		for (String letter : chars) {
			if (isMove(letter)) {
				if (previousMove.equals(letter)) {
					return true;
				}
				if (previousMove.equals("")) {
					previousMove = letter;
				}
				previousMove = letter;
			}
		}
		return false;

	}

	private boolean isMove(String letter) {

		//TODO REVIEW: https://pl.wikipedia.org/wiki/DRY
		if (letter.equals("R")) {
			return true;
		}
		if (letter.equals("L")) {
			return true;

		}
		if (letter.equals("U")) {
			return true;
		}
		if (letter.equals("D")) {
			return true;

		}
		if (letter.equals("B")) {
			return true;

		}
		if (letter.equals("F")) {
			return true;

		}
		return false;
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
