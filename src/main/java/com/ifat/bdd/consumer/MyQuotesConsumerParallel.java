package com.ifat.bdd.consumer;

import com.ifat.bdd.data.Quote;
import com.ifat.bdd.utiles.FilesUtils;
import com.ifat.bdd.utiles.QuotesConvertor;
import com.ifat.bdd.utiles.QuotesDeserializer;
import lombok.SneakyThrows;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class MyQuotesConsumerParallel {

    private final BlockingDeque<Map.Entry<String, Quote>> consumedFiles= new LinkedBlockingDeque<>();

    public void mainLoop(){
        Thread firstStageConsumer= new Thread(this::consumeLoop);
        Thread secondStageConsumer= new Thread(this::consumeSecondStage);
        firstStageConsumer.start();
        System.out.println("firstStageConsumer started = " + firstStageConsumer.getId());
        secondStageConsumer.start();
        System.out.println("secondStageConsumer started = " + secondStageConsumer.getId());

    }

    @SneakyThrows
    private void consumeLoop(){
        while(true){
            TimeUnit.SECONDS.sleep(10);
            consumeFirstStage();
        }
    }

    @SneakyThrows
    private void consumeSecondStage(){
        while(true){
            TimeUnit.SECONDS.sleep(11);
            Map.Entry<String, Quote> entry = this.consumedFiles.pop();
            consumeSecondStage(entry.getValue(), entry.getKey());
        }
    }

    private void consumeFirstStage(){
        Optional<String> fileCreatedName = FilesUtils.getLastWrittenFile();
        if (fileCreatedName.isPresent()) {
            String fileName = fileCreatedName.get();
            Quote quote = QuotesDeserializer.deserialize(fileName);
            consumedFiles.add(new AbstractMap.SimpleImmutableEntry<>( fileName,quote));
        }
    }

    private void consumeSecondStage(Quote quote,String  fileName){
        QuotesConvertor.convertToJsonFile(quote,fileName.replace(".obj",".json"));
    }

    public static void main(String[] args) {
        MyQuotesConsumerParallel myQuotesConsumerParallel = new MyQuotesConsumerParallel();
        myQuotesConsumerParallel.mainLoop();
    }

}
