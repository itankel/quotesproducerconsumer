package com.ifat.bdd.producer.services;

import com.ifat.bdd.common.model.Quote;
import com.ifat.bdd.common.model.QuoteLengthStatus;
import com.ifat.bdd.infra.Singleton;
import lombok.RequiredArgsConstructor;

/**
 * @author Evgeny Borisov
 */
@RequiredArgsConstructor
@Singleton
public class QuoteProducerImpl implements QuoteProducer {
    private final IdGenerator idGenerator;
    private final QuoterService quoterService;

    @Override
    public Quote generate() {


        String quoteText = quoterService.getRandomQuoteText();
        Quote quote = Quote.builder()
                .id(idGenerator.getNewId())
                .text(quoteText)
                .status(QuoteLengthStatus.findByLength(quoteText.length()))
                .build();

        return quote;
    }
}

