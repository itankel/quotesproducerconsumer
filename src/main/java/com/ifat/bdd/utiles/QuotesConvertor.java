package com.ifat.bdd.utiles;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;

public class QuotesConvertor {

    @SneakyThrows
    public static void convertToJsonFile(Object object, String fileName){
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), object);
    }
}
