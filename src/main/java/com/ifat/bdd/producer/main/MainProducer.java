package com.ifat.bdd.producer.main;

import com.ifat.bdd.infra.ApplicationContext;
import com.ifat.bdd.infra.JavaConfig;
import com.ifat.bdd.producer.flow.QuoterProducerFlowManager;

public class MainProducer {

    public static void main(String[] args) {

        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.ifat.bdd").build());
        QuoterProducerFlowManager flowManager = context.getObject(QuoterProducerFlowManager.class);
        flowManager.saveQuote();
    }
}
