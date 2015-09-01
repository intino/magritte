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
			rule().add((condition("type", "model"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\nimport tara.magritte.Morph;\nimport tara.magritte.Node;\nimport tara.magritte.Root;\nimport tara.magritte.Type;\n\nimport java.util.List;\n\npublic class ")).add(mark("name", "javaValidName")).add(literal("Model {\n\tprivate static Root root;\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("\n\n\tpublic static void use(Node node) {\n\t\troot = node.morph(Root.class);\n\t    ")).add(mark("node", "assign").multiple("\n")).add(literal("\n\t}\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n\n\tpublic static Type type(Class<? extends Morph> aClass) {\n    return root.type(aClass);\n    }\n\n    public static Type type(String type) {\n    return root.type(type);\n    }\n\n    public static <T extends Morph> T newInstance(Class<T> aClass) {\n    \treturn newInstance(aClass, \"\");\n    }\n    public static <T extends Morph> T newInstance(Class<T> aClass, String nodeName) {\n    \treturn root.type(aClass).newInstance(nodeName).morph(aClass);\n    }\n    public static Node newInstance(String type) {\n    \treturn newInstance(type, \"\");\n    }\n    public static Node newInstance(String type, String nodeName) {\n    \treturn root.type(root.type(type).morphClass()).newInstance();\n    }\n}")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "declaration"))).add(literal("private static ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "declaration"))).add(literal("private static List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List;")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal(" = node.components(")).add(mark("qn", "reference")).add(literal(".class).get(0);")),
			rule().add((condition("type", "node")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal("List = node.components(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "getter"))).add(literal("public static ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public static List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}"))
		);
		return this;
	}
}