package com.example.timer.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.timer.model.Score;
import com.example.timer.model.Statistics;

/**
 * Created by ignacy on 22.12.17.
 */
@Database(entities = { Score.class, Statistics.class }, version = 2)
public abstract class AppDatabase extends RoomDatabase {

	private static AppDatabase instance;

	public abstract ScoreDao scoreDao();

	public abstract StatisticsDao statisticsDao();

}

