package io.muzoo.ooc.ecosystems;

import io.muzoo.ooc.ecosystems.simulation.SimulationFacade;
import io.muzoo.ooc.ecosystems.observer.SimulatorView;

public class Application {

    public static void main(String[] args) {
        SimulationFacade simFacade = new SimulationFacade(100, 180);
        SimulatorView simView = new SimulatorView(100, 180);
        simFacade.attachObserver(simView);
        simFacade.simulate(500);
        System.exit(0);
    }

}
