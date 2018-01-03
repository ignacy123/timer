package com.example.timer.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ignacy on 22.12.17.
 */
@Entity
public class Score {

	private final String scramble;
	private final long time;
	@PrimaryKey(autoGenerate = true)
	private long id;

	public Score(String scramble, long time) {
		this.scramble = scramble;
		this.time = time;
	}

	public String getScramble() {
		return scramble;
	}

	public long getTime() {
		return time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Score score = (Score) o;

		if (time != score.time) {
			return false;
		}
		if (id != score.id) {
			return false;
		}
		return scramble != null ? scramble.equals(score.scramble) : score.scramble == null;
	}

	@Override
	public int hashCode() {
		int result = scramble != null ? scramble.hashCode() : 0;
		result = 31 * result + (int) (time ^ (time >>> 32));
		result = 31 * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Score{" + "scramble='" + scramble + '\'' + ", time=" + time + ", id=" + id + '}';
	}
}