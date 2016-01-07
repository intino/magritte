package tara.templates;

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
			rule().add((condition("type", "model"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\nimport tara.magritte.Model;\n\nimport java.util.List;\n\npublic class ModelHandler extends tara.magritte.ModelWrapper {\n\n\tprotected Model model;\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("\n\n\tpublic ModelHandler(Model model) {\n\t\tthis.model = model;\n\t    ")).add(mark("node", "assign").multiple("\n")).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("    protected void addInstance(tara.magritte.Instance instance) {")).add(literal("\n")).add(literal("        super.addInstance(instance);")).add(literal("\n")).add(literal("        ")).add(mark("node", "add").multiple("\n")).add(literal("\n")).add(literal("    }"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("    @Override")).add(literal("\n")).add(literal("    protected void removeInstance(tara.magritte.Instance instance) {")).add(literal("\n")).add(literal("        super.removeInstance(instance);")).add(literal("\n")).add(literal("        ")).add(mark("node", "remove").multiple("\n")).add(literal("\n")).add(literal("    }"))).add(literal("\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n\t")).add(mark("node", "filter").multiple("\n\n")).add(literal("\n\t")).add(mark("node", "new").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = instance.as(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "add"))),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = null;")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.remove(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "remove"))),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "declaration"))).add(literal("private ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "declaration"))).add(literal("private List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List;")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal(" = model.components(")).add(mark("qn", "reference")).add(literal(".class).stream().findFirst().orElse(null);")),
			rule().add((condition("type", "node")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal("List = model.components(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}")),
			rule().add((condition("type", "node")), not(condition("type", "single")), (condition("trigger", "filter"))).add(literal("public List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List(Predicate predicate) {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List.stream.filter(predicate).collect(java.util.Collectors.toList());\n}")),
			rule().add((condition("type", "node")), not(condition("type", "final")), not(condition("type", "single")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}")),
			rule().add((condition("type", "node")), not(condition("type", "final")), (condition("type", "single")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}"))
		);
		return this;
	}
}