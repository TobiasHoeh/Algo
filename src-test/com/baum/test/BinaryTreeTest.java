package com.baum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.baum.model.Gehaltserhoehung;
import com.baum.model.Mitarbeiter;
import com.baum.model.tree.BinarySearchTree;
import com.baum.model.tree.Tree2;
import com.baum.model.tree.TreeAccessException;
import com.baum.model.tree.UndefinedValueException;

public class BinaryTreeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void BinaryTreeTest() {
		Mitarbeiter m;
		BinarySearchTree<Mitarbeiter> binaryTree = BinarySearchTree.empty();
		
		m = new Mitarbeiter ( "Chef Wirtschaft", 7000.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Chef Technik", 6000.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Heinrich", 4400.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Heinrich", 6300.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Meerschweinchen", 0.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Mueller", 10000.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Mueller", 13000.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Mueller", 15000.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Meerschweinchen", 33.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Mueller", 10040.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Mueller", 154000.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		m = new Mitarbeiter ( "Mueller", 1500.0 );
		binaryTree.insert((int)m.getGehalt(), m);
		
		System.out.print(binaryTree.getPreorderString(5));
		
		try {
			binaryTree.delete(7000);
			binaryTree.delete(154000);
			binaryTree.delete(13000);
		} catch (TreeAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		
		System.out.print(binaryTree.getPreorderString(5));
		binaryTree.toString();
		
	}

}
