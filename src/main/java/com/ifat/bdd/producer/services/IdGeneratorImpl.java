package com.ifat.bdd.producer.services;

/**
 * @author Evgeny Borisov
 */
public class IdGeneratorImpl implements IdGenerator {
    @Override
    public long getNewId() {
        return System.nanoTime();
    }
}
