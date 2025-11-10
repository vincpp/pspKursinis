package com.psp.utils;

import java.util.Random;

public class RandomProvider {
    private long seed;
    private final Random random;

    public RandomProvider() {
        this(System.currentTimeMillis());
    }

    public RandomProvider(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
        this.random.setSeed(seed);
    }

    long getRandomNumber() {
        return random.nextLong();
    }
}
