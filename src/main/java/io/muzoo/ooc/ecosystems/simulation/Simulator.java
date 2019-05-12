package io.muzoo.ooc.ecosystems.simulation;

import io.muzoo.ooc.ecosystems.creatures.*;
import io.muzoo.ooc.ecosystems.creatures.animals.Animal;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple predator-prey simulator, based on a field containing
 * rabbits and foxes.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Simulator {
    // The private static final variables represent 
    // configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;

    // The list of actors in the field
    private List<Actor> liveActors;
    // The list of actors just born
    private List<Animal> newAnimals;
    // A second field, used to build the next stage of the simulation.
    private Field updatedField;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        liveActors = new ArrayList<>();
        newAnimals = new ArrayList<>();
        updatedField = new Field(depth, width);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each actor.
     * Update actors array to only keep live actors.
     */
    void simulateOneStep(SimulationFacade simulationFacade) {

        Field currentField = simulationFacade.getField();

        updatedField.clear();
        newAnimals.clear();
        ArrayList<Actor> actorsNextRound = new ArrayList<>();

        // let all actors act
        for (Actor actor : liveActors) {
            actor.act(currentField, updatedField, newAnimals);
            if (actor.isAlive()){
                actorsNextRound.add(actor);
            }
        }
        // add new born actors to the list of actors
        actorsNextRound.addAll(newAnimals);
        liveActors = actorsNextRound;

        simulationFacade.setField(updatedField);

    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        liveActors.clear();
        updatedField.clear();
    }

    /**
     * Populate a field with foxes and rabbits.
     *
     * @param field The field to be populated.
     */
    void populate(Field field) {
        ActorFactory actorFactory = new ActorFactory();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Actor actor = actorFactory.generateActor(true);
                if(actor != null) {
                    liveActors.add(actor);
                    actor.setLocation(row, col);
                    field.place(actor, row, col);
                } // else leave the location empty.
            }
        }
        Collections.shuffle(liveActors);
    }

    public List<Actor> getCurrentLiveActors(){
        return liveActors;
    }

}
