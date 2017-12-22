package com.example.timer.db;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.example.timer.sql.AppDatabase;

import org.junit.After;
import org.junit.Before;

/**
 * Created by ignacy on 09.11.17.
 */

abstract public class DbTest {
	protected AppDatabase db;

	@Before
	public void initDb() {
		db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
				AppDatabase.class).build();
	}

	@After
	public void closeDb() {
		db.close();
	}
}
