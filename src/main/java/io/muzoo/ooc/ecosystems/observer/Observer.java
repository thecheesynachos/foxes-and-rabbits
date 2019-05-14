package io.muzoo.ooc.ecosystems.observer;

public interface Observer {

	void update();

	void attachTo(Subject subject);

	Subject getAttachedSubject();

}
