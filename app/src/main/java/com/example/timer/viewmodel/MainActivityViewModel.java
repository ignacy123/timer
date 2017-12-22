package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ignacy on 23.11.17.
 */

public class MainActivityViewModel extends ViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();

	long time1;
	long time2;
	Timer t;

	public LiveData<String> getCounter() {
		return counter;
	}

	// REVIEW:
	// ViewModel musi zwracać typ LiveData, klienci (Activity/Fragment)
	// nie powinni mieć możliwości zawołania #setValue, które wystawia MutableLiveData
	public MutableLiveData<String> getScramble() {
		return scramble;
	}

	public void startCounting() {
		time1 = System.currentTimeMillis();

		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				counter.postValue(String.valueOf(System.currentTimeMillis() - time1));
			}
		}, 0, 10);

	}

	public void stopCounting() {
		time2 = System.currentTimeMillis();
		counter.postValue(String.valueOf((time2 - time1)));
		t.cancel();

	}

	public void setScramble() {
		scramble.postValue("Scramble will be displayed here.");
	}

}
