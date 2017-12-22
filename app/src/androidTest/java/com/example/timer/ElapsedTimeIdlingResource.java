package com.example.timer;

import android.support.test.espresso.IdlingResource;
import android.util.Log;

public class ElapsedTimeIdlingResource implements IdlingResource {

	private final long startTime;
	private final long waitingTime;
	private ResourceCallback resourceCallback;

	public ElapsedTimeIdlingResource(long waitingTime) {
		this.startTime = System.currentTimeMillis();
		this.waitingTime = waitingTime;
	}

	@Override
	public String getName() {
		return ElapsedTimeIdlingResource.class.getName() + ":" + waitingTime;
	}

	@Override
	public boolean isIdleNow() {
		long elapsed = System.currentTimeMillis() - startTime;
		// REVIEW - START
		// ciekawy pomysł zeby to tego uzyc IdlingResource ale Espresso nie wola #isIdleNow
		// co milisekundę, zaobserwuj w logcat co wypisuje poniższe:
		Log.e("REVIEW", "Elapsed:" + elapsed);
		// wydaje mi się, że najlepiej będzie stworzyć abstrakcję nad aktualnym czasem
		// żeby w normalnej aplikacji użyć prawdziwej implementacji z System.currentTimeMillis()
		// a w testach implementacji w której arbitralnie będziesz mógł ustawić, że upłynęło X czasu
		// dzięki czemu testy nie będą musiały czekać aż upłynie zadany (nawet bardzo długi) czas
		// REVIEW - END
		boolean idle = (elapsed >= waitingTime);
		if (idle) {
			resourceCallback.onTransitionToIdle();
		}
		return idle;
	}

	@Override
	public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
		this.resourceCallback = resourceCallback;
	}
}