package com.ifat.bdd.utiles;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class QuotesSerializer {
    private static String WRITE_TO_DIR = "c:\\tmp\\quotes_objects\\";

    @SneakyThrows
    public static void serialize(Object objToWrite) {
        String timeStamp = Timestamp.valueOf(LocalDateTime.now()).toString();
        timeStamp = timeStamp.replaceAll("[:. ]", "_");
        String fileSuffix = "" + timeStamp + ".obj";
        File file = new File(WRITE_TO_DIR + fileSuffix);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objToWrite);
    }
}
