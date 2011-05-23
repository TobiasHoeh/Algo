package com.baum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.baum.model.Gehaltserhoehung;
import com.baum.model.Mitarbeiter;
import com.baum.model.tree.Tree2;
import com.baum.model.tree.TreeAccessException;
import com.baum.model.tree.UndefinedValueException;

public class Tree2Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestTree2() {
		
		try {
			Mitarbeiter m = new Mitarbeiter ( "Direktor", 8000.0 );
			Tree2<Mitarbeiter> t = Tree2.maketree (m);
			m = new Mitarbeiter ( "Chef Wirtschaft", 7000.0 );
			t.addLeftNode ( m );
			m = new Mitarbeiter ( "Chef Technik", 7000.0 );
			t.addRightNode ( m );
			m = new Mitarbeiter ( "Mueller", 5000.0 );
			t.right().addRightNode ( m );
			m = new Mitarbeiter ( "Schmidt", 5000.0 );
			t.right().addLeftNode ( m );
			m = new Mitarbeiter ( "Heinrich", 4400.0 );
			
			t.left().addRightNode ( m );
			m = new Mitarbeiter ( "Meerschweinchen", 0.0 );
			t.left().right().addLeftNode ( m );
			System.out.println ( t.printPreorder ( 0 ) );
			
			t.processTree ( new Gehaltserhoehung (5.0) );
			System.out.println ( t.printPreorder ( 0 ) );
		} catch (UndefinedValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (TreeAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
