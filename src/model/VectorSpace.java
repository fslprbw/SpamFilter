package model;

import java.util.ArrayList;

public class VectorSpace {
	private ArrayList<Integer> value;
	private String className;
	
	public VectorSpace() {
		value = new ArrayList<Integer>();
		className = "undef";
	}
	
	public VectorSpace(ArrayList<Integer> value, String className) {
		this.value = value;
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
}
