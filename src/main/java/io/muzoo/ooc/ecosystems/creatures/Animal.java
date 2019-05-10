package io.muzoo.ooc.ecosystems.creatures;

public abstract class Animal extends Actor{

    // The animal's age.
    int age;
    // Whether the animal is alive or not.
    boolean alive;

    /**
     * Create an animal. An animal can be created as a new born (age zero
     * and not hungry) or with random age.
     *
     * @param randomAge If true, the animal will have random age and hunger level.
     */
    public Animal(boolean randomAge){
        super();
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

}
