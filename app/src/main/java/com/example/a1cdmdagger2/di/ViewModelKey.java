package com.example.a1cdmdagger2.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import dagger.MapKey;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/* This will be the same always when
*  planning to use view models
*  RUNTIME Ret Policy means same as singleton scope (All app lifetime)
* */
@Documented
@Target(ElementType.METHOD)
@Retention(RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}

