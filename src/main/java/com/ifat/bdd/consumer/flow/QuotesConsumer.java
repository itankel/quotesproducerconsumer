package com.ifat.bdd.consumer.flow;


import com.ifat.bdd.common.model.Quote;
import com.ifat.bdd.consumer.services.ConsumerQuoteFilesManager;
import com.ifat.bdd.consumer.services.QuoteLoader;
import com.ifat.bdd.consumer.services.QuoteSaverToFile;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@RequiredArgsConstructor
@Singleton
public class QuotesConsumer {
    @InjectByType
    private QuoteLoader quoteLoader;// = new QuoteLoaderFromFilesImpl();
    @InjectByType
    private QuoteSaverToFile quoteSaver;// = new QuoteSaveAsJsonImp();
    @InjectByType
    private ConsumerQuoteFilesManager quoteFileManager;// new ConsumerQuoteFilesLoadManagerImpl();

    private BlockingDeque<Map.Entry<String, Quote>> filesForLoad = new LinkedBlockingDeque<>();

    public void consumeLoadingPhase() {
        String quoteFileToLoaded = quoteFileManager.findNextQuoteFile();
        if (quoteFileToLoaded != null && quoteFileToLoaded.length() > 0) {
            Quote quote = quoteLoader.loadOneQuote(quoteFileToLoaded);
            if (quote != null) {
                this.filesForLoad.addLast(new AbstractMap.SimpleEntry<>(quoteFileToLoaded, quote));
             }
        }
    }

    public void consumeSavingPhase() {
        Map.Entry<String, Quote> quoteToSave = this.filesForLoad.pop();
        if (quoteToSave.getValue() != null) {
            quoteSaver.save(quoteToSave.getValue(), quoteToSave.getKey().replace(".obj", ".json"));
            System.out.println(quoteToSave.getValue() + " saved in " + quoteToSave.getKey());
        }
    }
}

//     work:  split to 2 phase to be able to run in threads
//    public void consume() {
//        String quoteFileToLoaded = quoteFileManager.findNextQuoteFile();
//        if (quoteFileToLoaded!=null || quoteFileToLoaded.length()>0) {
//            Quote quote = quoteLoader.loadOneQuote(quoteFileToLoaded);
//            if (quote != null) {
//                quoteSaver.save(quote, quoteFileToLoaded.replace(".obj",".json"));
//            }
//        }
//    }
//
//



