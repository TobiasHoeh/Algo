package com.baum.model;

public class Mitarbeiter {
	private double gehalt;
	private String name;
	
	public Mitarbeiter(String name, double gehalt) {
		this.gehalt = gehalt;
		this.name = name;
	}
	
	public String toString() {
		return name + " " + Double.toString(gehalt);
	}
	
	public void erhoeheGehalt( double prozent) {
		gehalt = (1 + prozent / 100) * gehalt;
	}
	
	public double getGehalt() {
		return gehalt;
	}
}
