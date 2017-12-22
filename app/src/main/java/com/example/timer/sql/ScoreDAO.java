package com.example.timer.sql;

/**
 * Created by ignacy on 22.12.17.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.timer.model.Score;

import java.util.List;

/**
 * Created by ignacy on 21.09.17.
 */
@Dao
public interface ScoreDAO {

	@Query("DELETE FROM score")
	void clear();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void persist(Score score);

	@Query("SELECT * FROM score")
	LiveData<List<Score>> fetch();
}
