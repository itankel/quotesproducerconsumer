package com.ifat.bdd.consumer.handlers;

import com.ifat.bdd.common.model.Quote;
import com.ifat.bdd.consumer.handlers.QuotesConsumerLoadingHandler;
import com.ifat.bdd.consumer.model.ConsumerQuote;
import com.ifat.bdd.consumer.services.QuoteLoader;
import com.ifat.bdd.consumer.services.QuotesFilesManager;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class QuotesConsumerLoadingHandlerImp implements QuotesConsumerLoadingHandler {
   // @InjectByType
    private QuoteLoader quoteLoader;// = new QuoteLoaderFromFilesImpl();
    //@InjectByType
    private QuotesFilesManager quoteFileManager;// new ConsumerQuoteFilesLoadManagerImpl();

    @Override
    public ConsumerQuote handleLoading() {
        ConsumerQuote consumerQuoteToReturn = null;
        String quoteFileName = quoteFileManager.findNextQuoteFile();
        if (quoteFileName != null && quoteFileName.length() > 0) {
            Quote quote = quoteLoader.loadOneQuote(quoteFileName);
            if (quote != null) {
                consumerQuoteToReturn = new ConsumerQuote();
                consumerQuoteToReturn.setFileName(quoteFileName);
                consumerQuoteToReturn.setQuote(quote);
            }
        }
        return consumerQuoteToReturn;
    }

}
