package io.muzoo.ooc.ecosystems.creatures;

import io.muzoo.ooc.ecosystems.location.Field;
import io.muzoo.ooc.ecosystems.location.Location;

import java.util.List;

public class Hunter extends Actor {

    @Override
    public void act(Field currentField, Field updatedField, List<Animal> newAnimals) {

    }

    @Override
    protected Location nextLocation(Field currentField, Field updatedField) {
        return null;
    }

}
