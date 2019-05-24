package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.creatures.animals.Animal;
import io.muzoo.ooc.ecosystems.simulation.Field;
import io.muzoo.ooc.ecosystems.simulation.helpers.Location;
import io.muzoo.ooc.ecosystems.simulation.helpers.Randomer;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Hunter extends Actor {

    // the probability that a hunter just disappears (arrested maybe)
    private static final double GONE_FACTOR = 0.001;

    private static final Color COLOUR = Color.black;

    public Hunter(){
        super();
        alive = true;
    }

    @Override
    public void act(Field currentField, Field updatedField, List<Animal> newAnimals) {
        checkStatus();
        if(alive){
            Location nextLocation = huntToNextLocation(currentField, updatedField);
            if (nextLocation != null) {
                setLocation(nextLocation);
                updatedField.place(this, nextLocation);
            } else {
                setLocation(location);
                updatedField.place(this, location);
            }
        }
    }

    private void checkStatus() {
        if(Randomer.randomBooleanWithProb(GONE_FACTOR)){
            alive = false;
        }
    }

    @Override
    protected Location huntToNextLocation(Field currentField, Field updatedField) {
        // Hunter just moves to a random spot
        Iterator<Location> adjacentLocations = currentField.adjacentLocations(location);
        Location where = adjacentLocations.next();
        Actor actor = currentField.getActorAt(where);
        // If the spot has some animal in it, it gets hunted and dies :(
        if (actor instanceof Animal) {
            Animal prey = (Animal) actor;
            if (prey.isAlive()) {
                prey.setDead();
            }
        }
        return where;
    }

    @Override
    public Color getColour() {
        return COLOUR;
    }
}
