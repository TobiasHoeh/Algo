package com.baum.model.tree;

import java.util.Vector;

public class Tree1<Element> {
		private boolean isempty = true;
		private Tree1<Element> right;
		private Tree1<Element> left;
		private Vector<Element> liste;
		private Element elem = null;
		
		public static<Element> Tree1<Element> empty() {
			return new Tree1<Element>();
		}
		
		private Tree1() {
		}
		
		private Tree1(Tree1<Element> links, Element elem, Tree1<Element> rechts) {
			this.left = (Tree1<Element>) links;
			this.right = (Tree1<Element>) rechts;
			this.elem = elem;
		}
		
		public static<Element> Tree1<Element> maketree(Tree1<Element> links, Element elem, Tree1<Element> rechts) throws UndefinedValueException {
			if(elem == null) {
				throw new UndefinedValueException("x=null!");
			}
			Tree1<Element> neuerBaum = new Tree1<Element>(links, elem, rechts);
			return neuerBaum;
		}
		
		public Element value() throws TreeAccessException {
			if(isEmpty()) throw new TreeAccessException("Der Baum ist leer");
			return elem;
		}
		
		public Tree1<Element> left() throws TreeAccessException {
			if(isEmpty()) throw new TreeAccessException("Der Baum ist leer");
			if(left == null) throw new TreeAccessException("Es ist kein linker Teilbaum vorhanden");
			return left;
		}

		public Tree1<Element> right() throws TreeAccessException {
			if(isEmpty()) throw new TreeAccessException("Der Baum ist leer");
			if(right == null) throw new TreeAccessException("Es ist kein rechter Teilbaum vorhanden");
			return right;
		}
		
		private boolean isEmpty() {
			return (left==null && right == null && elem == null);
		}
		
		private String space(int n) {
			String s = "";
			for(int i = 0; i<n;i++) {
				s = s + " ";
			}
			return s;
		}
		
		private String cr() {
			return "\n";
		}
		
		public String inorder(int n) {
			if(isEmpty()) return cr();
			String ausgabe = "";
			if(left!=null) {
				left.inorder(n+1);
			}	
			ausgabe = ausgabe + elem.toString();
			if(right != null) {
				right.inorder(n+1);
			}
			return ausgabe;
		}
}
