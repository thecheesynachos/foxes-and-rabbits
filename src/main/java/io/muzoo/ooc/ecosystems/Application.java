package io.muzoo.ooc.ecosystems;

import io.muzoo.ooc.ecosystems.simulation.SimulationFacade;

public class Application {
    public static void main(String[] args) {
        SimulationFacade simFacade = new SimulationFacade(100, 180);
        simFacade.simulate(500);
        System.exit(0);
    }

}
