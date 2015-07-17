package tara.io;

import java.io.Serializable;

public class Entry implements Serializable {

	public String name;
	public String[] types;
	public Var[] vars;
	public Entry[] entries;

}
