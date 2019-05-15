package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.creatures.animals.Animal;
import io.muzoo.ooc.ecosystems.simulation.Field;
import io.muzoo.ooc.ecosystems.simulation.helpers.Location;

import java.util.List;

public abstract class Actor {

    // The actor's position
    protected Location location;
    // Whether the actor is alive or not.
    protected boolean alive;

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
     * Set the actor's location.
     *
     * @param location The fox's location.
     */
    public void setLocation(Location location) {
        this.location = location;
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
     * This includes actions that is done by an actor at a time
     *
     * @param currentField The field currently occupied.
     * @param updatedField The field to transfer to.
     * @param newAnimals   A list to add newly born animal to (if any).
     */
    public abstract void act(Field currentField, Field updatedField, List<Animal> newAnimals);

    /**
     * Find the next location that the actor goes to
     * How this is chosen should depend on the type of actor (e.g. find food, random, run away)
     */
    protected abstract Location huntToNextLocation(Field currentField, Field updatedField);

}
