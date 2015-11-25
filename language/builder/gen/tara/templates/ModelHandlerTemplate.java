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
			rule().add((condition("type", "model"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\nimport tara.magritte.Model;\n\nimport java.util.List;\n\npublic class ModelHandler implements tara.magritte.ModelHandler {\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("\n\n\tpublic ModelHandler(Model model) {\n\t    ")).add(mark("node", "assign").multiple("\n")).add(literal("\n\t}\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "declaration"))).add(literal("private ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "declaration"))).add(literal("private List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List;")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal(" = model.components(")).add(mark("qn", "reference")).add(literal(".class).get(0);")),
			rule().add((condition("type", "node")), (condition("trigger", "assign"))).add(mark("name", "firstLowerCase")).add(literal("List = model.components(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node")), (condition("type", "single")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n    return ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}"))
		);
		return this;
	}
}