package com.ifat.bdd.consumer.services;

import com.ifat.bdd.common.model.Quote;

public interface QuoteSaverToFile {
        void save(Quote quote,String fileName);
}
