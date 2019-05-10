package io.muzoo.ooc.ecosystems.creatures;

import java.util.Random;

public class AnimalFactory {

    private static final Random rand = new Random();

    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;

    public Animal generateAnimal(boolean randomAge){
        if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
            return new Fox(randomAge);
        } else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
            return new Rabbit(randomAge);
        } else return null;
    }

}
