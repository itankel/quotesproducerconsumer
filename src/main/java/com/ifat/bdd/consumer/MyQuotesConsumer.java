package com.ifat.bdd.consumer;

import com.ifat.bdd.data.Quote;
import com.ifat.bdd.utiles.FilesUtils;
import com.ifat.bdd.utiles.QuotesConvertor;
import com.ifat.bdd.utiles.QuotesDeserializer;

import java.util.Optional;


public class MyQuotesConsumer {

    public void consume(){
        Optional<String> fileCreatedName = FilesUtils.getLastWrittenFile();
        if (fileCreatedName.isPresent()) {
            String fileName = fileCreatedName.get();
            Quote quote =QuotesDeserializer.deserialize(fileName);
            QuotesConvertor.convertToJsonFile(quote,fileName.replace(".obj",".json"));
        }
    }

}
