package com.ifat.bdd.infra;

/**
 * @author Evgeny Borisov
 */
public interface ObjectConfigurator {

    void configure(ApplicationContext context,Object t);
}
