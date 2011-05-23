package com.baum.model.graph;

//Jörg Baumgart
//08-02-2011

import java.io.PrintWriter;

public class Graph<Elem> {

	protected int nextIndex = 0;
	protected Node[] nodeList;
	protected EdgeAttributes[][] ajMatrix;
	protected Queue<Node> activeNodes;
	private Graph<Elem> bfsResult;

	// Innere Klasse f\"ur die Beschreibung der Knoten
	protected class Node<Elem> {
		protected Elem value = null;
		protected int index = 0;
		protected String color = "grey";

		protected Node(Elem value) {
			this.value = value;
			// Zugehöriger Index in der Adjazenzmatrix und der Knotenliste
			index = nextIndex;
		} // Constructor

		protected Node(Elem value, String color) {
			this.value = value;
			this.value = value;
			// Zugehöriger Index in der Adjazenzmatrix und der Knotenliste
			index = nextIndex;
		} // Constructor

		protected void mark() {
			color = "green";
		} // mark

		protected void unmark() {
			color = "grey";
		} // unmark

		protected boolean isMarked() {
			return color.equals("green");
		} // isMarked
	} // inner class Node

	// Achtung: Eine innere Klasse einer generischen Klasse ist implizit
	// generisch;
	// damit ist keine Array-Erzeugung mehr möglich. Daher wird EdgeAttributes
	// explizit als generisch deklariert; jetzt ist es möglich, eine
	// Adjazenzmatrix
	// und Knotenliste zu erzeugen, die für E dann den Typ Object verwendet
	protected class EdgeAttributes<E> {
		protected double weight = 0.0;
		protected String color = "black";

		protected EdgeAttributes(double weight, String color) {
			this.weight = weight;
			this.color = color;
		} // Constructor

		protected EdgeAttributes() {
		} // Constructor
	} // inner class EdgeAttributes

	public Graph(int nodeCount) {
		ajMatrix = new EdgeAttributes[nodeCount][nodeCount];
		nodeList = new Node[nodeCount];
	} // Constructor

	public void insertNode(Elem value) {
		if (nextIndex == ajMatrix.length)
			return;
		Node<Elem> nd = new Node<Elem>(value);
		nodeList[nextIndex] = nd;
		nextIndex++;
	} // insertNode

	public void insertNode(Elem value, String color) {
		if (nextIndex == ajMatrix.length)
			return;
		Node<Elem> nd = new Node<Elem>(value, color);
		nodeList[nextIndex] = nd;
		nextIndex++;
	} // insertNode

	protected int getIndex(Elem value) {
		for (int i = 0; i < nodeList.length; i++) {
			if (nodeList[i] != null && nodeList[i].value.equals(value))
				return i;
		} // for
		return -1;
	} // getIndex

	public void insertEdge(Elem startNode, Elem endNode) {
		ajMatrix[getIndex(startNode)][getIndex(endNode)] = new EdgeAttributes();
	} // insertEdge

	public void insertEdge(Elem startNode, Elem endNode, double weight,
			String color) {
		ajMatrix[getIndex(startNode)][getIndex(endNode)] = new EdgeAttributes(
				weight, color);
	} // insertEdge

	// Alle Knoten deaktivieren
	protected void deactivateNodes() {
		for (int i = 0; i < nodeList.length; i++) {
			if (nodeList[i] != null) {
				nodeList[i].unmark();
			} // if
		} // for
	} // deactivateNodes

	// Alle Knoten aktivieren, die ausgehend von startNode zu finden sind
	protected void activateNodes(Node startNode) {
		int startIndex = startNode.index;
		for (int endIndex = 0; endIndex < ajMatrix[startIndex].length; endIndex++) {
			Node endNode = nodeList[endIndex];
			if (ajMatrix[startIndex][endIndex] != null
					&& startIndex != endIndex && !endNode.isMarked()) {
				endNode.mark();
				activeNodes.put(endNode);
				bfsResult.insertNode((Elem) endNode.value);
				bfsResult.insertEdge((Elem) startNode.value,
						(Elem) endNode.value);
			} // if
		} // for
	} // activateNodes

	protected Node activateNode(Node startNode) {
		int startIndex = startNode.index;
		for (int endIndex = 0; endIndex < ajMatrix[startIndex].length; endIndex++) {
			Node endNode = nodeList[endIndex];
			if (ajMatrix[startIndex][endIndex] != null
					&& startIndex != endIndex && !endNode.isMarked()) {
				endNode.mark();
				activeNodes.put(endNode);
				bfsResult.insertNode((Elem) endNode.value);
				bfsResult.insertEdge((Elem) startNode.value,
						(Elem) endNode.value);
				return endNode;
			} // if
		} // for
		return null;
	} // activateNodes

	public Graph<Elem> breadthFirstSearch(Elem searchNode) {
		deactivateNodes();
		bfsResult = new Graph<Elem>(nodeList.length);
		bfsResult.insertNode(searchNode);
		activeNodes = new Queue<Node>();
		Node nd = nodeList[getIndex(searchNode)];
		nd.mark();
		activeNodes.put(nd);
		while (!activeNodes.isEmpty()) {
			nd = activeNodes.getFirst();
			activateNodes(nd);
		} // while
		return bfsResult;
	} // breadthFirstSearch

	public Graph<Elem> depthFirstSearch(Elem searchNode) {
		deactivateNodes();
		bfsResult = new Graph<Elem>(nodeList.length);
		bfsResult.insertNode(searchNode);
		activeNodes = new Queue<Node>();
		Node nd = nodeList[getIndex(searchNode)];
		nd.mark();
		while (true) {
			Node nd2 = activateNode(nd);
			if (nd2 != null) {
				activeNodes.put(nd);
				nd = nd2;
			} else {
				if (activeNodes.isEmpty()) {
					break;
				} else {
					nd = activeNodes.getLast();
				}
			}
		} // while
		return bfsResult;
	} // breadthFirstSearch

	public void print(PrintWriter out) {
		for (int i = 0; i < nodeList.length; i++) {
			Node startNode = nodeList[i];
			if (startNode == null)
				return;
			out.println("Node: value=" + startNode.value + " index="
					+ startNode.index + " color=" + startNode.color);
			for (int j = 0; j < ajMatrix[i].length; j++) {
				if (ajMatrix[i][j] != null) {
					Node endNode = nodeList[j];
					EdgeAttributes ea = ajMatrix[i][j];
					out.println("   Edge(" + startNode.value + ","
							+ endNode.value + ")" + " weight=" + ea.weight
							+ " color=" + ea.color);
				} // if
			} // for
		} // for
	} // print

} // Graph

