package com.ifat.bdd.consumer.flow;

import com.ifat.bdd.consumer.control.QuotesConsumer;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Singleton
public class QuotesConsumerFlowManager {

    @InjectByType
    private QuotesConsumer quotesConsumer;


    @SneakyThrows
    public void  activateConsumer(){
        Thread consumerLoadStageThread = new Thread(this::consumeLoadingStage);
        Thread consumerSaveStageThread = new Thread(this::consumeSecondStage);
        consumerLoadStageThread.start();
        consumerSaveStageThread.start();
        Thread.currentThread().join();
    }



    @SneakyThrows
    private void consumeLoadingStage(){
        System.out.println("Loading thread "+ Thread.currentThread().getId() + " start");
        while(true){
            quotesConsumer.consumeNewQuote();
            TimeUnit.SECONDS.sleep(10);
        }
    }

    @SneakyThrows
    private void consumeSecondStage(){
        System.out.println("Saving thread "+ Thread.currentThread().getId() + " start");
        while(true){
            TimeUnit.SECONDS.sleep(3);
            quotesConsumer.saveConsumedQuote();
            TimeUnit.SECONDS.sleep(7);
        }
    }

}
