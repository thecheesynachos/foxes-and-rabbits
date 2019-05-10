package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.creatures.animals.Animal;
import io.muzoo.ooc.ecosystems.creatures.animals.Fox;
import io.muzoo.ooc.ecosystems.creatures.animals.Rabbit;
import io.muzoo.ooc.ecosystems.creatures.animals.Tiger;

import java.util.Random;

public class ActorFactory {

    private static final Random rand = new Random();

    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.2;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.1;
    // The probability that a tiger will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.08;
    // The probability that a hunter will be created in a grid position
    private static final double HUNTER_CREATION_PROBABILITY = 0.02;

    public Actor generateActor(boolean randomAge){
        if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
            return new Fox(randomAge);
        } else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
            return new Rabbit(randomAge);
        } else if (rand.nextDouble() <= TIGER_CREATION_PROBABILITY) {
            return new Tiger(randomAge);
        } else if (rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
            return new Hunter();
        } else return null;
    }

}
