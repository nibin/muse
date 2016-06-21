package com.msi.brules.domain;

public class Person {

	String name;
	String nationality;
	boolean shouted;

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public boolean isShouted() {
		return shouted;
	}

	public void shout() {
		this.shouted = true;
		System.out.println("Shouted!");
	}

	public void shutUp() {
		this.shouted = false;
		System.out.println("Silence!");

	}

}
