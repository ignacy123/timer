package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.timer.viewmodel.CounterViewModel;
import com.example.timer.viewmodel.ScoreViewModel;
import com.example.timer.viewmodel.StatisticsViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(CounterViewModel.class)
	abstract ViewModel bindCounterFragmentViewModel(CounterViewModel viewModel);

	@Binds
	@IntoMap
	@ViewModelKey(ScoreViewModel.class)
	abstract ViewModel bindScoreFragmentViewModel(ScoreViewModel viewModel);

	@Binds
	@IntoMap
	@ViewModelKey(StatisticsViewModel.class)
	abstract ViewModel bindStatisticsViewModel(StatisticsViewModel viewModel);

	@Binds
	abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}