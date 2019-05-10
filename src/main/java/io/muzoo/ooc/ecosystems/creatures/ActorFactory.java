package io.muzoo.ooc.ecosystems.creatures;

import java.util.Random;

public class AnimalFactory {

    private static final Random rand = new Random();

    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.2;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.1;
    // The probability that a tiger will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.05;

    public Animal generateAnimal(boolean randomAge){
        if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
            return new Fox(randomAge);
        } else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
            return new Rabbit(randomAge);
        } else if (rand.nextDouble() <= TIGER_CREATION_PROBABILITY) {
            return new Tiger(randomAge);
        } else return null;
    }

}
