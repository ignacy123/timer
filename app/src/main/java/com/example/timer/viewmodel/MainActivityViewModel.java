package com.example.timer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;
import com.example.timer.model.Score;
import com.example.timer.sql.AppDatabase;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ignacy on 23.11.17.
 */

public class MainActivityViewModel extends AndroidViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();

	// REVIEW:
	// elementy zwiazane z liczeniem powinny byc w jakiejs osobnej klasy
	long time1;
	long time2;
	Timer t;
	public TimeProvider provider = new TimeProviderImpl();

	public MainActivityViewModel(@NonNull Application application) {
		super(application);
		scoreDAO = AppDatabase.getInstance(application.getApplicationContext())
				.scoreDao();
		executors = AppExecutors.getInstance();
	}

	public LiveData<String> getCounter() {
		return counter;
	}

	// REVIEW:
	// ViewModel musi zwracać typ LiveData, klienci (Activity/Fragment)
	// nie powinni mieć możliwości zawołania #setValue, które wystawia MutableLiveData
	public MutableLiveData<String> getScramble() {
		return scramble;
	}

	ScoreDAO scoreDAO;
	AppExecutors executors;

	public void startCounting() {
		time1 = provider.provideTime();

		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				counter.postValue(String.valueOf(provider.provideTime() - time1));
			}
		}, 0, 10);

	}

	public void stopCounting() {
		time2 = provider.provideTime();
		long timeDifference = time2 - time1;
		counter.postValue(String.valueOf(timeDifference));
		t.cancel();
		Score score = new Score("", timeDifference);
		executors.diskIO()
				.execute(() -> scoreDAO.persist(score));

	}

	public void setScramble() {
		scramble.postValue("Scramble will be displayed here.");
	}

}
