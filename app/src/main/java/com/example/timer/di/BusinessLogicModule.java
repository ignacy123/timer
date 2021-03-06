package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import com.example.timer.businesslogic.timeprovider.GraphGenerator;
import com.example.timer.businesslogic.timeprovider.GraphGeneratorImpl;
import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.businesslogic.timeprovider.StatisticsGeneratorImpl;
import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGenerator;
import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;
import com.example.timer.businesslogic.timeprovider.TimeFormatter;
import com.example.timer.businesslogic.timeprovider.TimeFormatterImpl;
import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;

import dagger.Binds;
import dagger.Module;

@Module
abstract class BusinessLogicModule {

	@Binds
	abstract TimeProvider timeProvider(TimeProviderImpl timeProvider);

	@Binds
	abstract TimeFormatter timeFormater(TimeFormatterImpl timeFormatter);

	@Binds
	abstract ThreeByThreeScrambleGenerator threeByThreeScrambleGenerator(ThreeByThreeScrambleGeneratorImpl threeByThreeScrambleGenerator);

	@Binds
	abstract StatisticsGenerator statisticsGenerator(StatisticsGeneratorImpl statisticsGenerator);

	@Binds
	abstract GraphGenerator graphGenerator(GraphGeneratorImpl graphGenerator);
}