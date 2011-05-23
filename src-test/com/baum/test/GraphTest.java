package com.baum.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.baum.model.Gehaltserhoehung;
import com.baum.model.Mitarbeiter;
import com.baum.model.graph.Graph;
import com.baum.model.tree.BinarySearchTree;
import com.baum.model.tree.Tree2;
import com.baum.model.tree.TreeAccessException;
import com.baum.model.tree.UndefinedValueException;

public class GraphTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void GraphTest() {
		Graph<String> g1 = new Graph<String>(7);
		g1.insertNode("A");
		g1.insertNode("B");
		g1.insertNode("C");
		g1.insertNode("D");
		g1.insertNode("E");
		g1.insertNode("F");
		g1.insertNode("G");
		g1.insertEdge("A", "D");
		g1.insertEdge("A", "E");
		g1.insertEdge("B", "C");
		g1.insertEdge("C", "A");
		g1.insertEdge("D", "B");
		g1.insertEdge("E", "F");
		g1.insertEdge("F", "E");
		g1.insertEdge("F", "F");
		g1.insertEdge("G", "A");
		
//		System.out.print("\n\nBreite-zuerst-Suche mit dem Startknoten G:\n");
//		System.out.print(g1.breadthFirstSearch("A"));
		
		PrintWriter out;
		try {
			out = new PrintWriter("g1.tex");
			g1.print(out);
			out.println("\n\nBreite-zuerst-Suche mit dem Startknoten G:\n");
			Graph<String> bfs1 = g1.breadthFirstSearch("A");
			bfs1.print(out);
			bfs1 = g1.depthFirstSearch("A");
			bfs1.print(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
