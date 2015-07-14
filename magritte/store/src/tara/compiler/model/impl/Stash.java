package tara.compiler.model.impl;

import java.io.Serializable;

public class Stash implements Serializable {

	public String name;
	public String[] types;
	public Variable[] variables;
	public Stash[] components;

	public static class Variable implements Serializable {
		public String name;
		public Object[] values;
	}

}
