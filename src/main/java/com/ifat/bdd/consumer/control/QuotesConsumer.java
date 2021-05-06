package com.ifat.bdd.consumer.control;

public interface QuotesConsumer {
    void consumeNewQuote();
    void saveConsumedQuote();
}
