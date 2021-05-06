package com.ifat.bdd.consumer.services;

import com.ifat.bdd.common.model.Quote;
import com.ifat.bdd.consumer.services.QuoteLoader;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


@NoArgsConstructor
public class QuoteLoaderFromFilesImpl implements QuoteLoader {

    @SneakyThrows
    @Override
    public Quote loadOneQuote(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("file "+fileName+"not exists");
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Quote quote = (Quote) ois.readObject();
        System.out.println("Finished loading "+ quote +" from file " + fileName);
        return quote;
    }

}
