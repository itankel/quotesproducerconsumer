package com.ifat.bdd.consumer.handlers;

import com.ifat.bdd.consumer.model.ConsumerQuote;
import com.ifat.bdd.consumer.services.QuoteSaverToFile;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import com.ifat.bdd.producer.services.InjectValue;


import java.io.File;

@Singleton
public class QuotesConsumerSavingHandlerImpl implements QuotesConsumerSavingHandler {
    @InjectValue("consumer_output_location")
    private String outputLocationDir;
    @InjectValue("consumer_input_quotes_files_extension_to_search")
    private String inFilesExtension;

    @InjectByType
    private QuoteSaverToFile quoteSaver;

    @Override
    public void handleSaving(ConsumerQuote consumerQuote) {
        String outputFile;
        if (consumerQuote.getQuote() != null) {
            outputFile=consumerQuote.getFileName().replace(inFilesExtension,".json");
            if ( outputLocationDir!=null && outputLocationDir.length()>0){
                String fileName=outputFile.substring(outputFile.lastIndexOf(File.separator)+1);
                outputFile=outputLocationDir+fileName;
            }
            System.out.println("outputFile = " + outputFile);
            quoteSaver.save(consumerQuote.getQuote(), outputFile);
            //System.out.println("finished saving "+consumerQuote.getQuote() + " in " + consumerQuote.getFileName());
        }
    }


}
