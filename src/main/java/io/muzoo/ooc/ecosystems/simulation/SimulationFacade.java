package io.muzoo.ooc.ecosystems.simulation;

import io.muzoo.ooc.ecosystems.observer.SimulatorView;


public class SimulationFacade {

	private Simulator simulator;
	private Field field;
	private FieldStats fieldStats;
	private SimulatorView simulatorView;
	private int step;

	public SimulationFacade(int depth, int width){
		simulator = new Simulator(depth, width, this);
		field = new Field(depth, width);
		fieldStats = new FieldStats();
		simulatorView = new SimulatorView(depth, width, this);
		simulatorView.attachTo(simulator);
		reset();
	}

	public void reset(){
		simulator.reset();
		field.clear();
		simulator.populate(field);
		this.step = 0;
	}

	public void simulate(int numSteps){
		for(int i = 1; i <= numSteps; i++){
			simulateOneStep();
		}
	}

	private void simulateOneStep(){
		simulator.simulateOneStep(this);
	}

	public void incrementStep(){
		step++;
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

	public int getStep() {
		return step;
	}

}
