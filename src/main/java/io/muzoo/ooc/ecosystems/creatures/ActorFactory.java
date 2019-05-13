package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.creatures.animals.Animal;
import io.muzoo.ooc.ecosystems.creatures.animals.Fox;
import io.muzoo.ooc.ecosystems.creatures.animals.Rabbit;
import io.muzoo.ooc.ecosystems.creatures.animals.Tiger;

import java.util.Random;

public class ActorFactory {

    private static final Random rand = new Random();

    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.3;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.2;
    // The probability that a tiger will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.1;
    // The probability that a hunter will be created in a grid position
    private static final double HUNTER_CREATION_PROBABILITY = 0.03;

    public Actor generateActor(boolean randomAge){

        double randomNumber = rand.nextDouble();

        if (randomNumber <= FOX_CREATION_PROBABILITY) {
            return new Fox(randomAge);
        }
        randomNumber -= FOX_CREATION_PROBABILITY;

        if (randomNumber <= RABBIT_CREATION_PROBABILITY) {
            return new Rabbit(randomAge);
        }
        randomNumber -= RABBIT_CREATION_PROBABILITY;

        if (randomNumber <= TIGER_CREATION_PROBABILITY) {
            return new Tiger(randomAge);
        }
        randomNumber -= TIGER_CREATION_PROBABILITY;

        if (randomNumber <= HUNTER_CREATION_PROBABILITY) {
            return new Hunter();
        } else return null;
    }

}
