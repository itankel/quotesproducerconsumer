package com.ifat.bdd.producer.services;


import com.ifat.bdd.common.model.Quote;

/**
 * @author Evgeny Borisov
 */
public interface QuoteProducer {
    Quote generate();
}
