package com.ifat.bdd.data;

public class QuotesFactory {
    private static final QuotesRepo repo;
    static {
        repo = new QuotesRepo();
    }

    public static Quote createQuote() {
        String text = repo.getRandomQuote();
        Status status = Status.getStatus(text.length());
        return new Quote(System.nanoTime(), text, status);
    }

}
