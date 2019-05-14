package io.muzoo.ooc.ecosystems.simulation.helpers;

import java.util.Random;

public class Grass {

	// Amount of grass
	private int grassAmount;
	// Maximum amount of grass that can exist in a spot
	private static final int MAX_GRASS_LIMIT = 5;
	// Grass growing probability
	private static final double GRASS_GROWING_PROB = 0.5;

	public Grass(){
		grassAmount = MAX_GRASS_LIMIT;
	}

	public boolean eatGrass(int amountToEat){
		if (grassAmount >= amountToEat){
			grassAmount -= amountToEat;
			return true;
		} else {
			return false;
		}
	}

	public boolean eatGrass(){
		return eatGrass(1);
	}

	public boolean hasGrass(){
		return grassAmount > 0;
	}

	public void growGrass(){
		if (grassAmount < MAX_GRASS_LIMIT && Randomer.randomBooleanWithProb(GRASS_GROWING_PROB)){
			grassAmount++;
		}
	}

}
