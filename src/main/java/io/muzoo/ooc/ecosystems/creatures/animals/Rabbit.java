package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.location.Field;
import io.muzoo.ooc.ecosystems.location.Location;

import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Rabbit extends Animal{
    // Characteristics shared by all rabbits (static fields).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 50;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    static final int RABBIT_FOOD_VALUE = 5;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the rabbit will have a random age.
     */
    public Rabbit(boolean randomAge) {
        super(randomAge);
    }


    @Override
    protected Location nextLocation(Field currentField, Field updatedField) {
        return updatedField.freeAdjacentLocation(location);
    }

    @Override
    protected void incrementHunger() { }


    @Override
    public int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    public int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    int getFoodValue() {
        return RABBIT_FOOD_VALUE;
    }

    @Override
    protected Animal generateNewAnimal() {
        return new Rabbit(true);
    }

}
