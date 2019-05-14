package io.muzoo.ooc.ecosystems.simulation;

import io.muzoo.ooc.ecosystems.creatures.Actor;
import io.muzoo.ooc.ecosystems.observer.Observer;
import io.muzoo.ooc.ecosystems.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class SimulationFacade implements Subject {

	private Simulator simulator;
	private Field field;
	private FieldStats fieldStats;
	private List<Observer> observers;
	private int step;

	public SimulationFacade(int depth, int width){
		simulator = new Simulator(depth, width);
		field = new Field(depth, width);
		fieldStats = new FieldStats();
		observers = new ArrayList<>();
		reset();
	}

	public void reset(){
		simulator.reset();
		field.clear();
		simulator.populate(field);
		this.step = 0;
		// Show the starting state in the view.
		notifyObservers();
	}

	public void simulate(int numSteps){
		for(int i = 1; i <= numSteps; i++){
			simulateOneStep();
		}
	}

	private void simulateOneStep(){

		simulator.simulateOneStep(this);

		List<Actor> liveActors = simulator.getCurrentLiveActors();
		fieldStats.generateCounts(liveActors);
		fieldStats.countFinished();
		step++;

		// display the new field on screen
		notifyObservers();

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

	@Override
	public List<Observer> getObservers() {
		return observers;
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : observers){
			observer.update();
		}
	}

	@Override
	public void attachObserver(Observer observer) {
		observers.add(observer);
		observer.attachTo(this);
	}
}
