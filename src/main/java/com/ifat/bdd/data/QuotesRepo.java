package com.ifat.bdd.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuotesRepo {
    private static Random random=new Random();
    private List<String> quotes = new ArrayList<>();

    public QuotesRepo(){
        this.quotes.add("Sky");
        this.quotes.add("Blue Sky :)");
        this.quotes.add("The blue sky are like a ....");
        this.quotes.add("this");
        this.quotes.add("this is a");
        this.quotes.add("This is a medium");
        this.quotes.add("This is a long sentence ");
        this.quotes.add("The colour of the rain bow  are .....");
        this.quotes.add("Overhead the albatross");
        this.quotes.add("Overhead");
        this.quotes.add("Overhead the");
        this.quotes.add("Overhead the albatross");
        this.quotes.add("Hangs motionless upon the air");
        this.quotes.add("Hangs");
        this.quotes.add("Hangs motionless");
        this.quotes.add("Hangs motionless upon the air");
    }

    public String getRandomQuote(){
        int randVal= random.nextInt(this.quotes.size());
        return this.quotes.get(randVal);
    }
}
