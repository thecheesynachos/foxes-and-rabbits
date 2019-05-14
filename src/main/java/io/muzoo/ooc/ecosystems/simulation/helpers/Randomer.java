package io.muzoo.ooc.ecosystems.simulation.helpers;

import java.util.Random;

public class Randomer {

	// A shared random number generator to control breeding.
	protected static final Random rand = new Random();

	public static boolean randomBooleanWithProb(double probability){
		return rand.nextDouble() < probability;
	}

	public static int randomInt(int maxInt){
		return rand.nextInt(maxInt);
	}

	public static double randomDouble(){
		return rand.nextDouble();
	}

	public static Random getRandomInstance(){
		return rand;
	}

}
