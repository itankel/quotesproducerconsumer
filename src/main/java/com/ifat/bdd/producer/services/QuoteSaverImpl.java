package com.ifat.bdd.producer.services;

import com.ifat.bdd.common.model.Quote;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Evgeny Borisov
 */
public class QuoteSaverImpl implements QuoteSaver {

    @InjectValue("producer_output_location")
    private String locationDir;

    @SneakyThrows
    @Override
    public void save(Quote quote) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        File file = new File(locationDir + "quote_" + timeStamp+".obj");
        file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(quote);
    }
}
