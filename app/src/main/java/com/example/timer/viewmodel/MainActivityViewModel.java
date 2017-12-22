package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ignacy on 23.11.17.
 */

public class MainActivityViewModel extends ViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();

	// REVIEW:
	// elementy zwiazane z liczeniem powinny byc w jakiejs osobnej klasy
	long time1;
	long time2;
	Timer t;
	public TimeProvider provider = new TimeProviderImpl();

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
		counter.postValue(String.valueOf((time2 - time1)));
		t.cancel();

	}

	public void setScramble() {
		scramble.postValue("Scramble will be displayed here.");
	}

}
