package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.creatures.animals.Animal;
import io.muzoo.ooc.ecosystems.simulation.Field;
import io.muzoo.ooc.ecosystems.simulation.helpers.Location;

import java.util.Iterator;
import java.util.List;

public class Hunter extends Actor {

    public Hunter(){
        super();
        alive = true;
    }

    @Override
    public void act(Field currentField, Field updatedField, List<Animal> newAnimals) {
        Location nextLocation = huntToNextLocation(currentField, updatedField);
        if (nextLocation != null) {
            setLocation(nextLocation);
            updatedField.place(this, nextLocation);
        } else {
            setLocation(location);
            updatedField.place(this, location);
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

}
