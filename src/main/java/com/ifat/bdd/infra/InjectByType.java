package com.ifat.bdd.infra;
/**
 * @author Evgeny Borisov
 */

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface InjectByType {
}
