package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ModelTemplate extends Template {

	protected ModelTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModelTemplate(Locale.ENGLISH, CRLF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "model"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\nimport tara.magritte.Node;\n\nimport java.util.List;\n\npublic class ")).add(mark("name", "javaValidName")).add(literal("Model {\n\n   ")).add(mark("node", "declaration").multiple("\n")).add(literal("\n\n\tpublic static void use(Node node) {\n\t    ")).add(mark("node", "assign").multiple("\n")).add(literal("\n\t}\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "declaration"))).add(literal("private static ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "declaration"))).add(literal("private static List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List;")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal(" = node.components(")).add(mark("qn", "reference")).add(literal(".class).get(0);")),
			rule().add((condition("type", "node")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal("List = node.components(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "getter"))).add(literal("public static ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLoweCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public static List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLoweCase")).add(literal("List() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}"))
		);
		return this;
	}
}