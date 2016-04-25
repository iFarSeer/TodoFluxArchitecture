package com.farseer.todo.flux.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}