package com.baum.model;

import com.baum.model.tree.ProcessTree;

public class Gehaltserhoehung implements ProcessTree<Mitarbeiter> {
	private double prozent;
	
	public Gehaltserhoehung(double prozent) {
		this.prozent = prozent;
	}

	@Override
	public void f(Mitarbeiter x) {
		x.erhoeheGehalt(prozent);
		
	}
}
