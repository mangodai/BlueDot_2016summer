package edu.Formula;

public class Proposition{
	private boolean value;
	private char name='P';
	
	public Proposition(boolean value, char name) {
		super();
		this.value = value;
		this.setName(name);
	}
	public Proposition(){	
	}
	public boolean getValue(){
		return this.value;
	}
	public void setValue(boolean v){
		this.value=v;
	}
	public char getName() {
		return name;
	}
	public void setName(char name) {
		this.name = name;
	}
}