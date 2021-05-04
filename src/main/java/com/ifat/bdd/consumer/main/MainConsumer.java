package com.ifat.bdd.consumer.main;

import com.ifat.bdd.consumer.flow.QuotesConsumerFlowManager;
import com.ifat.bdd.infra.ApplicationContext;
import com.ifat.bdd.infra.JavaConfig;
import lombok.SneakyThrows;


public class MainConsumer {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.ifat.bdd").build());
        QuotesConsumerFlowManager consumerFlow = context.getObject(QuotesConsumerFlowManager.class);
        consumerFlow.activateConsumer();
    }
}

