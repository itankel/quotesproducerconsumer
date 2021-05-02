package com.ifat.bdd.producer;

import com.ifat.bdd.data.Quote;
import com.ifat.bdd.data.QuotesFactory;
import com.ifat.bdd.utiles.QuotesSerializer;

public class MyQuoteProducer {
    public void play(){
        Quote quote = QuotesFactory.createQuote();
        System.out.println("create quote ==> " + quote);
        QuotesSerializer.serialize(quote);
    }

}
