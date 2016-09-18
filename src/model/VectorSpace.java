package model;

import java.util.ArrayList;

public class VectorSpace {
	private ArrayList<Integer> value;
	private String className;
	private int length;
	public VectorSpace() {
		value = new ArrayList<Integer>();
		className = "undef";
		setLength(0);
	}
	
	public VectorSpace(ArrayList<Integer> value) {
		this.value = value;
	}
	
	public VectorSpace(ArrayList<Integer> value, int length, String className) {
		this.value = value;
		this.setLength(length);
		this.className = className;
	}
	
	public int getAttrNum() {
		return value.size();
	}
	
	//getter and setter
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public ArrayList<Integer> getValue() {
		return value;
	}
	
	public void setValue(ArrayList<Integer> val) {
		value = val;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
