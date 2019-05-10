package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.location.Field;
import io.muzoo.ooc.ecosystems.location.Location;

public class Tiger extends Animal {

    // The age at which a tiger can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a tiger can live.
    private static final int MAX_AGE = 250;
    // The likelihood of a tiger breeding.
    private static final double BREEDING_PROBABILITY = 0.05;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // The food value of a single fox. In effect, this is the
    // number of steps a tiger can go before it has to eat again.
    private static final int FOX_FOOD_VALUE = 10;
    // The food value of a single tiger
    static final int TIGER_FOOD_VALUE = 30;
    // The tiger's food level, which is increased by eating foxes.
    private int foodLevel;

    public Tiger(boolean randomAge){
        super(randomAge);
        foodLevel = FOX_FOOD_VALUE;
    }

    @Override
    protected void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            alive = false;
        }
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
        return TIGER_FOOD_VALUE;
    }

    @Override
    protected Animal generateNewAnimal() {
        return new Tiger(true);
    }

    @Override
    protected Location nextLocation(Field currentField, Field updatedField) {
        Location newLocation = huntForPrey(currentField, location, Fox.class);
        if (newLocation == null) {  // no foxes - tries finding a rabbit
            newLocation = huntForPrey(currentField, location, Rabbit.class);
        }
        if (newLocation == null) {  // no food found - move randomly
            newLocation = updatedField.freeAdjacentLocation(location);
        }
        return newLocation;
    }

}
