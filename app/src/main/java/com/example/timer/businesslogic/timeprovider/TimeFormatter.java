package com.example.timer.businesslogic.timeprovider;

/**
 * Created by ignacy on 07.03.18.
 */

public interface TimeFormatter {

	// TODO REVIEW rozumiem czym się kierowałeś zmieniając nazwę, ale formatTime było lepszą nazwą
	// Spójrz na to z perspektywy użytkownika tego interfejsu, który nie zna implementacji.
	// Klasa nazywa się TimeFormatter i widzisz w niej metodę chooseTimeFormat.
	// To subiektywne ale ja bym pomyślał, że ta metoda zwraca format czyli coś takiego jak "m:ss.SSS" a nie "2:30.132"
	//
	// Inna kwestia, że nazwa chooseTimeToFormat zdradza szczegóły implementacyjne - to nie jest poprawne
	String chooseTimeFormat(long timeToFormat);
}
