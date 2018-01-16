package com.example.timer.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

/**
 * Created by ignacy on 16.01.18.
 */

public class ViewModelUtil {

	private ViewModelUtil() {
	}

	public static <T extends ViewModel> ViewModelProvider.Factory createFor(T model) {
		return new ViewModelProvider.Factory() {

			@Override
			public <T extends ViewModel> T create(Class<T> modelClass) {
				if (modelClass.isAssignableFrom(model.getClass())) {
					return (T) model;
				}
				throw new IllegalArgumentException("unexpected model class " + modelClass);
			}
		};
	}
}