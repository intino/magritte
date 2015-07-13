package tara.semantic;

public class Script {

	public static Definition root() {
		return new RootDefinition();
	}

	public static Definition define() {
		return new ScriptDefinition();
	}

	public static TargetDefinition targetDefine() {
		return new ScriptTargetDefinition();
	}

	public static FacetDefinition facetDefine() {
		return new ScriptFacetDefinition();
	}

}
