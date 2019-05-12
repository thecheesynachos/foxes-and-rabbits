package io.muzoo.ooc.ecosystems.creatures.animals;

import io.muzoo.ooc.ecosystems.creatures.Actor;
import io.muzoo.ooc.ecosystems.simulation.Field;
import io.muzoo.ooc.ecosystems.simulation.helpers.Location;

import java.util.Iterator;
import java.util.List;

public abstract class Animal extends Actor {

    // The animal's age.
    private int age;
    // The fox's food level, which is increased by eating rabbits.
    int foodLevel;

    /**
     * Create an animal. An animal can be created as a new born (age zero
     * and not hungry) or with random age.
     *
     * @param randomAge If true, the animal will have random age and hunger level.
     */
    Animal(boolean randomAge){
        super();
        age = 0;
        alive = true;
        foodLevel = getFoodValue();
        if (randomAge) {
            age = rand.nextInt(getMaxAge());
        }
    }


    /**
     * An animal can breed if it has reached the breeding age.
     */
    boolean canBreed() {
        return age >= getBreedingAge();
    }


    /**
     * Increase the age.
     * This could result in the animal's death.
     */
    private void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            alive = false;
        }
    }

    public void act(Field currentField, Field updatedField, List<Animal> newAnimals){
        incrementAge();
        incrementHunger();
        if(alive) {
            createOffspring(updatedField, newAnimals);
            Location nextLocation = huntToNextLocation(currentField, updatedField);
            if (nextLocation != null) {
                setLocation(nextLocation);
                updatedField.place(this, nextLocation);
            } else {
                // can neither move nor stay - overcrowding - all locations taken
                alive = false;
            }
        }
    }

    private void createOffspring(Field updatedField, List<Animal> newAnimals){
        int births = breed();
        for (int b = 0; b < births; b++) {
            Animal newAnimal = generateNewAnimal();
            Location loc = updatedField.randomAdjacentLocation(location);
            boolean success = updatedField.place(newAnimal, loc);
            // only update the spot if there is still space (can add new population in)
            if (success) {
                newAnimals.add(newAnimal);
                newAnimal.setLocation(loc);
            }
        }
    }


    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * Tell the animal that it's dead now :(
     */
    public void setDead() {
        alive = false;
    }

    protected void incrementHunger(){
        foodLevel--;
        if (foodLevel <= 0) {
            alive = false;
        }
    }

    public abstract int getBreedingAge();

    public abstract int getMaxAge();

    public abstract double getBreedingProbability();

    public abstract int getMaxLitterSize();

    abstract int getFoodValue();

    /**
     * Generate a new instance of certain animal
     *
     * @return new Animal (of specific species)
     */
    protected abstract Animal generateNewAnimal();

    public Location huntForPrey(Field field, Location location, Class<? extends Animal> preySpecies){
        Iterator<Location> adjacentLocations = field.adjacentLocations(location);
        while (adjacentLocations.hasNext()) {
            Location where = adjacentLocations.next();
            Actor actor = field.getActorAt(where);
            if (actor != null && actor.getClass() == preySpecies) {
                Animal prey = (Animal) actor;
                if (prey.isAlive()) {
                    prey.setDead();
                    foodLevel = prey.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }

}
