package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.location.Field;
import io.muzoo.ooc.ecosystems.location.Location;

import java.util.List;
import java.util.Random;

public abstract class Actor {

    // The actor's position
    Location location;

    // A shared random number generator to control breeding.
    static final Random rand = new Random();

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
     * This includes actions that is done by an actor at a time
     *
     * @param currentField The field currently occupied.
     * @param updatedField The field to transfer to.
     * @param newAnimals   A list to add newly born animal to (if any).
     */
    public abstract void act(Field currentField, Field updatedField, List<Animal> newAnimals);

    /**
     * Find the next location that the animal goes to
     * How this is chosen should depend on the type of animal (e.g. find food, random, run away)
     */
    protected abstract Location nextLocation(Field currentField, Field updatedField);

}
