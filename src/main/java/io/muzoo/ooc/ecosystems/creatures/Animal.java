package io.muzoo.ooc.ecosystems;

public abstract class Animal {

    // The animal's age.
    protected int age;
    // Whether the animal is alive or not.
    protected boolean alive;
    // The animal's position
    protected Location location;

    /**
     * Check whether the fox is alive or not.
     *
     * @return True if the fox is still alive.
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
     * Set the fox's location.
     *
     * @param location The fox's location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
