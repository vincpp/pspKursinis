package com.psp.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RandomProvider {
    private long seed;

    public RandomProvider(long seed) {
        this.seed = seed;
        System.out.println("RandomProvider initialized with seed: " + seed);
    }
    long getRandomNumber(){
        return 0;
    }
}
