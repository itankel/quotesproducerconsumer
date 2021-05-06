package com.ifat.bdd.consumer.control;


import com.ifat.bdd.consumer.handlers.QuotesConsumerLoadingHandler;
import com.ifat.bdd.consumer.handlers.QuotesConsumerSavingHandler;
import com.ifat.bdd.consumer.model.ConsumerQuote;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@RequiredArgsConstructor
@Singleton
public class QuotesConsumerImp implements QuotesConsumer {
    @InjectByType
    private QuotesConsumerSavingHandler quotesConsumerSavingHandler;
    @InjectByType
    private QuotesConsumerLoadingHandler quotesConsumerLoadingHandler;

    private BlockingDeque<ConsumerQuote> quotesStatesList = new LinkedBlockingDeque<>();

    public void consumeNewQuote() {
        ConsumerQuote consumerQuote = quotesConsumerLoadingHandler.handleLoading();
        if ( consumerQuote !=null) {
            quotesStatesList.addLast(consumerQuote);
            System.out.println("Consumed quote is > "+ consumerQuote);
        }
    }

    public void saveConsumedQuote() {
        ConsumerQuote consumerQuote = quotesStatesList.poll();
        quotesConsumerSavingHandler.handleSaving(consumerQuote);
        System.out.println("Saved quote is > "+ consumerQuote);

    }
}




