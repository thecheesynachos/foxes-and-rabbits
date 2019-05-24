package io.muzoo.ooc.ecosystems.observer;

public interface Subject {

	void notifyObserver();

	void getAttachedBy(Observer observer);

}
