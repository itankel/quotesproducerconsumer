package com.ifat.bdd.consumer.services;

import com.ifat.bdd.infra.Singleton;
import com.ifat.bdd.producer.services.InjectValue;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Singleton
public class QuotesFilesManagerImpl implements QuotesFilesManager {

    @InjectValue("consumer_input_location")
    private String inputLocationDir;
    @InjectValue("consumer_input_files_extension_to_search")
    private String filesExtension;

    private List<File> filesCache = new ArrayList<>();
    private long lastLoaded=0;


    @Override
    public String findNextQuoteFile() {

        List<File> filesCreatedSinceLastLoadingTime = Stream.of(new File(inputLocationDir).listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().contains(".obj"))
                .filter(file ->file.lastModified() > lastLoaded)
                .collect(Collectors.toList());

        final Long lastOne= lastLoaded;
        if (filesCreatedSinceLastLoadingTime.size()>0){
            Long currentLastLoaded = filesCreatedSinceLastLoadingTime.stream()
                    .map(File::lastModified).max(Long::compareTo)
                    .orElseGet(()-> lastOne);
            filesCache.addAll(filesCreatedSinceLastLoadingTime);
            lastLoaded=currentLastLoaded;
        }else{
            System.out.println(" no new obj files to load ");
        }

        String fileNameToReturn="";
        if (filesCache.size()>0){
            fileNameToReturn = filesCache.get(0).getAbsolutePath();
            filesCache.remove(0);
        }else{
            System.out.println(" no obj files in cache ");
        }

        return fileNameToReturn;
    }

}
