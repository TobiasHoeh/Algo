package com.baum.model.tree;

import com.baum.model.tree.TreeAccessException;

public class Tree2<Elem> {
	private Elem element = null;
	private Tree2<Elem> left = null;
	private Tree2<Elem> right = null;

	// //////////////////////////////////////////////////////////////////////
	// ///// Baum erzeugen
	// //////////////////////////////////////////////////////////////////////
	public static <Elem> Tree2<Elem> empty() {
		return new Tree2<Elem>();
	}

	private Tree2() {
	}

	private Tree2(Elem value) {
		this.element = value;
	} // Constructor

	public static<Elem> Tree2<Elem> maketree(Elem value)
			throws UndefinedValueException {
		Tree2<Elem> neuerBaum = null;
		if (value == null) {
			throw new UndefinedValueException();
		}
		neuerBaum = new Tree2<Elem>(value);
		neuerBaum.left = new Tree2<Elem>();
		neuerBaum.right = new Tree2<Elem>();
		return neuerBaum;
	} // makeTree

	// //////////////////////////////////////////////////////////////////////
	// ///// linken und rechten Knoten hinzufügen
	// //////////////////////////////////////////////////////////////////////

	public void addRightNode(Elem value) throws TreeAccessException,
			UndefinedValueException {
		if (isEmpty()) {
			throw new TreeAccessException("Leerer Baum!");
		}
		if (hasRightNode()) {
			throw new TreeAccessException(
					"Achtung: Ein rechter Knoten ist bereits vorhanden.");
		}
		if (value == null) {
			throw new UndefinedValueException("Der übergebene Wert is null!");
		}

		right = maketree(value);
	} // addRightNode

	public void addLeftNode(Elem value) throws TreeAccessException,
			UndefinedValueException {
		if (isEmpty()) {
			throw new TreeAccessException("Leerer Baum!");
		}
		if (hasLeftNode()) {
			throw new TreeAccessException(
					"Achtung: Ein linker Knoten ist bereits vorhanden.");
		}
		if (value == null) {
			throw new UndefinedValueException("Der übergebene Wert is null!");
		}

		left = maketree(value);
	} // addLeftNode

	// //////////////////////////////////////////////////////////////////////
	// ///// linken und rechten Knoten löschen
	// //////////////////////////////////////////////////////////////////////
	private void deleteNode() throws TreeAccessException {
		if(isEmpty()) {
			return;
		}
		if(hasLeftNode()) {
			left.deleteNode();
			left = null;
		}
		if(hasRightNode()) {
			right.deleteNode();
			right = null;
		}
		this.element = null;
	}

	private void deleteRightTree() throws TreeAccessException {
		if (!hasRightNode()) {
			throw new TreeAccessException("Rechter Teilbaum existiert nicht!!!");
		}
		right.deleteNode();
		right = null;
	}

	private void deleteLeftTree() throws TreeAccessException {
		if (!hasLeftNode()) {
			throw new TreeAccessException("Linker Teilbaum existiert nicht!!!");
		}
		left.deleteNode();
		left = null;
	}
	
	public void delete() throws TreeAccessException {
		if(isEmpty()) {
			throw new TreeAccessException("Der Baum ist leer!!!");
		}
		if(hasLeftNode()) {
			deleteLeftTree();
			left = null;
		} // if
		if(hasRightNode()) {
			deleteRightTree();
			right = null;
		} // if
		element = null;
	}

	// //////////////////////////////////////////////////////////////////////
	// ///// get-Methoden
	// //////////////////////////////////////////////////////////////////////

	// hole linke oder rechte Seite
	public Tree2<Elem> left() throws TreeAccessException {
		if (isEmpty()) {
			throw new TreeAccessException("Kein linker Knoten vorhanden");
		}
		return left;
	} // left

	public Tree2<Elem> right() throws TreeAccessException {
		if (isEmpty()) {
			throw new TreeAccessException("Kein rechter Knoten vorhanden");
		}
		return right;
	} // right

	public Elem value() throws TreeAccessException {
		if (isEmpty()) {
			throw new TreeAccessException("Leerer Baum");
		}
		return element;
	} // value

	// //////////////////////////////////////////////////////////////////////
	// ///// prüf-Methoden
	// //////////////////////////////////////////////////////////////////////

	// prüfe auf linke oder recht Seite
	public boolean hasLeftNode() throws TreeAccessException {
		if (isEmpty()) {
			throw new TreeAccessException("Leerer Baum!");
		}
		return left.isEmpty() == false;
	} // hasLeftNode

	public boolean hasRightNode() throws TreeAccessException {
		if (isEmpty()) {
			throw new TreeAccessException("Leerer Baum!");
		}
		return right.isEmpty() == false;
	} // hasRightNode

	public boolean isEmpty() {
		if (left == null && right == null && element == null) {
			return true;
		} else {
			return false;
		}
	} // isEmpty

	public boolean isLeafNode() throws TreeAccessException {
		if (isEmpty()) {
			throw new TreeAccessException("Der Baum ist leer!");
		}
		return left.isEmpty() && right.isEmpty();
	} // isLeafNode

	// //////////////////////////////////////////////////////////////////////
	// ///// Ausgabe-Methoden
	// //////////////////////////////////////////////////////////////////////
	public void processTree(ProcessTree<Elem> ppo) {
		if(isEmpty()) return;
		left.processTree(ppo);
		ppo.f(element);
		right.processTree(ppo);
	} // processTree
	
	private String cr() {
		return "\n";
	} // cr
	
	private String space(int n) {
		String sp ="";
		for(int i=0; i<n;i++) {
			sp = sp + " ";
		} // for
		return sp;
	} // space
	
	public String printPreorder(int n) {
		if(isEmpty()) return "";
		return space(3*n) + element.toString() + cr() + left.printPreorder(n+1) + right.printPreorder(n+1);
	}
}
