package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.timer.sql.AppDatabase;
import com.example.timer.sql.ScoreDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = { ViewModelModule.class, BusinessLogicModule.class })
class AppModule {

	@Singleton
	@Provides
	AppDatabase provideDb(Application app) {
		return Room.databaseBuilder(app, AppDatabase.class, "timer.db")
				.build();
	}

	@Singleton
	@Provides
	ScoreDAO provideScoreDAO(AppDatabase db) {
		return db.scoreDao();
	}

}