package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ModelTemplate extends Template {

	protected ModelTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModelTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "scene"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\npublic class ")).add(mark("name", "javaValidName")).add(literal("Model extends magritte.wraps.Model {\n\n    public ")).add(mark("name", "javaValidName")).add(literal("Model(magritte.wraps.Morph morph) {\n        super(morph);\n    }\n\n    public ")).add(mark("name", "javaValidName")).add(literal("Model(magritte.Graph graph) {\n\t\tsuper(graph);\n\t}\n\n\t")).add(mark("root").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "root")), not(condition("type", "single")), (condition("trigger", "root"))).add(literal("public magritte.Set<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "javaValidName", "plural", "firstLowerCase")).add(literal("() {\n    return _get(\"")).add(mark("name", "FirstLowerCase", "plural")).add(literal("\", ")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(int index) {\n    return ")).add(mark("name", "javaValidName", "plural", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "root")), (condition("type", "single")), (condition("trigger", "root"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n    return _get(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", ")).add(mark("qn", "reference")).add(literal(".class).get(0);\n}"))
		);
		return this;
	}
}