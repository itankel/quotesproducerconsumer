package com.ifat.bdd.consumer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifat.bdd.common.model.Quote;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
@NoArgsConstructor
public class QuoteSaveAsJsonImp implements QuoteSaverToFile {

    @SneakyThrows
    @Override
    public void save(Quote quote, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), quote);
        System.out.println("    Saved quote : "+quote + " to json file : "+fileName);
    }
}
