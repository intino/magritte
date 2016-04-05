package tara.io;

import java.util.ArrayList;
import java.util.List;

public abstract class Variable {

	public java.lang.String name;
	public List<?> values = new ArrayList<>();

	public static class Integer extends Variable {
	}

	public static class Double extends Variable {
	}

	public static class Boolean extends Variable {
	}

	public static class String extends Variable {
	}

	public static class Resource extends Variable {
	}

	public static class Reference extends Variable {
	}

	public static class Word extends Variable {
	}

	public static class Function extends Variable {
	}

	public static class Date extends Variable {
	}

	public static class Time extends Variable {
	}

	public static class Object extends Variable {
	}
}
