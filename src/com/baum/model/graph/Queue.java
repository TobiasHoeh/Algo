package com.baum.model.graph;

import java.util.Vector;

public class Queue<T> {
	private Vector<T> q = new Vector<T>();

	public boolean isEmpty() {
		return q.size() == 0;
	} // isEmpty

	public void put(T element) {
		q.add(element);
	} // put

	// Element, das sich am l�ngsten in der Schlange befindet 
	// �ltestes Element, entfernen
	public T getFirst() {
		if (q.isEmpty())
			return null;
		return q.remove(0);
	} // getFirst

	// Element, das als letztes eingef�gt wurde j�ngstes Element,
	// entfernen
	public T getLast() {
		if (q.isEmpty())
			return null;
		return q.remove(q.size() - 1);
	} // getLast
} // Queue

