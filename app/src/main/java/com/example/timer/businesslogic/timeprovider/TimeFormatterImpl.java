package com.example.timer.businesslogic.timeprovider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ignacy on 07.03.18.
 */

@Singleton
public class TimeFormatterImpl implements TimeFormatter {

	@Inject
	public TimeFormatterImpl() {

	}


	public String chooseTimeFormat(long timeToFormat) {
		// TODO REVIEW - DRY - zmieniłeś ale ciągle masz trzy razy powtórzone: "return formatTime(..., timeToFormat)"
		// Nie myśl, że sie czepiam.  W praktyce zawsze powinieneś dążyć do eliminowania zduplikowanego kodu.
		// W tym przypadku jedynym co się zmienia jest pattern, więc powinienieś to tak przerobić
		// żeby tylko pattern był wyliczany w zależności od timeToFormat np. w osobnej metodzie i całość się uprości.
		//
		// Usunięcie zduplikowanego kodu to jeden z ważniejszych kroków cyklu TDD :
		// https://en.wikipedia.org/wiki/Test-driven_development#Test-driven_development_cycle

		if (timeToFormat < 10000) {
			return formatTime("s.SSS", timeToFormat);
		}
		if (timeToFormat < 60000) {
			return formatTime("ss.SSS", timeToFormat);
		}
		return formatTime("m:ss.SSS", timeToFormat);
	}

	public String formatTime(String pattern, long timeToFormat){
		return new SimpleDateFormat(pattern).format(new Date(timeToFormat));
	}
}