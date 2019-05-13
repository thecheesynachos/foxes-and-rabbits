package io.muzoo.ooc.ecosystems.simulation;

import io.muzoo.ooc.ecosystems.creatures.Actor;
import io.muzoo.ooc.ecosystems.simulation.helpers.Grass;
import io.muzoo.ooc.ecosystems.simulation.helpers.Location;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single animal.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.04.09
 */
public class Field {
    private static final Random rand = new Random();

    // The depth and width of the field.
    private int depth, width;
    // Storage for the animals.
    private Actor[][][] field;
    // Current population of a field
    private int[][] population;
    // Array to keep amount of grass in a field
    private Grass[][] grass;
    // How many Actor can share the same spot in field
    private static final int POPULATION_DENSITY = 3;

    /**
     * Represent a field of the given dimensions.
     *
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public Field(int depth, int width) {
        this.depth = depth;
        this.width = width;
        field = new Actor[depth][width][POPULATION_DENSITY];
        population = new int[depth][width];
        grass = new Grass[depth][width];
    }

    /**
     * Empty the field.
     */
    public void clear() {
        for (int row = 0; row < depth; row++) {
            for (int col = 0; col < width; col++) {
                population[row][col] = 0;
                grass[row][col] = new Grass();
                for (int spot = 0; spot < POPULATION_DENSITY; spot++){
                    field[row][col][spot] = null;
                }
            }
        }
    }


    /**
     * Place an animal at the given location.
     * If there is already an animal at the location it will
     * be lost.
     *
     * @param actor  The actor to be placed.
     * @param row    Row coordinate of the location.
     * @param col    Column coordinate of the location.
     */
    public boolean place(Actor actor, int row, int col) {
        return place(actor, new Location(row, col));
    }

    /**
     * Place an animal at the given location.
     * If there is already an animal at the location it will
     * be lost.
     *
     * @param actor   The actor to be placed.
     * @param location Where to place the animal.
     */
    public boolean place(Actor actor, Location location) {
        int row = location.getRow();
        int col = location.getCol();
        // only add in population if there is still free space
        if (population[row][col] < POPULATION_DENSITY - 1) {
            field[row][col][population[row][col]] = actor;
            population[row][col]++;
            return true;
        }
        return false;
    }

    /**
     * @return if spot is free
     */
    public boolean isFree(Location location){
        return population[location.getRow()][location.getCol()] < POPULATION_DENSITY - 1;
    }

    /**
     * Return the first actor at the given location, if any.
     *
     * @param location Where in the field.
     * @return The animal at the given location, or null if there is none.
     */
    public Actor getActorAt(Location location) {
        return getActorAt(location.getRow(), location.getCol());
    }


    /**
     * Return the first actor at the given location, if any.
     *
     * @param row The desired row.
     * @param col The desired column.
     * @return The animal at the given location, or null if there is none.
     */
    public Actor getActorAt(int row, int col) {
        return field[row][col][0];
    }

    /**
     * Return the actors at the given location, if any.
     *
     * @param location Where in the field.
     * @return The actors at the given location, or null if there is none.
     */
    public Actor[] getActorsAt(Location location) {
        return field[location.getRow()][location.getCol()];
    }

    /**
     * Generate a random location that is adjacent to the
     * given location, or is the same location.
     * The returned location will be within the valid bounds
     * of the field.
     *
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area. This
     * may be the same object as the location parameter.
     */
    public Location randomAdjacentLocation(Location location) {
        int row = location.getRow();
        int col = location.getCol();
        // Generate an offset of -1, 0, or +1 for both the current row and col.
        int nextRow = row + rand.nextInt(3) - 1;
        int nextCol = col + rand.nextInt(3) - 1;
        // Check in case the new location is outside the bounds.
        if (nextRow < 0 || nextRow >= depth || nextCol < 0 || nextCol >= width) {
            return location;
        } else if (nextRow != row || nextCol != col) {
            return new Location(nextRow, nextCol);
        } else {
            return location;
        }
    }

    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, then return the current
     * location if it is free. If not, return null.
     * The returned location will be within the valid bounds
     * of the field.
     *
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area. This may be the
     * same object as the location parameter, or null if all
     * locations around are full.
     */
    public Location freeAdjacentLocation(Location location) {
        Iterator adjacent = adjacentLocations(location);
        while (adjacent.hasNext()) {
            Location next = (Location) adjacent.next();
            if (population[next.getRow()][next.getCol()] < POPULATION_DENSITY) {
                return next;
            }
        }
        // check whether current location is free
        if (population[location.getRow()][location.getCol()] < POPULATION_DENSITY) {
            return location;
        } else {
            return null;
        }
    }

    /**
     * Generate an iterator over a shuffled list of locations adjacent
     * to the given one. The list will not include the location itself.
     * All locations will lie within the grid.
     *
     * @param location The location from which to generate adjacencies.
     * @return An iterator over locations adjacent to that given.
     */
    public Iterator<Location> adjacentLocations(Location location) {
        int row = location.getRow();
        int col = location.getCol();
        LinkedList<Location> locations = new LinkedList<>();
        for (int roffset = -1; roffset <= 1; roffset++) {
            int nextRow = row + roffset;
            if (nextRow >= 0 && nextRow < depth) {
                for (int coffset = -1; coffset <= 1; coffset++) {
                    int nextCol = col + coffset;
                    // Exclude invalid locations and the original location.
                    if (nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                        locations.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }
        Collections.shuffle(locations, rand);
        return locations.iterator();
    }

    /**
     *  Eat grass at a location
     *
     * @return if grass eating is successful
     */
    public boolean eatGrass(int row, int col){
		boolean success = grass[row][col].eatGrass();
		return success;
    }

	public boolean eatGrass(Location location){
		return eatGrass(location.getRow(), location.getCol());
	}

    public boolean hasGrass(Location location){
        return grass[location.getRow()][location.getCol()].hasGrass();
    }

    /**
     *  Grows grass (in one timestep)
     */
    public void growGrass(){
        for (int row = 0; row < depth; row++){
            for (int col = 0; col < width; col++){
				grass[row][col].growGrass();
            }
        }
    }

    public void transferGrassDataFrom(Field field){
        this.grass = field.grass;
    }


    /**
     * @return The depth of the field.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @return The width of the field.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Population at a certain spot
     */
    public int getPoulation(int row, int col){
        return population[row][col];
    }

	public static int getPopulationDensity() {
		return POPULATION_DENSITY;
	}
}
