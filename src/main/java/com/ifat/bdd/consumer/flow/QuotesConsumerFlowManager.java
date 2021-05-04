package com.ifat.bdd.consumer.flow;

import com.ifat.bdd.common.model.Quote;
import com.ifat.bdd.infra.InjectByType;
import com.ifat.bdd.infra.Singleton;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Singleton
public class QuotesConsumerFlowManager {

    @InjectByType
    private QuotesConsumer quotesConsumer;

    private final BlockingDeque<Map.Entry<String, Quote>> consumedFiles= new LinkedBlockingDeque<>();

    @SneakyThrows
    public void  activateConsumer(){
        Thread consumerLoadStageThread = new Thread(this::consumeLoadingLoop);
        Thread consumerSaveStageThread = new Thread(this::consumeSecondStage);
        consumerLoadStageThread.start();
        consumerSaveStageThread.start();
        Thread.currentThread().join();
    }



    @SneakyThrows
    private void consumeLoadingLoop(){
        System.out.println("Thread "+ Thread.currentThread().getId() + " start");
        while(true){
            TimeUnit.SECONDS.sleep(10);
            quotesConsumer.consumeLoadingPhase();
        }
    }

    @SneakyThrows
    private void consumeSecondStage(){
        System.out.println("Thread "+ Thread.currentThread().getId() + " start");
        while(true){
            TimeUnit.SECONDS.sleep(11);
            quotesConsumer.consumeSavingPhase();
        }
    }

}
