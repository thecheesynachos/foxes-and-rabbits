package io.muzoo.ooc.ecosystems.creatures.animals;

import io.muzoo.ooc.ecosystems.simulation.Field;
import io.muzoo.ooc.ecosystems.simulation.helpers.Location;
import io.muzoo.ooc.ecosystems.simulation.helpers.Randomer;

import java.awt.*;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Fox extends Animal {
    // Characteristics shared by all foxes (static fields).

    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a fox can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.1;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The food value of a single fox
    private static final int FOX_FOOD_VALUE = 10;

    private static final Color COLOUR = Color.blue;

    // Individual characteristics (instance fields).

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with random age.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     */
    public Fox(boolean randomAge) {
        super(randomAge);
    }

    public Fox(){
        super(false);
    }


    @Override
    protected Location huntToNextLocation(Field currentField, Field updatedField) {
        Location newLocation = huntForPrey(currentField, location, Rabbit.class);
        if (newLocation == null) {  // no food found - move randomly
            newLocation = updatedField.freeAdjacentLocation(location);
        }
        return newLocation;
    }

    @Override
    public Color getColour() {
        return COLOUR;
    }

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
        return FOX_FOOD_VALUE;
    }

    @Override
    protected Animal generateNewAnimal() {
        return new Fox(true);
    }

    /**
     * Tell the fox that it's dead now :(
     */
    public void setDead() {
        alive = false;
    }

}
