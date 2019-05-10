package io.muzoo.ooc.ecosystems;

import io.muzoo.ooc.ecosystems.simulation.Simulator;

public class Application {
    public static void main(String[] args) {
        Simulator sim = new Simulator(100, 180);
        sim.simulate(500);
        System.exit(0);
    }

}
