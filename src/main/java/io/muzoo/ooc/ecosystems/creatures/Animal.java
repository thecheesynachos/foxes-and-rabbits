package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.location.Field;
import io.muzoo.ooc.ecosystems.location.Location;

import java.util.List;
import java.util.Random;

public abstract class Animal {

    // The animal's age.
    int age;
    // Whether the animal is alive or not.
    boolean alive;
    // The animal's position
    Location location;

    // A shared random number generator to control breeding.
    static final Random rand = new Random();

    /**
     * Create an animal. An animal can be created as a new born (age zero
     * and not hungry) or with random age.
     *
     * @param randomAge If true, the animal will have random age and hunger level.
     */
    public Animal(boolean randomAge){
        age = 0;
        alive = true;
        if (randomAge) {
            age = rand.nextInt(getMaxAge());
        }
    }

    /**
     * Check whether the animal is alive or not.
     *
     * @return True if the animal is still alive.
     */
    public boolean isAlive() {
        return alive;
    }


    /**
     * Set the animal's location.
     *
     * @param row The vertical coordinate of the location.
     * @param col The horizontal coordinate of the location.
     */
    public void setLocation(int row, int col) {
        this.location = new Location(row, col);
    }


    /**
     * Set the animal's location.
     *
     * @param location The fox's location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * An animal can breed if it has reached the breeding age.
     */
    protected boolean canBreed() {
        return age >= getBreedingAge();
    }


    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * Increase the age.
     * This could result in the animal's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            alive = false;
        }
    }

    public abstract int getBreedingAge();

    public abstract int getMaxAge();

    public abstract double getBreedingProbability();

    public abstract int getMaxLitterSize();

    /**
     * This includes actions that is done by an animal at a time
     *
     * @param currentField The field currently occupied.
     * @param updatedField The field to transfer to.
     * @param newAnimals     A list to add newly born foxes to.
     */
    public abstract void act(Field currentField, Field updatedField, List<Animal> newAnimals);

}
