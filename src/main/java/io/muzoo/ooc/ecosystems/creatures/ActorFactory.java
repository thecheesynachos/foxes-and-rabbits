package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.creatures.animals.Fox;
import io.muzoo.ooc.ecosystems.creatures.animals.Rabbit;
import io.muzoo.ooc.ecosystems.creatures.animals.Tiger;
import io.muzoo.ooc.ecosystems.simulation.helpers.Randomer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActorFactory {

    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.3;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.2;
    // The probability that a tiger will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.15;
    // The probability that a hunter will be created in a grid position
    private static final double HUNTER_CREATION_PROBABILITY = 0.01;

    private static final double[] ACTOR_CREATION_PROBABILITIES = new double[]{
            RABBIT_CREATION_PROBABILITY,
            FOX_CREATION_PROBABILITY,
            TIGER_CREATION_PROBABILITY,
            HUNTER_CREATION_PROBABILITY
    };

    private static final List<Class<? extends Actor>> CLASSES = new ArrayList<>(
            Arrays.asList(Rabbit.class, Fox.class, Tiger.class, Hunter.class)
    );

    public Actor generateActor(){

        double randomNumber = Randomer.randomDouble();

        for (int i = 0 ; i < ACTOR_CREATION_PROBABILITIES.length; i++){
            randomNumber -= ACTOR_CREATION_PROBABILITIES[i];
            if (randomNumber < 0){
                try {
                    return CLASSES.get(i).getConstructor().newInstance();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
