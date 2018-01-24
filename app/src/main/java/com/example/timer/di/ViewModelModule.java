package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.timer.viewmodel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel.class)
	abstract ViewModel bindMainActivityViewModel(MainViewModel activeMainActivityViewModel);

	@Binds
	abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}