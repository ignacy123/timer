package com.example.timer.sql;

/**
 * Created by ignacy on 22.12.17.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.timer.model.Statistics;

import java.util.List;

/**
 * Created by ignacy on 21.09.17.
 */
@Dao
public interface StatisticsDao {

	@Query("DELETE FROM statistics")
	void clear();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void persist(Statistics statistics);

	@Query("SELECT * FROM statistics")
	LiveData<List<Statistics>> fetch();

	@Delete
	void delete(Statistics statistics);

	@Query("SELECT MIN(single) FROM statistics")
	LiveData<Long> getBestSingle();

	@Query("SELECT MIN(mo3) FROM statistics")
	LiveData<Long> getBestMo3();

	@Query("SELECT MIN(avg5) FROM statistics")
	LiveData<Long> getBestAvg5();

	@Query("SELECT MIN(avg12) FROM statistics")
	LiveData<Long> getBestAvg12();

	@Query("SELECT MIN(avg50) FROM statistics")
	LiveData<Long> getBestAvg50();

	@Query("SELECT MIN(avg100) FROM statistics")
	LiveData<Long> getBestAvg100();

}
