package com.ifat.bdd.producer;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class MainProducer {

    @SneakyThrows
    public static void main(String[] args) {
        MyQuoteProducer myQuotesProducer=new MyQuoteProducer();
        for (int i = 0; i < 100; i++) {
            myQuotesProducer.play();
            TimeUnit.SECONDS.sleep(8);
        }
    }
}
