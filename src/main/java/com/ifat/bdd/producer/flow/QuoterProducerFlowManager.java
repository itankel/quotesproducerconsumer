package com.ifat.bdd.producer.flow;


import com.ifat.bdd.common.model.Quote;
import com.ifat.bdd.producer.services.QuoteProducer;
import com.ifat.bdd.producer.services.QuoteSaver;
import lombok.AllArgsConstructor;

/**
 * @author Evgeny Borisov
 */
@AllArgsConstructor
public class QuoterProducerFlowManager {
    private QuoteProducer producer;
    private QuoteSaver saver;

    public void saveQuote() {
        Quote quote = producer.generate();
        saver.save(quote);
        System.out.println("produced quote > " + quote);
    }

}
