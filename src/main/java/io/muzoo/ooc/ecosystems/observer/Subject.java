package io.muzoo.ooc.ecosystems.observer;

import java.util.List;

public interface Subject {

	List<Observer> getObservers();

	void notifyObservers();

	void attachObserver(Observer observer);

}
