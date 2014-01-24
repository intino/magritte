package monet.tara.transpiler.metamodel.metamodeldescription;

public class Param {
	String type, name;

	public Param(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}
