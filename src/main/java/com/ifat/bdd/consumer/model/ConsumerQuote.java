package com.ifat.bdd.consumer.model;

import com.ifat.bdd.common.model.Quote;
import lombok.Data;

@Data
public class ConsumerQuote {
    private Quote quote;
    private String fileName;
}
