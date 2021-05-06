package com.ifat.bdd.consumer.handlers;

import com.ifat.bdd.consumer.model.ConsumerQuote;
import com.ifat.bdd.consumer.services.QuoteSaverToFile;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class QuotesConsumerSavingHandlerImp implements QuotesConsumerSavingHandler {
    private QuoteSaverToFile quoteSaver;// = new QuoteSaveAsJsonImp();

    @Override
    public void handleSaving(ConsumerQuote consumerQuote) {
        if (consumerQuote.getQuote() != null) {
            quoteSaver.save(consumerQuote.getQuote(), consumerQuote.getFileName().replace(".obj", ".json"));
            //System.out.println("finished saving "+consumerQuote.getQuote() + " in " + consumerQuote.getFileName());
        }
    }


}
