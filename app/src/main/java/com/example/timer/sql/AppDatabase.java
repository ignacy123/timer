package com.example.timer.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.timer.model.Score;

/**
 * Created by ignacy on 22.12.17.
 */
@Database(entities = { Score.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

	private static AppDatabase instance;

	public static final AppDatabase getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context, AppDatabase.class, "timer-database")
					.build();
		}
		return instance;
	}

	public abstract ScoreDAO scoreDao();
}

