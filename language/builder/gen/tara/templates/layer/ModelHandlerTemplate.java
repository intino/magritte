package tara.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ModelHandlerTemplate extends Template {

	protected ModelHandlerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModelHandlerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "model"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\nimport tara.magritte.Model;\n\nimport java.util.List;\n\npublic class ModelHandler extends tara.magritte.ModelWrapper {\n\n\tprotected Model model;\n\t")).add(mark("node", "list").multiple("\n")).add(literal("\n\n\tpublic ModelHandler(Model model) {\n\t\tthis.model = model;\n\t    ")).add(mark("node", "assign").multiple("\n")).add(literal("\n\t}\n\n\t@Override\n    protected void addInstance(tara.magritte.Instance instance) {\n        ")).add(mark("node", "add").multiple("\n")).add(literal("\n    }\n\n    @Override\n    protected void removeInstance(tara.magritte.Instance instance) {\n        ")).add(mark("node", "remove").multiple("\n")).add(literal("\n    }\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n\t")).add(mark("node", "filter").multiple("\n\n")).add(literal("\n\t")).add(mark("node", "new").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "single")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = instance.as(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("trigger", "add"))),
			rule().add((condition("type", "single")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = null;")),
			rule().add((condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.remove(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("trigger", "remove"))),
			rule().add((condition("type", "node & single")), (condition("trigger", "list"))).add(literal("private ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "list"))).add(literal("private List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List;")),
			rule().add((condition("type", "node & single")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal(" = model.components(")).add(mark("qn", "reference")).add(literal(".class).stream().findFirst().orElse(null);")),
			rule().add((condition("type", "node")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal("List = model.components(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node & single")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}")),
			rule().add((condition("type", "node")), not(condition("type", "single")), (condition("trigger", "filter"))).add(literal("public List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> predicate) {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List.stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "node & concept")), not(condition("type", "final")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("(String _spacename, String _name")).add(expression().add(literal(", ")).add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n\t")).add(mark("qn", "reference")).add(literal(" newElement = model.newMain(")).add(mark("qn", "reference")).add(literal(".class, _spacename, _name).as(")).add(mark("qn", "reference")).add(literal(".class);\n    ")).add(mark("variable", "assign").multiple("\n")).add(literal("\n\treturn newElement;\n\n}\npublic ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("(String _spacename")).add(expression().add(literal(", ")).add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n    ")).add(mark("qn", "reference")).add(literal(" newElement = model.newMain(")).add(mark("qn", "reference")).add(literal(".class, _spacename).as(")).add(mark("qn", "reference")).add(literal(".class);\n    ")).add(mark("variable", "assign").multiple("\n")).add(literal("\n    return newElement;\n}"))
		);
		return this;
	}
}