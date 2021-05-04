package com.ifat.bdd.consumer.services;

import com.ifat.bdd.common.model.Quote;

public interface QuoteLoader {
    Quote loadOneQuote(String fileName);
}
