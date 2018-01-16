package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGenerator;
import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;
import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;

import dagger.Binds;
import dagger.Module;

@Module
abstract class BusinessLogicModule {

	@Binds
	abstract TimeProvider timeProvider(TimeProviderImpl timeProvider);

	@Binds
	abstract ThreeByThreeScrambleGenerator threeByThreeScrambleGenerator(ThreeByThreeScrambleGeneratorImpl threeByThreeScrambleGenerator);
}