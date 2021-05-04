package com.ifat.bdd.producer.services;



import com.ifat.bdd.infra.RandomUtil;
import com.ifat.bdd.infra.Singleton;
import com.ifat.bdd.producer.repos.QuoterRepo;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Singleton
public class QuoterServiceImpl implements QuoterService {
    private final QuoterRepo quoterRepo;
    private List<String> allQuotes;

    public QuoterServiceImpl(QuoterRepo quoterRepo) {
        this.quoterRepo = quoterRepo;
        allQuotes = quoterRepo.getAllQuotes();

    }

    @Override
    public String getRandomQuoteText() {
        return RandomUtil.getRandomItem(quoterRepo.getAllQuotes());
    }
}
