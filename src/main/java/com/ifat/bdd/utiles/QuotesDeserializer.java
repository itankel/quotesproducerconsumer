package com.ifat.bdd.utiles;

import com.ifat.bdd.data.Quote;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class QuotesDeserializer {
    @SneakyThrows
    public static Quote deserialize(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("file "+fileName+"not exists");
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Quote quote = (Quote) ois.readObject();
        System.out.println("created from file quote = " + quote);
        return quote;
    }

}
