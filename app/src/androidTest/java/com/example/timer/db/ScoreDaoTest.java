package com.example.timer.db;

import android.support.test.runner.AndroidJUnit4;

import com.example.timer.model.Score;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.example.timer.util.LiveDataTestUtil.getValue;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by ignacy on 09.11.17.
 */
@RunWith(AndroidJUnit4.class)
public class ScoreDaoTest extends DbTest {

	@Test
	public void fetchesUser() throws InterruptedException {
		List<Score> scores = getValue(db.scoreDao()
				.fetch());
		assertTrue(scores.isEmpty());
	}

	@Test
	public void insertsAndFetchesScore() throws InterruptedException {
		Score score = new Score(0, "wddwdwdw", 6000, "");
		db.scoreDao()
				.persist(score);
		List<Score> scoreFromDb = getValue(db.scoreDao()
				.fetch());
		Score scoreDb = scoreFromDb.get(0);
		scoreDb.setId(score.getId());
		assertThat(scoreDb, is(score));
	}

	@Test
	public void insertsAndFetchesTwoIdenticalScores() throws InterruptedException {
		Score score = new Score(0, "wddwdwdw", 6000, "");
		db.scoreDao()
				.persist(score);
		db.scoreDao()
				.persist(score);
		List<Score> scoreFromDb = getValue(db.scoreDao()
				.fetch());
		assertTrue(scoreFromDb.size() == 2);
	}

	@Test
	public void clearsDatabase() throws InterruptedException {

		Score score = new Score(0, "wddwdwdw", 6000, "");
		db.scoreDao()
				.persist(score);
		db.scoreDao()
				.clear();

		List<Score> scoreFromDb = getValue(db.scoreDao()
				.fetch());
		assertTrue(scoreFromDb.isEmpty());

	}

}
