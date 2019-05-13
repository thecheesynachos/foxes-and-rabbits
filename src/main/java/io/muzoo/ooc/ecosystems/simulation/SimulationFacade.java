package io.muzoo.ooc.ecosystems.simulation;

import io.muzoo.ooc.ecosystems.creatures.Actor;
import io.muzoo.ooc.ecosystems.creatures.Hunter;
import io.muzoo.ooc.ecosystems.creatures.animals.Fox;
import io.muzoo.ooc.ecosystems.creatures.animals.Rabbit;
import io.muzoo.ooc.ecosystems.creatures.animals.Tiger;

import java.awt.Color;
import java.util.List;

public class SimulationFacade {

	private Simulator simulator;
	private SimulatorView simulatorView;
	private Field field;
	private FieldStats fieldStats;

	public SimulationFacade(int depth, int width){

		simulator = new Simulator(depth, width);

		field = new Field(depth, width);

		fieldStats = new FieldStats();

		simulatorView = new SimulatorView(depth, width);
		simulatorView.setColor(Fox.class, Color.blue);
		simulatorView.setColor(Rabbit.class, Color.yellow);
		simulatorView.setColor(Tiger.class, Color.red);
		simulatorView.setColor(Hunter.class, Color.black);

		reset();

	}

	public void reset(){
		simulator.reset();
		field.clear();
		simulator.populate(field);
		// Show the starting state in the view.
		simulatorView.showStatus(0, this);
	}

	public void simulate(int numSteps){
		for(int i = 1; i <= numSteps; i++){
			simulateOneStep(i);
		}
	}

	private void simulateOneStep(int step){

		simulator.simulateOneStep(this);

		List<Actor> liveActors = simulator.getCurrentLiveActors();
		fieldStats.generateCounts(liveActors);
		fieldStats.countFinished();

		// display the new field on screen
		simulatorView.showStatus(step, this);

	}

	public Field getField() {
		return field;
	}

	public FieldStats getFieldStats() {
		return fieldStats;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
